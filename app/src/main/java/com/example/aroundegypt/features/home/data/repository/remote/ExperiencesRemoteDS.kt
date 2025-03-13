package com.example.aroundegypt.features.home.data.repository.remote

import com.example.aroundegypt.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.aroundegypt.features.home.data.models.dto.ExperiencesResponseDto
import com.example.aroundegypt.features.home.domain.repository.remote.IExperiencesRemoteDS

class ExperiencesRemoteDS(private val networkProvider: IRestApiNetworkProvider) :
    IExperiencesRemoteDS {
    override suspend fun getRecommendedExperiences(): List<ExperiencesResponseDto.ExperienceDto> {
        return networkProvider.get<ExperiencesResponseDto>(
            pathUrl = "v2/experiences",
            queryParams = mapOf("filter[recommended]" to true),
            responseType = ExperiencesResponseDto::class.java
        ).experiences ?: listOf()
    }

    override suspend fun getMostRecentExperiences(): List<ExperiencesResponseDto.ExperienceDto> {
        return networkProvider.get<ExperiencesResponseDto>(
            pathUrl = "v2/experiences",
            responseType = ExperiencesResponseDto::class.java
        ).experiences ?: listOf()
    }
}