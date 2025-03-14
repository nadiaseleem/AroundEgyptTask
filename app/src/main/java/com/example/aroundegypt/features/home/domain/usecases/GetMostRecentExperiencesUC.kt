package com.example.aroundegypt.features.home.domain.usecases

import com.example.aroundegypt.common.data.models.exception.AroundEgyptException
import com.example.aroundegypt.common.data.models.state.Resource
import com.example.aroundegypt.features.home.domain.repository.IExperiencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class GetMostRecentExperiencesUC(private val repository: IExperiencesRepository) {
    operator fun invoke() = flow {
        emit(Resource.Loading())
        val experiences = repository.getMostRecentExperiences()
        emit(Resource.Success(experiences))
    }.catch { throwable ->
        val exception =
            when (throwable) {
                is AroundEgyptException -> throwable
                is UnknownHostException -> AroundEgyptException.Network.UnknownHost(errorMessage = throwable.localizedMessage)
                is SocketTimeoutException -> AroundEgyptException.Network.Timeout(errorMessage = throwable.localizedMessage)
                else -> AroundEgyptException.UnknownException("Unknown error in GetMostRecentExperiencesUC: $throwable")
            }
        emit(Resource.Failure(exception))
    }.onCompletion {
        emit(Resource.Loading(false))
    }.flowOn(Dispatchers.IO)
}