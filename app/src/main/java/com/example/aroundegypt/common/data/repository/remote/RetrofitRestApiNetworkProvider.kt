package com.example.aroundegypt.common.data.repository.remote

import com.example.aroundegypt.android.extensions.fromJson
import com.example.aroundegypt.common.data.models.exception.AroundEgyptException
import com.example.aroundegypt.common.domain.repository.remote.IRestApiNetworkProvider
import okhttp3.ResponseBody
import retrofit2.Response
import java.lang.reflect.Type
import java.net.HttpURLConnection

class RetrofitRestApiNetworkProvider(private val apiService: AroundEgyptApiService) :
    IRestApiNetworkProvider {
    override suspend fun <ResponseBody> get(
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?,
        responseType: Type
    ): ResponseBody {
        val response = apiService.get(
            pathUrl = pathUrl,
            headers = headers ?: mapOf(),
            queryParams = queryParams ?: mapOf()
        )
        return handleResponse(response, responseType)
    }

    override suspend fun <RequestBody, ResponseBody> post(
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?,
        requestBody: RequestBody?,
        responseType: Type
    ): ResponseBody {
        val response = apiService.post(
            pathUrl = pathUrl,
            headers = headers ?: mapOf(),
            queryParams = queryParams ?: mapOf(),
            requestBody = requestBody ?: Unit
        )
        return handleResponse(response, responseType)
    }

    private fun <ResponseType> handleResponse(
        response: Response<ResponseBody>,
        responseType: Type
    ): ResponseType {
        return if (response.isSuccessful) {
            val responseBody =
                response.body() ?: throw AroundEgyptException.UnexpectedHttpException(
                    httpErrorCode = response.code(),
                    errorMessage = "Response body is null"
                )
            responseBody.string().fromJson(responseType)
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = errorBody ?: "Unknown error"
            when (response.code()) {

                HttpURLConnection.HTTP_NOT_FOUND -> throw AroundEgyptException.Client.NotFound(
                    errorMessage = errorMessage
                )

                HttpURLConnection.HTTP_UNAVAILABLE, HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> throw AroundEgyptException.Server.RetryableServerException(
                    errorMessage = errorMessage
                )

                in 500..599 -> throw AroundEgyptException.Server.NonRetryableServerException(
                    errorMessage = errorMessage
                )

                else -> throw AroundEgyptException.UnexpectedHttpException(
                    httpErrorCode = response.code(),
                    errorMessage = errorMessage
                )
            }
        }
    }
}