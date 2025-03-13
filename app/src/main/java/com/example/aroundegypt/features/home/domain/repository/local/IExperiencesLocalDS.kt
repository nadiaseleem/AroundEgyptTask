package com.example.aroundegypt.features.home.domain.repository.local

import com.example.aroundegypt.features.home.data.models.entity.ExperienceEntity

interface IExperiencesLocalDS {
    suspend fun getRecommendedExperiences(): List<ExperienceEntity>
    suspend fun saveRecommendedExperiences(experiences: List<ExperienceEntity>)
}