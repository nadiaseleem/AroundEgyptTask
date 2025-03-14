package com.example.aroundegypt.features.home.domain.usecases

import com.example.aroundegypt.features.home.domain.models.ExperiencesResponse
import com.example.aroundegypt.features.home.domain.repository.IExperiencesRepository

class SaveLikedExperiencesUC(private val repository: IExperiencesRepository) {
    suspend operator fun invoke(likedExperiences: List<ExperiencesResponse.Experience>) {
        repository.saveLikedExperiences(likedExperiences)
    }
}