package com.example.aroundegypt.features.home.data.models.dto


import com.google.gson.annotations.SerializedName

data class ExperiencesResponseDto(
    @SerializedName("data") val experiences: List<ExperienceDto>?,
    @SerializedName("meta") val meta: MetaDto?,
) {
    data class ExperienceDto(
        @SerializedName("address") val address: String?,
        @SerializedName("audio_url") val audioUrl: String?,
        @SerializedName("city") val city: CityDto?,
        @SerializedName("cover_photo") val coverPhoto: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("detailed_description") val detailedDescription: String?,
        @SerializedName("era") val era: EraDto?,
        @SerializedName("experience_tips") val experienceTips: List<String?>?,
        @SerializedName("famous_figure") val famousFigure: String?,
        @SerializedName("founded") val founded: String?,
        @SerializedName("gmap_location") val gmapLocation: GmapLocationDto?,
        @SerializedName("has_audio") val hasAudio: Boolean?,
        @SerializedName("has_video") val hasVideo: Int?,
        @SerializedName("id") val id: String?,
        @SerializedName("is_liked") val isLiked: Boolean?,
        @SerializedName("likes_no") val likesNo: Int?,
        @SerializedName("opening_hours") val openingHours: OpeningHoursDto?,
        @SerializedName("period") val period: PeriodDto?,
        @SerializedName("rating") val rating: Int?,
        @SerializedName("recommended") val recommended: Int?,
        @SerializedName("reviews") val reviews: List<ReviewDto?>?,
        @SerializedName("reviews_no") val reviewsNo: Int?,
        @SerializedName("starting_price") val startingPrice: Int?,
        @SerializedName("tags") val tags: List<TagDto?>?,
        @SerializedName("ticket_prices") val ticketPrices: List<TicketPriceDto?>?,
        @SerializedName("title") val title: String?,
        @SerializedName("tour_html") val tourHtml: String?,
        @SerializedName("translated_opening_hours") val translatedOpeningHours: TranslatedOpeningHoursDto?,
        @SerializedName("views_no") val viewsNo: Int?
    ) {
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

        data class OpeningHoursDto(
            @SerializedName("friday") val friday: List<String?>?,
            @SerializedName("monday") val monday: List<String?>?,
            @SerializedName("saturday") val saturday: List<String?>?,
            @SerializedName("sunday") val sunday: List<String?>?,
            @SerializedName("thursday") val thursday: List<String?>?,
            @SerializedName("tuesday") val tuesday: List<String?>?,
            @SerializedName("wednesday") val wednesday: List<String?>?
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
        ) {
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
        }
    }

    data class MetaDto(
        @SerializedName("code") val code: Int?,
        @SerializedName("errors") val errors: List<String?>?
    )

}