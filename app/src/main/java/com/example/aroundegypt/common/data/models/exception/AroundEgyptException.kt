package com.example.aroundegypt.common.data.models.exception

/**
 * A sealed class representing different types of exceptions in the AroundEgypt application.
 * Each exception type is categorized based on the source of the error (Network, Server, Client, Local, etc.).
 *
 * @param message An optional custom error message.
 */
sealed class AroundEgyptException(message: String?) :
    Exception(message) {

    // Network-related exceptions
    sealed class Network(
        message: String?
    ) : AroundEgyptException(message) {

        /**
         * Represents a network timeout exception.
         */
        data class Timeout(
            val errorMessage: String? = "Network timeout. Please try again."
        ) : Network(errorMessage)

        /**
         * Represents an unknown host exception.
         */
        data class UnknownHost(
            val errorMessage: String? = "Please check your internet connection and try again."
        ) : Network(errorMessage)
    }

    // Server-related exceptions
    sealed class Server(message: String?) : AroundEgyptException(message) {

        /**
         * Represents server exceptions that can be retried. (e.g.,503 -> ServiceUnavailable, 504 -> GatewayTimeout)
         */
        data class RetryableServerException(
            val errorMessage: String? = "The server is taking longer to respond. Please wait and try again.",
        ) : Server(errorMessage)

        /**
         * Represents server exceptions that cannot be retried.
         */
        data class NonRetryableServerException(
            val errorMessage: String? = "An unexpected error occurred. Please contact support if the issue persists"
        ) : Server(errorMessage)
    }

    // Client-related exceptions
    sealed class Client(message: String?) :
        AroundEgyptException(message) {

        /**
         * Represents a not found exception (HTTP 404).
         */
        data class NotFound(
            val errorMessage: String? = "The requested resource could not be found."
        ) : Client(errorMessage)

    }

    data class Local(override val message: String?) : AroundEgyptException(message)

    /**
     * Represents an unexpected HTTP exception with an unknown status code.
     */
    data class UnexpectedHttpException(
        val httpErrorCode: Int,
        val errorMessage: String? = null
    ) : AroundEgyptException(errorMessage)

    /**
     * Represents an unclassified exception.
     */
    data class UnknownException(
        val errorMessage: String? = null
    ) : AroundEgyptException(errorMessage)

}
