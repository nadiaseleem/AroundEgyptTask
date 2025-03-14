package com.example.aroundegypt.features.home.domain.models


data class ExperiencesResponse(
    val experiences: List<Experience>
)

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
    var isLiked: Boolean,
    var likesNo: Int,
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
    val viewsNo: Int
)

data class City(
    val disable: Boolean,
    val id: Int,
    val name: String,
    val topPick: Int
)

data class Era(
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val value: String
)


data class GmapLocation(
    val coordinates: List<Double>,
    val type: String
)

data class Period(
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val value: String
)

data class Review(
    val comment: String,
    val createdAt: String,
    val experience: String,
    val id: String,
    val name: String,
    val rating: Int
)

data class Tag(
    val disable: Boolean,
    val id: Int,
    val name: String,
    val topPick: Int
)

data class TicketPrice(
    val price: Int,
    val type: String
)


