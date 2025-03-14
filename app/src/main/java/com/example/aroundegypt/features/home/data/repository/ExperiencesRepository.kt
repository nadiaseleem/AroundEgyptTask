package com.example.aroundegypt.features.home.data.repository

import com.example.aroundegypt.common.domain.internet_connection_handler.IInternetConnectivityChecker
import com.example.aroundegypt.features.home.data.mappers.ExperienceMapper
import com.example.aroundegypt.features.home.domain.models.ExperiencesResponse
import com.example.aroundegypt.features.home.domain.repository.IExperiencesRepository
import com.example.aroundegypt.features.home.domain.repository.local.IExperiencesLocalDS
import com.example.aroundegypt.features.home.domain.repository.remote.IExperiencesRemoteDS

class ExperiencesRepository(
    private val remoteDS: IExperiencesRemoteDS,
    private val localDS: IExperiencesLocalDS,
    private val connectivityChecker: IInternetConnectivityChecker
) : IExperiencesRepository {
    override suspend fun getRecommendedExperiences(): List<ExperiencesResponse.Experience> {
        if (connectivityChecker.isInternetAvailable()) {
            val experiencesDto = remoteDS.getRecommendedExperiences()
            val experiences = experiencesDto.map { ExperienceMapper.dtoToDomain(it) }
            val experiencesEntity = experiences.map { ExperienceMapper.domainToEntity(it) }
            localDS.saveExperiences(experiencesEntity)
            return experiences
        } else {
            return localDS.getRecommendedExperiences().map { ExperienceMapper.entityToDomain(it) }
        }
    }

    override suspend fun getMostRecentExperiences(): List<ExperiencesResponse.Experience> {
        if (connectivityChecker.isInternetAvailable()) {
            val experiencesDto = remoteDS.getMostRecentExperiences()
            val experiences = experiencesDto.map { ExperienceMapper.dtoToDomain(it) }
            val experiencesEntity = experiences.map { ExperienceMapper.domainToEntity(it) }
            localDS.saveExperiences(
                experiencesEntity
            )
            return experiences
        } else {
            return localDS.getMostRecentExperiences().map { ExperienceMapper.entityToDomain(it) }
        }
    }

    override suspend fun searchExperiences(query: String): List<ExperiencesResponse.Experience> {
        if (connectivityChecker.isInternetAvailable()) {
            val experiencesDto = remoteDS.searchExperiences(query)
            val experiences = experiencesDto.map { ExperienceMapper.dtoToDomain(it) }
            val experiencesEntity = experiences.map { ExperienceMapper.domainToEntity(it) }
            localDS.saveExperiences(
                experiencesEntity
            )
            return experiences
        } else {
            return localDS.searchExperiences(query).map { ExperienceMapper.entityToDomain(it) }
        }
    }

    override suspend fun likeExperience(id: String): Int {
        val updatedLikeNo = remoteDS.likeExperience(id)
        localDS.likeExperience(id, updatedLikeNo)
        return updatedLikeNo

    }

    override suspend fun getLikedExperiences(): List<ExperiencesResponse.Experience> {
        return localDS.getLikedExperiences().map { ExperienceMapper.entityToDomain(it) }
    }

    override suspend fun saveLikedExperiences(likedExperiences: List<ExperiencesResponse.Experience>) {
        val likedExperiencesEntities = likedExperiences.map { ExperienceMapper.domainToEntity(it) }
        localDS.saveLikedExperiences(likedExperiencesEntities)
    }
}