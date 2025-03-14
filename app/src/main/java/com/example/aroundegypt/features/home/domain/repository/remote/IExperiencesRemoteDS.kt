package com.example.aroundegypt.features.home.domain.repository.remote

import com.example.aroundegypt.features.home.data.models.dto.ExperienceDto

interface IExperiencesRemoteDS {
    suspend fun getRecommendedExperiences(): List<ExperienceDto>
    suspend fun getMostRecentExperiences(): List<ExperienceDto>
    suspend fun searchExperiences(query: String): List<ExperienceDto>
    suspend fun likeExperience(id: String): Int
    suspend fun getExperienceById(id: String): ExperienceDto?
}