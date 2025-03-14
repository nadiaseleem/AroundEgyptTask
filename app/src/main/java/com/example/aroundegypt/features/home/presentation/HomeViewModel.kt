package com.example.aroundegypt.features.home.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.common.data.models.state.Resource
import com.example.aroundegypt.common.presentation.viewmodel.sendEvent
import com.example.aroundegypt.features.home.domain.models.ExperiencesResponse
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

    private val _state = MutableStateFlow(HomeViewStates())
    val state = _state.onStart {
        if (!hasLoadedRecommendedExperiences)
            getRecommendedExperiences()
        if (!hasLoadedMostRecentExperiences)
            getMostRecentExperiences()

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), HomeViewStates())

    init {
        viewModelScope.launch {
            val savedLikedExperiences = getLikedExperiencesUC.invoke()
            _state.update { it.copy(likedExperiences = savedLikedExperiences) }
        }
    }
    fun setSearchQuery(searchQuery: String) {
        _state.update { it.copy(searchQuery = searchQuery, isSearchPerformed = false) }
        clearSearchResults()

    }

    fun searchExperiences() {
        viewModelScope.launch {
            clearSearchResults()
            _state.update { it.copy(isSearchPerformed = true) }
            searchExperiencesUC.invoke(_state.value.searchQuery).collect { resource ->
                when (resource) {
                    is Resource.Failure -> resource.exception.message?.let {
                        sendEvent(HomeEvent.ShowError(it))
                    }
                    is Resource.Loading -> _state.update { it.copy(isLoading = resource.loading) }
                    is Resource.Success -> {
                        val updatedExperiences = resource.data.map { experience ->
                            val savedLikedExp =
                                _state.value.likedExperiences.find { it.id == experience.id }
                            if (savedLikedExp != null) {
                                experience.copy(
                                    isLiked = true,
                                    likesNo = savedLikedExp.likesNo
                                )
                            } else {
                                experience
                            }
                        }
                        _state.update { it.copy(experiencesSearchResult = updatedExperiences) }
                    }
                }
            }
        }
    }

    fun clearSearchResults() {
        _state.update { it.copy(experiencesSearchResult = emptyList()) }
    }

    fun getRecommendedExperiences() {
        viewModelScope.launch {
            getRecommendedExperiencesUC.invoke().collect { resource ->
                when (resource) {
                    is Resource.Failure -> resource.exception.message?.let {
                        sendEvent(HomeEvent.ShowError(it))
                    }
                    is Resource.Loading -> _state.update { it.copy(isLoading = resource.loading) }
                    is Resource.Success -> {
                        val updatedExperiences = resource.data.map { experience ->
                            val savedLikedExp =
                                _state.value.likedExperiences.find { it.id == experience.id }
                            if (savedLikedExp != null) {
                                experience.copy(
                                    isLiked = true,
                                    likesNo = savedLikedExp.likesNo
                                )
                            } else {
                                experience
                            }
                        }
                        _state.update { it.copy(recommendedExperiences = updatedExperiences) }
                    }
                }
            }
        }
    }

    private fun getMostRecentExperiences() {
        viewModelScope.launch {
            getMostRecentExperiencesUC.invoke().collect { resource ->
                when (resource) {
                    is Resource.Failure -> resource.exception.message?.let {
                        sendEvent(HomeEvent.ShowError(it))
                    }
                    is Resource.Loading -> _state.update { it.copy(isLoading = resource.loading) }
                    is Resource.Success -> {
                        val updatedExperiences = resource.data.map { experience ->
                            val savedLikedExp =
                                _state.value.likedExperiences.find { it.id == experience.id }
                            if (savedLikedExp != null) {
                                experience.copy(
                                    isLiked = true,
                                    likesNo = savedLikedExp.likesNo
                                )
                            } else {
                                experience
                            }
                        }
                        _state.update { it.copy(mostRecentExperiences = updatedExperiences) }
                    }
                }
            }
        }
    }

    private var lastClickTime = 0L
    private val debounceTime = 500L

    fun likeExperience(experience: ExperiencesResponse.Experience) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime < debounceTime) {
            return // Ignore rapid clicks
        }
        lastClickTime = currentTime

        viewModelScope.launch {
            if (state.value.likedExperiences.contains(experience)) {
                return@launch
            }
            likeExperienceUC.invoke(experience.id).collect { resource ->
                when (resource) {
                    is Resource.Failure -> resource.exception.message?.let {
                        sendEvent(HomeEvent.ShowError(it))
                    }

                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val updatedExperience = experience.copy(
                            isLiked = true,
                            likesNo = resource.data
                        )

                        _state.update { currentState ->
                            val updatedLikedExperiences =
                                listOf(updatedExperience) + currentState.likedExperiences

                            val updatedRecommendedExperiences =
                                currentState.recommendedExperiences.map { exp ->
                                    if (exp.id == experience.id) updatedExperience else exp
                                }
                            val updatedMostRecentExperiences =
                                currentState.mostRecentExperiences.map { exp ->
                                    if (exp.id == experience.id) updatedExperience else exp
                                }
                            val updatedSearchResults =
                                currentState.experiencesSearchResult.map { exp ->
                                    if (exp.id == experience.id) updatedExperience else exp
                                }
                            currentState.copy(
                                likedExperiences = updatedLikedExperiences,
                                recommendedExperiences = updatedRecommendedExperiences,
                                mostRecentExperiences = updatedMostRecentExperiences,
                                experiencesSearchResult = updatedSearchResults
                            )
                        }
                        saveLikedExperiencesUC.invoke(_state.value.likedExperiences)
                    }
                }
            }
        }
    }
}