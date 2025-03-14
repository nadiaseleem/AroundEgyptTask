package com.example.aroundegypt.features.home.presentation

import com.example.aroundegypt.features.home.domain.models.ExperiencesResponse

data class HomeViewStates(
    val isLoading: Boolean = false,
    val recommendedExperiences: List<ExperiencesResponse.Experience> = emptyList(),
    val mostRecentExperiences: List<ExperiencesResponse.Experience> = emptyList(),
    val experiencesSearchResult: List<ExperiencesResponse.Experience> = emptyList(),
    val errorMessage: String? = null,
    val searchQuery: String = "",
    val isSearchPerformed: Boolean = false,
    val likedExperiences: List<ExperiencesResponse.Experience> = emptyList(),
)