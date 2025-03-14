package com.example.aroundegypt.features.home.data.models.dto

import com.google.gson.annotations.SerializedName

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
)