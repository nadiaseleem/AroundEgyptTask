package com.example.aroundegypt.features.home.domain.usecases

import com.example.aroundegypt.features.home.domain.models.Experience
import com.example.aroundegypt.features.home.domain.repository.IExperiencesRepository

class GetLikedExperiencesUC(private val repository: IExperiencesRepository) {
    suspend operator fun invoke(): List<Experience> {
        return repository.getLikedExperiences()
    }
}