package com.example.aroundegypt.features.home.data.mappers

import com.example.aroundegypt.common.data.mapper.Mapper
import com.example.aroundegypt.features.home.data.models.dto.ExperiencesResponseDto
import com.example.aroundegypt.features.home.data.models.entity.CityEntity
import com.example.aroundegypt.features.home.data.models.entity.DayTimeEntity
import com.example.aroundegypt.features.home.data.models.entity.EraEntity
import com.example.aroundegypt.features.home.data.models.entity.ExperienceEntity
import com.example.aroundegypt.features.home.data.models.entity.GmapLocationEntity
import com.example.aroundegypt.features.home.data.models.entity.OpeningHoursEntity
import com.example.aroundegypt.features.home.data.models.entity.PeriodEntity
import com.example.aroundegypt.features.home.data.models.entity.ReviewEntity
import com.example.aroundegypt.features.home.data.models.entity.TagEntity
import com.example.aroundegypt.features.home.data.models.entity.TicketPriceEntity
import com.example.aroundegypt.features.home.data.models.entity.TranslatedOpeningHoursEntity
import com.example.aroundegypt.features.home.domain.models.ExperiencesResponse

internal object ExperienceMapper :
    Mapper<ExperiencesResponseDto.ExperienceDto, ExperiencesResponse.Experience, ExperienceEntity>() {

    override fun dtoToDomain(model: ExperiencesResponseDto.ExperienceDto): ExperiencesResponse.Experience {
        return ExperiencesResponse.Experience(
            address = model.address ?: "",
            audioUrl = model.audioUrl ?: "",
            city = ExperiencesResponse.Experience.City(
                disable = model.city?.disable ?: false,
                id = model.city?.id ?: 0,
                name = model.city?.name ?: "",
                topPick = model.city?.topPick ?: 0
            ),
            coverPhoto = model.coverPhoto ?: "",
            description = model.description ?: "",
            detailedDescription = model.detailedDescription ?: "",
            era = ExperiencesResponse.Experience.Era(
                createdAt = model.era?.createdAt ?: "",
                id = model.era?.id ?: "",
                updatedAt = model.era?.updatedAt ?: "",
                value = model.era?.value ?: ""
            ),
            experienceTips = model.experienceTips?.filterNotNull() ?: emptyList(),
            famousFigure = model.famousFigure ?: "",
            founded = model.founded ?: "",
            gmapLocation = ExperiencesResponse.Experience.GmapLocation(
                coordinates = model.gmapLocation?.coordinates?.filterNotNull() ?: emptyList(),
                type = model.gmapLocation?.type ?: ""
            ),
            hasAudio = model.hasAudio ?: false,
            hasVideo = model.hasVideo ?: 0,
            id = model.id ?: "",
            isLiked = model.isLiked ?: false,
            likesNo = model.likesNo ?: 0,
            openingHours = ExperiencesResponse.Experience.OpeningHours(
                friday = model.openingHours?.friday?.filterNotNull() ?: emptyList(),
                monday = model.openingHours?.monday?.filterNotNull() ?: emptyList(),
                saturday = model.openingHours?.saturday?.filterNotNull() ?: emptyList(),
                sunday = model.openingHours?.sunday?.filterNotNull() ?: emptyList(),
                thursday = model.openingHours?.thursday?.filterNotNull() ?: emptyList(),
                tuesday = model.openingHours?.tuesday?.filterNotNull() ?: emptyList(),
                wednesday = model.openingHours?.wednesday?.filterNotNull() ?: emptyList()
            ),
            period = ExperiencesResponse.Experience.Period(
                createdAt = model.period?.createdAt ?: "",
                id = model.period?.id ?: "",
                updatedAt = model.period?.updatedAt ?: "",
                value = model.period?.value ?: ""
            ),
            rating = model.rating ?: 0,
            recommended = model.recommended ?: 0,
            reviews = model.reviews?.filterNotNull()?.map { reviewDto ->
                ExperiencesResponse.Experience.Review(
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
                ExperiencesResponse.Experience.Tag(
                    disable = tagDto.disable ?: false,
                    id = tagDto.id ?: 0,
                    name = tagDto.name ?: "",
                    topPick = tagDto.topPick ?: 0
                )
            } ?: emptyList(),
            ticketPrices = model.ticketPrices?.filterNotNull()?.map { ticketPriceDto ->
                ExperiencesResponse.Experience.TicketPrice(
                    price = ticketPriceDto.price ?: 0,
                    type = ticketPriceDto.type ?: ""
                )
            } ?: emptyList(),
            title = model.title ?: "",
            tourHtml = model.tourHtml ?: "",
            translatedOpeningHours = ExperiencesResponse.Experience.TranslatedOpeningHours(
                friday = ExperiencesResponse.Experience.TranslatedOpeningHours.Friday(
                    day = model.translatedOpeningHours?.friday?.day ?: "",
                    time = model.translatedOpeningHours?.friday?.time ?: ""
                ),
                monday = ExperiencesResponse.Experience.TranslatedOpeningHours.Monday(
                    day = model.translatedOpeningHours?.monday?.day ?: "",
                    time = model.translatedOpeningHours?.monday?.time ?: ""
                ),
                saturday = ExperiencesResponse.Experience.TranslatedOpeningHours.Saturday(
                    day = model.translatedOpeningHours?.saturday?.day ?: "",
                    time = model.translatedOpeningHours?.saturday?.time ?: ""
                ),
                sunday = ExperiencesResponse.Experience.TranslatedOpeningHours.Sunday(
                    day = model.translatedOpeningHours?.sunday?.day ?: "",
                    time = model.translatedOpeningHours?.sunday?.time ?: ""
                ),
                thursday = ExperiencesResponse.Experience.TranslatedOpeningHours.Thursday(
                    day = model.translatedOpeningHours?.thursday?.day ?: "",
                    time = model.translatedOpeningHours?.thursday?.time ?: ""
                ),
                tuesday = ExperiencesResponse.Experience.TranslatedOpeningHours.Tuesday(
                    day = model.translatedOpeningHours?.tuesday?.day ?: "",
                    time = model.translatedOpeningHours?.tuesday?.time ?: ""
                ),
                wednesday = ExperiencesResponse.Experience.TranslatedOpeningHours.Wednesday(
                    day = model.translatedOpeningHours?.wednesday?.day ?: "",
                    time = model.translatedOpeningHours?.wednesday?.time ?: ""
                )
            ),
            viewsNo = model.viewsNo ?: 0
        )
    }

    override fun entityToDomain(model: ExperienceEntity): ExperiencesResponse.Experience {
        return ExperiencesResponse.Experience(
            address = model.address,
            audioUrl = model.audio_url,
            city = ExperiencesResponse.Experience.City(
                disable = model.city.disable,
                id = model.city.id,
                name = model.city.name,
                topPick = model.city.top_pick
            ),
            coverPhoto = model.cover_photo,
            description = model.description,
            detailedDescription = model.detailed_description,
            era = ExperiencesResponse.Experience.Era(
                createdAt = model.era.created_at,
                id = model.era.id,
                updatedAt = model.era.updated_at,
                value = model.era.value
            ),
            experienceTips = model.experience_tips,
            famousFigure = model.famous_figure,
            founded = model.founded,
            gmapLocation = ExperiencesResponse.Experience.GmapLocation(
                coordinates = model.gmap_location.coordinates,
                type = model.gmap_location.type
            ),
            hasAudio = model.has_audio,
            hasVideo = model.has_video,
            id = model.id,
            isLiked = model.is_liked,
            likesNo = model.likes_no,
            openingHours = ExperiencesResponse.Experience.OpeningHours(
                friday = model.opening_hours.friday,
                monday = model.opening_hours.monday,
                saturday = model.opening_hours.saturday,
                sunday = model.opening_hours.sunday,
                thursday = model.opening_hours.thursday,
                tuesday = model.opening_hours.tuesday,
                wednesday = model.opening_hours.wednesday
            ),
            period = ExperiencesResponse.Experience.Period(
                createdAt = model.period.created_at,
                id = model.period.id,
                updatedAt = model.period.updated_at,
                value = model.period.value
            ),
            rating = model.rating,
            recommended = model.recommended,
            reviews = model.reviews.map { reviewEntity ->
                ExperiencesResponse.Experience.Review(
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
                ExperiencesResponse.Experience.Tag(
                    disable = tagEntity.disable,
                    id = tagEntity.id,
                    name = tagEntity.name,
                    topPick = tagEntity.top_pick
                )
            },
            ticketPrices = model.ticket_prices.map { ticketPriceEntity ->
                ExperiencesResponse.Experience.TicketPrice(
                    price = ticketPriceEntity.price,
                    type = ticketPriceEntity.type
                )
            },
            title = model.title,
            tourHtml = model.tour_html,
            translatedOpeningHours = ExperiencesResponse.Experience.TranslatedOpeningHours(
                friday = ExperiencesResponse.Experience.TranslatedOpeningHours.Friday(
                    day = model.translated_opening_hours.friday.day,
                    time = model.translated_opening_hours.friday.time
                ),
                monday = ExperiencesResponse.Experience.TranslatedOpeningHours.Monday(
                    day = model.translated_opening_hours.monday.day,
                    time = model.translated_opening_hours.monday.time
                ),
                saturday = ExperiencesResponse.Experience.TranslatedOpeningHours.Saturday(
                    day = model.translated_opening_hours.saturday.day,
                    time = model.translated_opening_hours.saturday.time
                ),
                sunday = ExperiencesResponse.Experience.TranslatedOpeningHours.Sunday(
                    day = model.translated_opening_hours.sunday.day,
                    time = model.translated_opening_hours.sunday.time
                ),
                thursday = ExperiencesResponse.Experience.TranslatedOpeningHours.Thursday(
                    day = model.translated_opening_hours.thursday.day,
                    time = model.translated_opening_hours.thursday.time
                ),
                tuesday = ExperiencesResponse.Experience.TranslatedOpeningHours.Tuesday(
                    day = model.translated_opening_hours.tuesday.day,
                    time = model.translated_opening_hours.tuesday.time
                ),
                wednesday = ExperiencesResponse.Experience.TranslatedOpeningHours.Wednesday(
                    day = model.translated_opening_hours.wednesday.day,
                    time = model.translated_opening_hours.wednesday.time
                )
            ),
            viewsNo = model.views_no
        )
    }

    override fun domainToEntity(model: ExperiencesResponse.Experience): ExperienceEntity {
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
            opening_hours = OpeningHoursEntity(
                friday = model.openingHours.friday,
                monday = model.openingHours.monday,
                saturday = model.openingHours.saturday,
                sunday = model.openingHours.sunday,
                thursday = model.openingHours.thursday,
                tuesday = model.openingHours.tuesday,
                wednesday = model.openingHours.wednesday
            ),
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
            translated_opening_hours = TranslatedOpeningHoursEntity(
                friday = DayTimeEntity(
                    day = model.translatedOpeningHours.friday.day,
                    time = model.translatedOpeningHours.friday.time
                ),
                monday = DayTimeEntity(
                    day = model.translatedOpeningHours.monday.day,
                    time = model.translatedOpeningHours.monday.time
                ),
                saturday = DayTimeEntity(
                    day = model.translatedOpeningHours.saturday.day,
                    time = model.translatedOpeningHours.saturday.time
                ),
                sunday = DayTimeEntity(
                    day = model.translatedOpeningHours.sunday.day,
                    time = model.translatedOpeningHours.sunday.time
                ),
                thursday = DayTimeEntity(
                    day = model.translatedOpeningHours.thursday.day,
                    time = model.translatedOpeningHours.thursday.time
                ),
                tuesday = DayTimeEntity(
                    day = model.translatedOpeningHours.tuesday.day,
                    time = model.translatedOpeningHours.tuesday.time
                ),
                wednesday = DayTimeEntity(
                    day = model.translatedOpeningHours.wednesday.day,
                    time = model.translatedOpeningHours.wednesday.time
                )
            ),
            views_no = model.viewsNo
        )
    }
}