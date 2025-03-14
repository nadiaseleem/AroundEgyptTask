package com.example.aroundegypt.features.home.domain.usecases

import com.example.aroundegypt.common.data.models.exception.AroundEgyptException
import com.example.aroundegypt.common.data.models.state.Resource
import com.example.aroundegypt.features.home.domain.models.Experience
import com.example.aroundegypt.features.home.domain.repository.IExperiencesRepository

class GetLikedExperiencesUC(private val repository: IExperiencesRepository) {
    suspend operator fun invoke(): Resource<List<Experience>> {
        return try {
            Resource.Success(repository.getLikedExperiences())
        } catch (e: Exception) {
            val exception =
                if (e is AroundEgyptException) e
                else AroundEgyptException.UnknownException("Unknown error in GetExperienceFromLocalUC: $e")
            Resource.Failure(exception)
        }
    }

}
