package com.example.aroundegypt.features.home.domain.usecases

import com.example.aroundegypt.features.home.domain.models.ExperiencesResponse
import com.example.aroundegypt.features.home.domain.repository.IExperiencesRepository

class GetLikedExperiencesUC(private val repository: IExperiencesRepository) {
    suspend operator fun invoke(): List<ExperiencesResponse.Experience> {
        return repository.getLikedExperiences()
    }
}