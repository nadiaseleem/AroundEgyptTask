package com.example.aroundegypt.common.data.repository.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

@JvmSuppressWildcards
interface AroundEgyptApiService {
    @GET("{path}")
    suspend fun get(
        @Path("path", encoded = true) pathUrl: String,
        @HeaderMap headers: Map<String, Any>,
        @QueryMap queryParams: Map<String, Any>
    ): Response<ResponseBody>

    @POST("{path}")
    suspend fun post(
        @Path("path", encoded = true) pathUrl: String,
        @HeaderMap headers: Map<String, Any>,
        @QueryMap queryParams: Map<String, Any>,
        @Body requestBody: Any
    ): Response<ResponseBody>

}