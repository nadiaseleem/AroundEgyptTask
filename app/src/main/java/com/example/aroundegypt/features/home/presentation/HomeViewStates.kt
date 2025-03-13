package com.example.aroundegypt.features.home.presentation

import com.example.aroundegypt.features.home.domain.models.ExperiencesResponse

data class HomeViewStates(
    val isLoading: Boolean = false,
    val recommendedExperiences: List<ExperiencesResponse.Experience> = emptyList(),
    val errorMessage: String? = null,
    val searchQuery: String = ""
)