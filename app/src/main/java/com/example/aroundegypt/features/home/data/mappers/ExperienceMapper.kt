package com.example.aroundegypt.features.home.data.mappers

import com.example.aroundegypt.common.data.mapper.Mapper
import com.example.aroundegypt.features.home.data.models.dto.ExperienceDto
import com.example.aroundegypt.features.home.data.models.entity.CityEntity
import com.example.aroundegypt.features.home.data.models.entity.EraEntity
import com.example.aroundegypt.features.home.data.models.entity.ExperienceEntity
import com.example.aroundegypt.features.home.data.models.entity.GmapLocationEntity
import com.example.aroundegypt.features.home.data.models.entity.PeriodEntity
import com.example.aroundegypt.features.home.data.models.entity.ReviewEntity
import com.example.aroundegypt.features.home.data.models.entity.TagEntity
import com.example.aroundegypt.features.home.data.models.entity.TicketPriceEntity
import com.example.aroundegypt.features.home.domain.models.City
import com.example.aroundegypt.features.home.domain.models.Era
import com.example.aroundegypt.features.home.domain.models.Experience
import com.example.aroundegypt.features.home.domain.models.GmapLocation
import com.example.aroundegypt.features.home.domain.models.Period
import com.example.aroundegypt.features.home.domain.models.Review
import com.example.aroundegypt.features.home.domain.models.Tag
import com.example.aroundegypt.features.home.domain.models.TicketPrice

internal object ExperienceMapper :
    Mapper<ExperienceDto, Experience, ExperienceEntity>() {

    override fun dtoToDomain(model: ExperienceDto): Experience {
        ExperiencesResponseMapper
        return Experience(
            address = model.address ?: "",
            audioUrl = model.audioUrl ?: "",
            city = City(
                disable = model.city?.disable ?: false,
                id = model.city?.id ?: 0,
                name = model.city?.name ?: "",
                topPick = model.city?.topPick ?: 0
            ),
            coverPhoto = model.coverPhoto ?: "",
            description = model.description ?: "",
            detailedDescription = model.detailedDescription ?: "",
            era = Era(
                createdAt = model.era?.createdAt ?: "",
                id = model.era?.id ?: "",
                updatedAt = model.era?.updatedAt ?: "",
                value = model.era?.value ?: ""
            ),
            experienceTips = model.experienceTips?.filterNotNull() ?: emptyList(),
            famousFigure = model.famousFigure ?: "",
            founded = model.founded ?: "",
            gmapLocation = GmapLocation(
                coordinates = model.gmapLocation?.coordinates?.filterNotNull() ?: emptyList(),
                type = model.gmapLocation?.type ?: ""
            ),
            hasAudio = model.hasAudio ?: false,
            hasVideo = model.hasVideo ?: 0,
            id = model.id ?: "",
            isLiked = model.isLiked ?: false,
            likesNo = model.likesNo ?: 0,
            period = Period(
                createdAt = model.period?.createdAt ?: "",
                id = model.period?.id ?: "",
                updatedAt = model.period?.updatedAt ?: "",
                value = model.period?.value ?: ""
            ),
            rating = model.rating ?: 0,
            recommended = model.recommended ?: 0,
            reviews = model.reviews?.filterNotNull()?.map { reviewDto ->
                Review(
                    comment = reviewDto.comment ?: "",
                    createdAt = reviewDto.createdAt ?: "",
                    experience = reviewDto.experience ?: "",
                    id = reviewDto.id ?: "",
                    name = reviewDto.name ?: "",
                    rating = reviewDto.rating ?: 0
                )
            } ?: emptyList(),
            reviewsNo = model.reviewsNo ?: 0,
            startingPrice = model.startingPrice ?: 0,
            tags = model.tags?.filterNotNull()?.map { tagDto ->
                Tag(
                    disable = tagDto.disable ?: false,
                    id = tagDto.id ?: 0,
                    name = tagDto.name ?: "",
                    topPick = tagDto.topPick ?: 0
                )
            } ?: emptyList(),
            ticketPrices = model.ticketPrices?.filterNotNull()?.map { ticketPriceDto ->
                TicketPrice(
                    price = ticketPriceDto.price ?: 0,
                    type = ticketPriceDto.type ?: ""
                )
            } ?: emptyList(),
            title = model.title ?: "",
            tourHtml = model.tourHtml ?: "",
            viewsNo = model.viewsNo ?: 0
        )
    }

    override fun entityToDomain(model: ExperienceEntity): Experience {
        return Experience(
            address = model.address,
            audioUrl = model.audio_url,
            city = City(
                disable = model.city.disable,
                id = model.city.id,
                name = model.city.name,
                topPick = model.city.top_pick
            ),
            coverPhoto = model.cover_photo,
            description = model.description,
            detailedDescription = model.detailed_description,
            era = Era(
                createdAt = model.era.created_at,
                id = model.era.id,
                updatedAt = model.era.updated_at,
                value = model.era.value
            ),
            experienceTips = model.experience_tips,
            famousFigure = model.famous_figure,
            founded = model.founded,
            gmapLocation = GmapLocation(
                coordinates = model.gmap_location.coordinates,
                type = model.gmap_location.type
            ),
            hasAudio = model.has_audio,
            hasVideo = model.has_video,
            id = model.id,
            isLiked = model.is_liked,
            likesNo = model.likes_no,
            period = Period(
                createdAt = model.period.created_at,
                id = model.period.id,
                updatedAt = model.period.updated_at,
                value = model.period.value
            ),
            rating = model.rating,
            recommended = model.recommended,
            reviews = model.reviews.map { reviewEntity ->
                Review(
                    comment = reviewEntity.comment,
                    createdAt = reviewEntity.created_at,
                    experience = reviewEntity.experience,
                    id = reviewEntity.id,
                    name = reviewEntity.name,
                    rating = reviewEntity.rating
                )
            },
            reviewsNo = model.reviews_no,
            startingPrice = model.starting_price,
            tags = model.tags.map { tagEntity ->
                Tag(
                    disable = tagEntity.disable,
                    id = tagEntity.id,
                    name = tagEntity.name,
                    topPick = tagEntity.top_pick
                )
            },
            ticketPrices = model.ticket_prices.map { ticketPriceEntity ->
                TicketPrice(
                    price = ticketPriceEntity.price,
                    type = ticketPriceEntity.type
                )
            },
            title = model.title,
            tourHtml = model.tour_html,
            viewsNo = model.views_no
        )
    }

    override fun domainToEntity(model: Experience): ExperienceEntity {
        return ExperienceEntity(
            id = model.id,
            address = model.address,
            audio_url = model.audioUrl,
            city = CityEntity(
                disable = model.city.disable,
                id = model.city.id,
                name = model.city.name,
                top_pick = model.city.topPick
            ),
            cover_photo = model.coverPhoto,
            description = model.description,
            detailed_description = model.detailedDescription,
            era = EraEntity(
                created_at = model.era.createdAt,
                id = model.era.id,
                updated_at = model.era.updatedAt,
                value = model.era.value
            ),
            experience_tips = model.experienceTips,
            famous_figure = model.famousFigure,
            founded = model.founded,
            gmap_location = GmapLocationEntity(
                coordinates = model.gmapLocation.coordinates,
                type = model.gmapLocation.type
            ),
            has_audio = model.hasAudio,
            has_video = model.hasVideo,
            is_liked = model.isLiked,
            likes_no = model.likesNo,
            period = PeriodEntity(
                created_at = model.period.createdAt,
                id = model.period.id,
                updated_at = model.period.updatedAt,
                value = model.period.value
            ),
            rating = model.rating,
            recommended = model.recommended,
            reviews = model.reviews.map { review ->
                ReviewEntity(
                    comment = review.comment,
                    created_at = review.createdAt,
                    experience = review.experience,
                    id = review.id,
                    name = review.name,
                    rating = review.rating
                )
            },
            reviews_no = model.reviewsNo,
            starting_price = model.startingPrice,
            tags = model.tags.map { tag ->
                TagEntity(
                    disable = tag.disable,
                    id = tag.id,
                    name = tag.name,
                    top_pick = tag.topPick
                )
            },
            ticket_prices = model.ticketPrices.map { ticketPrice ->
                TicketPriceEntity(
                    price = ticketPrice.price,
                    type = ticketPrice.type
                )
            },
            title = model.title,
            tour_html = model.tourHtml,
            views_no = model.viewsNo
        )
    }
}