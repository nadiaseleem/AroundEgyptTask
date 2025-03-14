package com.example.aroundegypt.features.home.presentation.screens.home

import com.example.aroundegypt.features.home.domain.models.Experience

data class HomeViewStates(
    val isLoading: Boolean = false,
    val recommendedExperiences: List<Experience> = emptyList(),
    val mostRecentExperiences: List<Experience> = emptyList(),
    val experiencesSearchResult: List<Experience> = emptyList(),
    val errorMessage: String? = null,
    val searchQuery: String = "",
    val isSearchPerformed: Boolean = false,
    val likedExperiences: List<Experience> = emptyList(),
)