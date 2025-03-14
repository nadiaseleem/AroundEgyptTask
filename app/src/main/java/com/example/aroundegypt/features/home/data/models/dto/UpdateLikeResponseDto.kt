package com.example.aroundegypt.features.home.data.models.dto


import com.google.gson.annotations.SerializedName

data class UpdateLikeResponseDto(
    @SerializedName("meta") val meta: Meta,
    @SerializedName("data") val data: Int,
    @SerializedName("pagination") val pagination: Pagination
)

data class Meta(
    @SerializedName("code") val code: Int,
    @SerializedName("errors") val errors: List<String>
)

data object Pagination