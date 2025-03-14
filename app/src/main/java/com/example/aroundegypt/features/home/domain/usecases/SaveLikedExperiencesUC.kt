package com.example.aroundegypt.features.home.domain.usecases

import com.example.aroundegypt.features.home.domain.models.Experience
import com.example.aroundegypt.features.home.domain.repository.IExperiencesRepository

class SaveLikedExperiencesUC(private val repository: IExperiencesRepository) {
    suspend operator fun invoke(likedExperiences: List<Experience>) {
        repository.saveLikedExperiences(likedExperiences)
    }
}