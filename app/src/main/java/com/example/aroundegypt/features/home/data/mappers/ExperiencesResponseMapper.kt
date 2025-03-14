package com.example.aroundegypt.features.home.data.mappers

import com.example.aroundegypt.common.data.mapper.Mapper
import com.example.aroundegypt.features.home.data.models.dto.ExperiencesResponseDto
import com.example.aroundegypt.features.home.data.models.entity.CityEntity
import com.example.aroundegypt.features.home.data.models.entity.EraEntity
import com.example.aroundegypt.features.home.data.models.entity.ExperienceEntity
import com.example.aroundegypt.features.home.data.models.entity.ExperiencesResponseEntity
import com.example.aroundegypt.features.home.data.models.entity.GmapLocationEntity
import com.example.aroundegypt.features.home.data.models.entity.PeriodEntity
import com.example.aroundegypt.features.home.data.models.entity.ReviewEntity
import com.example.aroundegypt.features.home.data.models.entity.TagEntity
import com.example.aroundegypt.features.home.data.models.entity.TicketPriceEntity
import com.example.aroundegypt.features.home.domain.models.City
import com.example.aroundegypt.features.home.domain.models.Era
import com.example.aroundegypt.features.home.domain.models.Experience
import com.example.aroundegypt.features.home.domain.models.ExperiencesResponse
import com.example.aroundegypt.features.home.domain.models.GmapLocation
import com.example.aroundegypt.features.home.domain.models.Period
import com.example.aroundegypt.features.home.domain.models.Review
import com.example.aroundegypt.features.home.domain.models.Tag
import com.example.aroundegypt.features.home.domain.models.TicketPrice


internal object ExperiencesResponseMapper :
    Mapper<ExperiencesResponseDto, ExperiencesResponse, ExperiencesResponseEntity>() {

    override fun dtoToDomain(model: ExperiencesResponseDto): ExperiencesResponse {
        return ExperiencesResponse(
            experiences = model.experiences?.map { experienceDto ->
                Experience(
                    address = experienceDto.address ?: "",
                    audioUrl = experienceDto.audioUrl ?: "",
                    city = City(
                        disable = experienceDto.city?.disable ?: false,
                        id = experienceDto.city?.id ?: 0,
                        name = experienceDto.city?.name ?: "",
                        topPick = experienceDto.city?.topPick ?: 0
                    ),
                    coverPhoto = experienceDto.coverPhoto ?: "",
                    description = experienceDto.description ?: "",
                    detailedDescription = experienceDto.detailedDescription ?: "",
                    era = Era(
                        createdAt = experienceDto.era?.createdAt ?: "",
                        id = experienceDto.era?.id ?: "",
                        updatedAt = experienceDto.era?.updatedAt ?: "",
                        value = experienceDto.era?.value ?: ""
                    ),
                    experienceTips = experienceDto.experienceTips?.filterNotNull() ?: emptyList(),
                    famousFigure = experienceDto.famousFigure ?: "",
                    founded = experienceDto.founded ?: "",
                    gmapLocation = GmapLocation(
                        coordinates = experienceDto.gmapLocation?.coordinates?.filterNotNull()
                            ?: emptyList(),
                        type = experienceDto.gmapLocation?.type ?: ""
                    ),
                    hasAudio = experienceDto.hasAudio ?: false,
                    hasVideo = experienceDto.hasVideo ?: 0,
                    id = experienceDto.id ?: "",
                    isLiked = experienceDto.isLiked ?: false,
                    likesNo = experienceDto.likesNo ?: 0,
                    period = Period(
                        createdAt = experienceDto.period?.createdAt ?: "",
                        id = experienceDto.period?.id ?: "",
                        updatedAt = experienceDto.period?.updatedAt ?: "",
                        value = experienceDto.period?.value ?: ""
                    ),
                    rating = experienceDto.rating ?: 0,
                    recommended = experienceDto.recommended ?: 0,
                    reviews = experienceDto.reviews?.filterNotNull()?.map { reviewDto ->
                        Review(
                            comment = reviewDto.comment ?: "",
                            createdAt = reviewDto.createdAt ?: "",
                            experience = reviewDto.experience ?: "",
                            id = reviewDto.id ?: "",
                            name = reviewDto.name ?: "",
                            rating = reviewDto.rating ?: 0
                        )
                    } ?: emptyList(),
                    reviewsNo = experienceDto.reviewsNo ?: 0,
                    startingPrice = experienceDto.startingPrice ?: 0,
                    tags = experienceDto.tags?.filterNotNull()?.map { tagDto ->
                        Tag(
                            disable = tagDto.disable ?: false,
                            id = tagDto.id ?: 0,
                            name = tagDto.name ?: "",
                            topPick = tagDto.topPick ?: 0
                        )
                    } ?: emptyList(),
                    ticketPrices = experienceDto.ticketPrices?.filterNotNull()
                        ?.map { ticketPriceDto ->
                            TicketPrice(
                                price = ticketPriceDto.price ?: 0,
                                type = ticketPriceDto.type ?: ""
                            )
                        } ?: emptyList(),
                    title = experienceDto.title ?: "",
                    tourHtml = experienceDto.tourHtml ?: "",
                    viewsNo = experienceDto.viewsNo ?: 0
                )
            } ?: emptyList()
        )
    }

    override fun entityToDomain(model: ExperiencesResponseEntity): ExperiencesResponse {
        return ExperiencesResponse(
            experiences = model.experiences.map { experienceEntity ->
                Experience(
                    address = experienceEntity.address,
                    audioUrl = experienceEntity.audio_url,
                    city = City(
                        disable = experienceEntity.city.disable,
                        id = experienceEntity.city.id,
                        name = experienceEntity.city.name,
                        topPick = experienceEntity.city.top_pick
                    ),
                    coverPhoto = experienceEntity.cover_photo,
                    description = experienceEntity.description,
                    detailedDescription = experienceEntity.detailed_description,
                    era = Era(
                        createdAt = experienceEntity.era.created_at,
                        id = experienceEntity.era.id,
                        updatedAt = experienceEntity.era.updated_at,
                        value = experienceEntity.era.value
                    ),
                    experienceTips = experienceEntity.experience_tips,
                    famousFigure = experienceEntity.famous_figure,
                    founded = experienceEntity.founded,
                    gmapLocation = GmapLocation(
                        coordinates = experienceEntity.gmap_location.coordinates,
                        type = experienceEntity.gmap_location.type
                    ),
                    hasAudio = experienceEntity.has_audio,
                    hasVideo = experienceEntity.has_video,
                    id = experienceEntity.id,
                    isLiked = experienceEntity.is_liked,
                    likesNo = experienceEntity.likes_no,
                    period = Period(
                        createdAt = experienceEntity.period.created_at,
                        id = experienceEntity.period.id,
                        updatedAt = experienceEntity.period.updated_at,
                        value = experienceEntity.period.value
                    ),
                    rating = experienceEntity.rating,
                    recommended = experienceEntity.recommended,
                    reviews = experienceEntity.reviews.map { reviewEntity ->
                        Review(
                            comment = reviewEntity.comment,
                            createdAt = reviewEntity.created_at,
                            experience = reviewEntity.experience,
                            id = reviewEntity.id,
                            name = reviewEntity.name,
                            rating = reviewEntity.rating
                        )
                    },
                    reviewsNo = experienceEntity.reviews_no,
                    startingPrice = experienceEntity.starting_price,
                    tags = experienceEntity.tags.map { tagEntity ->
                        Tag(
                            disable = tagEntity.disable,
                            id = tagEntity.id,
                            name = tagEntity.name,
                            topPick = tagEntity.top_pick
                        )
                    },
                    ticketPrices = experienceEntity.ticket_prices.map { ticketPriceEntity ->
                        TicketPrice(
                            price = ticketPriceEntity.price,
                            type = ticketPriceEntity.type
                        )
                    },
                    title = experienceEntity.title,
                    tourHtml = experienceEntity.tour_html,
                    viewsNo = experienceEntity.views_no
                )
            }
        )
    }

    override fun domainToEntity(model: ExperiencesResponse): ExperiencesResponseEntity {
        return ExperiencesResponseEntity(
            experiences = model.experiences.map { experience ->
                ExperienceEntity(
                    id = experience.id,
                    address = experience.address,
                    audio_url = experience.audioUrl,
                    city = CityEntity(
                        disable = experience.city.disable,
                        id = experience.city.id,
                        name = experience.city.name,
                        top_pick = experience.city.topPick
                    ),
                    cover_photo = experience.coverPhoto,
                    description = experience.description,
                    detailed_description = experience.detailedDescription,
                    era = EraEntity(
                        created_at = experience.era.createdAt,
                        id = experience.era.id,
                        updated_at = experience.era.updatedAt,
                        value = experience.era.value
                    ),
                    experience_tips = experience.experienceTips,
                    famous_figure = experience.famousFigure,
                    founded = experience.founded,
                    gmap_location = GmapLocationEntity(
                        coordinates = experience.gmapLocation.coordinates,
                        type = experience.gmapLocation.type
                    ),
                    has_audio = experience.hasAudio,
                    has_video = experience.hasVideo,
                    is_liked = experience.isLiked,
                    likes_no = experience.likesNo,
                    period = PeriodEntity(
                        created_at = experience.period.createdAt,
                        id = experience.period.id,
                        updated_at = experience.period.updatedAt,
                        value = experience.period.value
                    ),
                    rating = experience.rating,
                    recommended = experience.recommended,
                    reviews = experience.reviews.map { review ->
                        ReviewEntity(
                            comment = review.comment,
                            created_at = review.createdAt,
                            experience = review.experience,
                            id = review.id,
                            name = review.name,
                            rating = review.rating
                        )
                    },
                    reviews_no = experience.reviewsNo,
                    starting_price = experience.startingPrice,
                    tags = experience.tags.map { tag ->
                        TagEntity(
                            disable = tag.disable,
                            id = tag.id,
                            name = tag.name,
                            top_pick = tag.topPick
                        )
                    },
                    ticket_prices = experience.ticketPrices.map { ticketPrice ->
                        TicketPriceEntity(
                            price = ticketPrice.price,
                            type = ticketPrice.type
                        )
                    },
                    title = experience.title,
                    tour_html = experience.tourHtml,
                    views_no = experience.viewsNo
                )
            }
        )
    }
}