package com.example.aroundegypt.features.home.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.common.data.models.state.Resource
import com.example.aroundegypt.common.presentation.viewmodel.sendEvent
import com.example.aroundegypt.features.home.domain.usecases.GetMostRecentExperiencesUC
import com.example.aroundegypt.features.home.domain.usecases.GetRecommendedExperiencesUC
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
    private val searchExperiencesUC: SearchExperiencesUC
) :
    ViewModel() {
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
                    is Resource.Success -> _state.update { it.copy(experiencesSearchResult = resource.data) }
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
                    is Resource.Success -> _state.update { it.copy(recommendedExperiences = resource.data) }

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
                    is Resource.Success -> _state.update { it.copy(mostRecentExperiences = resource.data) }

                }
            }
        }
    }

    fun likeExperience(id: String) {}
}