package com.example.aroundegypt.features.home.data.models.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ExperiencesResponseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    // Convert List<ExperienceEntity> to JSON string using type converter
    val experiences: List<ExperienceEntity>
)


@Entity
data class ExperienceEntity(
    @PrimaryKey
    val id: String,
    val address: String,
    val audio_url: String,
    // Embed these objects
    @Embedded(prefix = "city_") val city: CityEntity,
    val cover_photo: String,
    val description: String,
    val detailed_description: String,
    @Embedded(prefix = "era_") val era: EraEntity,
    // Convert List<String> to JSON string
    val experience_tips: List<String>,
    val famous_figure: String,
    val founded: String,
    // Embed this object
    @Embedded(prefix = "location_") val gmap_location: GmapLocationEntity,
    val has_audio: Boolean,
    val has_video: Int,
    val is_liked: Boolean,
    val likes_no: Int,
    // Embed this object
    @Embedded(prefix = "hours_") val opening_hours: OpeningHoursEntity,
    @Embedded(prefix = "period_") val period: PeriodEntity,
    val rating: Int,
    val recommended: Int,
    // Convert List<ReviewEntity> to JSON string
    val reviews: List<ReviewEntity>,
    val reviews_no: Int,
    val starting_price: Int,
    // Convert List<TagEntity> to JSON string
    val tags: List<TagEntity>,
    // Convert List<TicketPriceEntity> to JSON string
    val ticket_prices: List<TicketPriceEntity>,
    val title: String,
    val tour_html: String,
    // Embed this object
    @Embedded(prefix = "trans_") val translated_opening_hours: TranslatedOpeningHoursEntity,
    val views_no: Int
)

// Supporting data classes
data class CityEntity(
    val id: Int,
    val disable: Boolean,
    val name: String,
    val top_pick: Int
)

data class EraEntity(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val value: String
)

data class GmapLocationEntity(
    // Convert List<Double> to JSON string
    val coordinates: List<Double>,
    val type: String
)

data class OpeningHoursEntity(
    // Convert these to JSON strings
    val friday: List<String>,
    val monday: List<String>,
    val saturday: List<String>,
    val sunday: List<String>,
    val thursday: List<String>,
    val tuesday: List<String>,
    val wednesday: List<String>
)

data class PeriodEntity(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val value: String
)

data class ReviewEntity(
    val id: String,
    val comment: String,
    val created_at: String,
    val experience: String,
    val name: String,
    val rating: Int
)

data class TagEntity(
    val id: Int,
    val disable: Boolean,
    val name: String,
    val top_pick: Int
)

data class TicketPriceEntity(
    val price: Int,
    val type: String
)

data class TranslatedOpeningHoursEntity(
    @Embedded(prefix = "friday_") val friday: DayTimeEntity,
    @Embedded(prefix = "monday_") val monday: DayTimeEntity,
    @Embedded(prefix = "saturday_") val saturday: DayTimeEntity,
    @Embedded(prefix = "sunday_") val sunday: DayTimeEntity,
    @Embedded(prefix = "thursday_") val thursday: DayTimeEntity,
    @Embedded(prefix = "tuesday_") val tuesday: DayTimeEntity,
    @Embedded(prefix = "wednesday_") val wednesday: DayTimeEntity
)

data class DayTimeEntity(
    val day: String,
    val time: String
)