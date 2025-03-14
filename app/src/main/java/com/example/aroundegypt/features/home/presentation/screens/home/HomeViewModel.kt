package com.example.aroundegypt.features.home.presentation.screens.home

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.common.data.models.state.Resource
import com.example.aroundegypt.common.presentation.viewmodel.sendEvent
import com.example.aroundegypt.features.home.domain.models.Experience
import com.example.aroundegypt.features.home.domain.usecases.GetLikedExperiencesUC
import com.example.aroundegypt.features.home.domain.usecases.GetMostRecentExperiencesUC
import com.example.aroundegypt.features.home.domain.usecases.GetRecommendedExperiencesUC
import com.example.aroundegypt.features.home.domain.usecases.LikeExperienceUC
import com.example.aroundegypt.features.home.domain.usecases.SaveLikedExperiencesUC
import com.example.aroundegypt.features.home.domain.usecases.SearchExperiencesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecommendedExperiencesUC: GetRecommendedExperiencesUC,
    private val getMostRecentExperiencesUC: GetMostRecentExperiencesUC,
    private val searchExperiencesUC: SearchExperiencesUC,
    private val likeExperienceUC: LikeExperienceUC,
    private val getLikedExperiencesUC: GetLikedExperiencesUC,
    private val saveLikedExperiencesUC: SaveLikedExperiencesUC
) : ViewModel() {
    val snackbarHostState = SnackbarHostState()
    private var hasLoadedRecommendedExperiences = false
    private var hasLoadedMostRecentExperiences = false
    private var lastClickTime = 0L
    private val debounceTime = 500L

    private val _state = MutableStateFlow(HomeViewStates())
    val state = _state.onStart {
        if (!hasLoadedRecommendedExperiences)
            getRecommendedExperiences()
        if (!hasLoadedMostRecentExperiences)
            getMostRecentExperiences()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), HomeViewStates())

    init {
        loadLikedExperiences()
    }

    private fun loadLikedExperiences() {
        viewModelScope.launch {
            when (val savedLikedExperiences = getLikedExperiencesUC.invoke()) {
                is Resource.Failure -> handleError(savedLikedExperiences)
                is Resource.Loading -> {}
                is Resource.Success -> _state.update { it.copy(likedExperiences = savedLikedExperiences.data) }
            }
        }
    }

    fun setSearchQuery(searchQuery: String) {
        _state.update { it.copy(searchQuery = searchQuery, isSearchPerformed = false) }
        clearSearchResults()
    }

    fun reloadLikedExperiences() {
        viewModelScope.launch {
            when (val savedLikedExperiences = getLikedExperiencesUC.invoke()) {
                is Resource.Failure -> handleError(savedLikedExperiences)
                is Resource.Loading -> {}
                is Resource.Success -> {
                    _state.update { currentState ->
                        currentState.copy(
                            likedExperiences = savedLikedExperiences.data,
                            recommendedExperiences = updateExperiencesWithLikedStatus(
                                currentState.recommendedExperiences,
                                savedLikedExperiences.data
                            ),
                            mostRecentExperiences = updateExperiencesWithLikedStatus(
                                currentState.mostRecentExperiences,
                                savedLikedExperiences.data
                            ),
                            experiencesSearchResult = updateExperiencesWithLikedStatus(
                                currentState.experiencesSearchResult,
                                savedLikedExperiences.data
                            )
                        )
                    }
                }
            }

        }
    }

    fun searchExperiences() {
        viewModelScope.launch {
            clearSearchResults()
            _state.update { it.copy(isSearchPerformed = true) }
            searchExperiencesUC.invoke(_state.value.searchQuery).collect { resource ->
                handleExperienceResource(resource) { experiences ->
                    _state.update { it.copy(experiencesSearchResult = experiences) }
                }
            }
        }
    }

    fun clearSearchResults() {
        _state.update { it.copy(experiencesSearchResult = emptyList()) }
    }

    fun getRecommendedExperiences() {
        viewModelScope.launch {
            hasLoadedRecommendedExperiences = true
            getRecommendedExperiencesUC.invoke().collect { resource ->
                handleExperienceResource(resource) { experiences ->
                    _state.update { it.copy(recommendedExperiences = experiences) }
                }
            }
        }
    }

    private fun getMostRecentExperiences() {
        viewModelScope.launch {
            hasLoadedMostRecentExperiences = true
            getMostRecentExperiencesUC.invoke().collect { resource ->
                handleExperienceResource(resource) { experiences ->
                    _state.update { it.copy(mostRecentExperiences = experiences) }
                }
            }
        }
    }

    fun likeExperience(experience: Experience) {
        if (!canProcessClick() || state.value.likedExperiences.contains(experience)) {
            return
        }

        viewModelScope.launch {
            likeExperienceUC.invoke(experience.id).collect { resource ->
                when (resource) {
                    is Resource.Failure -> handleError(resource)
                    is Resource.Loading -> {} // No action needed
                    is Resource.Success -> {
                        val updatedExperience = experience.copy(
                            isLiked = true,
                            likesNo = resource.data
                        )
                        updateStateWithLikedExperience(updatedExperience)
                    }
                }
            }
        }
    }

    private fun canProcessClick(): Boolean {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime < debounceTime) {
            return false // Ignore rapid clicks
        }
        lastClickTime = currentTime
        return true
    }

    private fun updateStateWithLikedExperience(updatedExperience: Experience) {
        _state.update { currentState ->
            val updatedLikedExperiences = listOf(updatedExperience) + currentState.likedExperiences

            currentState.copy(
                likedExperiences = updatedLikedExperiences,
                recommendedExperiences = updateExperienceInList(
                    currentState.recommendedExperiences,
                    updatedExperience
                ),
                mostRecentExperiences = updateExperienceInList(
                    currentState.mostRecentExperiences,
                    updatedExperience
                ),
                experiencesSearchResult = updateExperienceInList(
                    currentState.experiencesSearchResult,
                    updatedExperience
                )
            )
        }
        viewModelScope.launch {
            saveLikedExperiencesUC.invoke(_state.value.likedExperiences)
        }
    }

    private fun updateExperienceInList(
        experiences: List<Experience>,
        updatedExperience: Experience
    ): List<Experience> {
        return experiences.map { exp ->
            if (exp.id == updatedExperience.id) updatedExperience else exp
        }
    }

    private suspend fun handleExperienceResource(
        resource: Resource<List<Experience>>,
        onSuccess: suspend (List<Experience>) -> Unit
    ) {
        when (resource) {
            is Resource.Failure -> handleError(resource)
            is Resource.Loading -> _state.update { it.copy(isLoading = resource.loading) }
            is Resource.Success -> {
                val updatedExperiences = updateExperiencesWithLikedStatus(
                    resource.data,
                    _state.value.likedExperiences
                )
                onSuccess(updatedExperiences)
            }
        }
    }

    private fun handleError(resource: Resource.Failure) {
        resource.exception.message?.let {
            sendEvent(HomeEvent.ShowError(it))
        }
    }

    private fun updateExperiencesWithLikedStatus(
        experiences: List<Experience>,
        likedExperiences: List<Experience>
    ): List<Experience> {
        return experiences.map { experience ->
            val savedLikedExp = likedExperiences.find { it.id == experience.id }
            if (savedLikedExp != null) {
                experience.copy(
                    isLiked = true,
                    likesNo = savedLikedExp.likesNo
                )
            } else {
                experience
            }
        }
    }
}