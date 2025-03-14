package com.example.aroundegypt.features.home.domain.repository

import com.example.aroundegypt.features.home.domain.models.Experience

interface IExperiencesRepository {
    suspend fun getRecommendedExperiences(): List<Experience>
    suspend fun getMostRecentExperiences(): List<Experience>
    suspend fun searchExperiences(query: String): List<Experience>
    suspend fun likeExperience(id: String): Int
    suspend fun getLikedExperiences(): List<Experience>
    suspend fun saveLikedExperiences(likedExperiences: List<Experience>)
    suspend fun getExperienceById(id: String): Experience?
    suspend fun getExperienceFromLocal(id: String): Experience?
}