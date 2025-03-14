package com.example.aroundegypt.features.home.data.models.dto


import com.google.gson.annotations.SerializedName

data class ExperiencesResponseDto(
    @SerializedName("data") val experiences: List<ExperienceDto>?,
    @SerializedName("meta") val meta: MetaDto?,
)


data class CityDto(
    @SerializedName("disable") val disable: Boolean?,
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("top_pick") val topPick: Int?
)

data class EraDto(
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("value") val value: String?
)

data class GmapLocationDto(
    @SerializedName("coordinates") val coordinates: List<Double?>?,
    @SerializedName("type") val type: String?
)

data class PeriodDto(
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("value") val value: String?
)

data class ReviewDto(
    @SerializedName("comment") val comment: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("experience") val experience: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("rating") val rating: Int?
)

data class TagDto(
    @SerializedName("disable") val disable: Boolean?,
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("top_pick") val topPick: Int?
)

data class TicketPriceDto(
    @SerializedName("price") val price: Int?,
    @SerializedName("type") val type: String?
)

data class TranslatedOpeningHoursDto(
    @SerializedName("friday") val friday: FridayDto?,
    @SerializedName("monday") val monday: MondayDto?,
    @SerializedName("saturday") val saturday: SaturdayDto?,
    @SerializedName("sunday") val sunday: SundayDto?,
    @SerializedName("thursday") val thursday: ThursdayDto?,
    @SerializedName("tuesday") val tuesday: TuesdayDto?,
    @SerializedName("wednesday") val wednesday: WednesdayDto?
)

data class FridayDto(
    @SerializedName("day") val day: String?,
    @SerializedName("time") val time: String?
)

data class MondayDto(
    @SerializedName("day") val day: String?,
    @SerializedName("time") val time: String?
)

data class SaturdayDto(
    @SerializedName("day") val day: String?,
    @SerializedName("time") val time: String?
)

data class SundayDto(
    @SerializedName("day") val day: String?,
    @SerializedName("time") val time: String?
)

data class ThursdayDto(
    @SerializedName("day") val day: String?,
    @SerializedName("time") val time: String?
)

data class TuesdayDto(
    @SerializedName("day") val day: String?,
    @SerializedName("time") val time: String?
)

data class WednesdayDto(
    @SerializedName("day") val day: String?,
    @SerializedName("time") val time: String?
)


data class MetaDto(
    @SerializedName("code") val code: Int?,
    @SerializedName("errors") val errors: List<String?>?
)

