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

    override suspend fun saveRecommendedExperiences(experiences: List<ExperienceEntity>) {
        try {
            return experiencesDao.saveRecommendedExperiences(experiences)
        } catch (e: Exception) {
            throw AroundEgyptException.Local(message = e.message)
        }
    }
}