package com.example.aroundegypt.features.home.domain.repository

import com.example.aroundegypt.features.home.domain.models.ExperiencesResponse

interface IExperiencesRepository {
    suspend fun getRecommendedExperiences(): List<ExperiencesResponse.Experience>
    suspend fun getMostRecentExperiences(): List<ExperiencesResponse.Experience>
    suspend fun searchExperiences(query: String): List<ExperiencesResponse.Experience>

}