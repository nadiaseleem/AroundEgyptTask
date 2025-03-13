package com.example.aroundegypt.common.data.models.state

import com.example.aroundegypt.common.data.models.exception.AroundEgyptException


/**
 * Sealed class representing the result of a resource operation, which can either be a success with data or a failure with an exception.
 *
 * @param T The type of the data in case of success.
 */
sealed class Resource<out T> {

    /**
     * Represents a loading state.
     */
    data class Loading(val loading: Boolean = true) : Resource<Nothing>()

    /**
     * Represents a successful operation with data of type [T].
     */
    data class Success<out T>(val data: T) : Resource<T>()

    /**
     * Represents a failed operation with an exception.
     */
    data class Failure(val exception: AroundEgyptException) : Resource<Nothing>()

    /**
     * Checks if the resource is a success.
     */
    val isSuccess: Boolean get() = this is Success

    /**
     * Checks if the resource is a failure.
     */
    val isFailure: Boolean get() = this is Failure
}


