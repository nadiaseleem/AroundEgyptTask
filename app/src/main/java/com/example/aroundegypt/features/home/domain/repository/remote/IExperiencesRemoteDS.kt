package com.example.aroundegypt.features.home.domain.repository.remote

import com.example.aroundegypt.features.home.data.models.dto.ExperiencesResponseDto

interface IExperiencesRemoteDS {
    suspend fun getRecommendedExperiences(): List<ExperiencesResponseDto.ExperienceDto>
    suspend fun getMostRecentExperiences(): List<ExperiencesResponseDto.ExperienceDto>
}