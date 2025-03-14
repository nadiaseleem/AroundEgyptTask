package com.example.aroundegypt.features.home.data.repository.local

import com.example.aroundegypt.common.data.models.exception.AroundEgyptException
import com.example.aroundegypt.common.data.repository.local.database.ExperiencesDao
import com.example.aroundegypt.features.home.data.models.entity.ExperienceEntity
import com.example.aroundegypt.features.home.domain.repository.local.IExperiencesLocalDS

class ExperiencesLocalDS(private val experiencesDao: ExperiencesDao) : IExperiencesLocalDS {
    override suspend fun getRecommendedExperiences(): List<ExperienceEntity> {
        try {
            return experiencesDao.getRecommendedExperiences()
        } catch (e: Exception) {
            throw AroundEgyptException.Local(message = e.message)
        }
    }

    override suspend fun saveExperiences(experiences: List<ExperienceEntity>) {
        try {
            return experiencesDao.saveExperiences(experiences)
        } catch (e: Exception) {
            throw AroundEgyptException.Local(message = e.message)
        }
    }

    override suspend fun getMostRecentExperiences(): List<ExperienceEntity> {
        try {
            return experiencesDao.getMostRecentExperiences()
        } catch (e: Exception) {
            throw AroundEgyptException.Local(message = e.message)
        }
    }

    override suspend fun searchExperiences(query: String): List<ExperienceEntity> {
        try {
            return experiencesDao.searchExperiences(query)
        } catch (e: Exception) {
            throw AroundEgyptException.Local(message = e.message)
        }
    }

    override suspend fun likeExperience(id: String, updatedLikeNo: Int) {
        try {
            return experiencesDao.likeExperience(id, updatedLikeNo)
        } catch (e: Exception) {
            throw AroundEgyptException.Local(message = e.message)
        }
    }

    override suspend fun getLikedExperiences(): List<ExperienceEntity> {
        return experiencesDao.getLikedExperiences()
    }

    override suspend fun saveLikedExperiences(likedExperiences: List<ExperienceEntity>) {
        experiencesDao.saveExperiences(likedExperiences)
    }

    override suspend fun getExperienceById(id: String): ExperienceEntity? {
        try {
            return experiencesDao.getExperienceById(id)
        } catch (e: Exception) {
            throw AroundEgyptException.Local(message = e.message)
        }
    }
}