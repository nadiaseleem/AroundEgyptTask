package com.example.aroundegypt.features.home.domain.repository.local

import com.example.aroundegypt.features.home.data.models.entity.ExperienceEntity

interface IExperiencesLocalDS {
    suspend fun getRecommendedExperiences(): List<ExperienceEntity>
    suspend fun saveExperiences(experiences: List<ExperienceEntity>)
    suspend fun getMostRecentExperiences(): List<ExperienceEntity>
    suspend fun searchExperiences(query: String): List<ExperienceEntity>
    suspend fun likeExperience(id: String, updatedLikeNo: Int)
    suspend fun getLikedExperiences(): List<ExperienceEntity>
    suspend fun saveLikedExperiences(likedExperiences: List<ExperienceEntity>)
    suspend fun getExperienceById(id: String): ExperienceEntity?

}