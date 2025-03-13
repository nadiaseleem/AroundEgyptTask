package com.example.aroundegypt.features.home.domain.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExperiencesResponse(
    val experiences: List<Experience>
) : Parcelable {
    @Parcelize
    data class Experience(
        val address: String,
        val audioUrl: String,
        val city: City,
        val coverPhoto: String,
        val description: String,
        val detailedDescription: String,
        val era: Era,
        val experienceTips: List<String>,
        val famousFigure: String,
        val founded: String,
        val gmapLocation: GmapLocation,
        val hasAudio: Boolean,
        val hasVideo: Int,
        val id: String,
        val isLiked: Boolean,
        val likesNo: Int,
        val openingHours: OpeningHours,
        val period: Period,
        val rating: Int,
        val recommended: Int,
        val reviews: List<Review>,
        val reviewsNo: Int,
        val startingPrice: Int,
        val tags: List<Tag>,
        val ticketPrices: List<TicketPrice>,
        val title: String,
        val tourHtml: String,
        val translatedOpeningHours: TranslatedOpeningHours,
        val viewsNo: Int
    ) : Parcelable {
        @Parcelize
        data class City(
            val disable: Boolean,
            val id: Int,
            val name: String,
            val topPick: Int
        ) : Parcelable

        @Parcelize
        data class Era(
            val createdAt: String,
            val id: String,
            val updatedAt: String,
            val value: String
        ) : Parcelable

        @Parcelize
        data class GmapLocation(
            val coordinates: List<Double>,
            val type: String
        ) : Parcelable

        @Parcelize
        data class OpeningHours(
            val friday: List<String>,
            val monday: List<String>,
            val saturday: List<String>,
            val sunday: List<String>,
            val thursday: List<String>,
            val tuesday: List<String>,
            val wednesday: List<String>
        ) : Parcelable

        @Parcelize
        data class Period(
            val createdAt: String,
            val id: String,
            val updatedAt: String,
            val value: String
        ) : Parcelable

        @Parcelize
        data class Review(
            val comment: String,
            val createdAt: String,
            val experience: String,
            val id: String,
            val name: String,
            val rating: Int
        ) : Parcelable

        @Parcelize
        data class Tag(
            val disable: Boolean,
            val id: Int,
            val name: String,
            val topPick: Int
        ) : Parcelable

        @Parcelize
        data class TicketPrice(
            val price: Int,
            val type: String
        ) : Parcelable

        @Parcelize
        data class TranslatedOpeningHours(
            val friday: Friday,
            val monday: Monday,
            val saturday: Saturday,
            val sunday: Sunday,
            val thursday: Thursday,
            val tuesday: Tuesday,
            val wednesday: Wednesday
        ) : Parcelable {
            @Parcelize
            data class Friday(
                val day: String,
                val time: String
            ) : Parcelable

            @Parcelize
            data class Monday(
                val day: String,
                val time: String
            ) : Parcelable

            @Parcelize
            data class Saturday(
                val day: String,
                val time: String
            ) : Parcelable

            @Parcelize
            data class Sunday(
                val day: String,
                val time: String
            ) : Parcelable

            @Parcelize
            data class Thursday(
                val day: String,
                val time: String
            ) : Parcelable

            @Parcelize
            data class Tuesday(
                val day: String,
                val time: String
            ) : Parcelable

            @Parcelize
            data class Wednesday(
                val day: String,
                val time: String
            ) : Parcelable
        }
    }
}