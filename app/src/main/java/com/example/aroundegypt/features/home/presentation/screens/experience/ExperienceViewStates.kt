package com.example.aroundegypt.features.home.presentation.screens.experience

import com.example.aroundegypt.features.home.domain.models.Experience

data class ExperienceViewStates(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val experienceFromLocal: Experience? = null,
    val selectedExperience: Experience? = null,
)