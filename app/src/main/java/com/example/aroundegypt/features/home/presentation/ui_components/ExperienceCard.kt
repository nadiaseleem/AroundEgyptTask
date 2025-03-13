package com.example.aroundegypt.features.home.presentation.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.aroundegypt.R
import com.example.aroundegypt.common.presentation.ui.theme.Gotham
import com.example.aroundegypt.common.presentation.ui.theme.black49
import com.example.aroundegypt.common.presentation.ui_components.VectorIcon
import com.example.aroundegypt.common.presentation.ui_components.VectorResIcon
import com.example.aroundegypt.features.home.domain.models.ExperiencesResponse

@Composable
fun ExperienceCard(
    modifier: Modifier = Modifier,
    experience: ExperiencesResponse.Experience,
    isRecommended: Boolean = false,
    onLikeClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(214.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExperienceImage(experience, isRecommended = isRecommended)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = experience.title,
                fontFamily = Gotham,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            LikeExperience(experience = experience, onLikeClick = { onLikeClick(it) })
        }
    }
}

@Composable
private fun ExperienceImage(
    experience: ExperiencesResponse.Experience,
    modifier: Modifier = Modifier,
    isRecommended: Boolean,
    onViewIn360Click: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(164.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(experience.coverPhoto)
                    .build(),
                contentDescription = stringResource(R.string.experience_image),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (recommendedBadge, infoIcon, viewsCount, imageIcon, viewIn360) = createRefs()

                if (isRecommended)
                // Recommended Badge
                RecommendedBadge(modifier = Modifier.constrainAs(recommendedBadge) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                })

                // Info Icon
                VectorIcon(
                    icon = Icons.Outlined.Info,
                    color = Color.White,
                    contentDescription = stringResource(R.string.info),
                    modifier = Modifier.constrainAs(infoIcon) {
                        top.linkTo(parent.top, margin = 10.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                    },
                    size = 20.dp
                )

                // Views Count
                ViewsCount(
                    modifier = Modifier.constrainAs(viewsCount) {
                        bottom.linkTo(parent.bottom, margin = 5.dp)
                        start.linkTo(parent.start, margin = 10.dp)
                    },
                    viewsNo = experience.viewsNo
                )

                // Image Icon
                VectorResIcon(
                    icon = R.drawable.ic_image,
                    height = 16.dp,
                    contentDescription = stringResource(R.string.image),
                    modifier = Modifier.constrainAs(imageIcon) {
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                    }
                )

                // 360 View Icon
                VectorResIcon(
                    icon = R.drawable.ic_360view,
                    height = 37.dp,
                    contentDescription = stringResource(R.string.view_in_360),
                    modifier = Modifier
                        .clickable { onViewIn360Click() }
                        .constrainAs(viewIn360) {
                            bottom.linkTo(parent.bottom)
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
            }
        }
    }
}

@Composable
fun RecommendedBadge(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(black49, RoundedCornerShape(9.dp))
            .height(22.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        VectorResIcon(
            icon = R.drawable.ic_recommended,
            height = 10.dp,
            contentDescription = stringResource(R.string.recommended),
            modifier = Modifier.padding(start = 10.dp)
        )
        Text(
            text = stringResource(R.string.recommended_capital),
            color = Color.White,
            fontFamily = Gotham,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            modifier = Modifier.padding(start = 6.dp, end = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RecommendedBadgePreview() {
    RecommendedBadge()
}

@Composable
fun ViewsCount(modifier: Modifier = Modifier, viewsNo: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
    ) {
        VectorResIcon(
            icon = R.drawable.ic_eye,
            height = 10.dp,
            contentDescription = stringResource(R.string.number_of_views)
        )
        Text(
            text = "$viewsNo",
            color = Color.White,
            fontFamily = Gotham,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun LikeExperience(
    modifier: Modifier = Modifier,
    experience: ExperiencesResponse.Experience,
    onLikeClick: (String) -> Unit
) {
    Row {
        Text(
            text = "${experience.likesNo}",
            modifier = modifier.padding(end = 10.dp),
            fontFamily = Gotham,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
        VectorResIcon(icon = if (!experience.isLiked) R.drawable.ic_outlined_like else R.drawable.ic_filed_like,
            height = 18.dp,
            contentDescription = stringResource(
                R.string.like
            ),
            modifier = Modifier.clickable {
                onLikeClick(experience.id)
            })
    }
}

@Preview(showBackground = true)
@Composable
private fun LikeExperiencePreview() {
    LikeExperience(experience = experience, onLikeClick = {})

}

@Preview(showBackground = true)
@Composable
private fun ViewsCountPreview() {
    ViewsCount(viewsNo = 3525)
}

@Preview(showBackground = true)
@Composable
private fun ExperienceCardPreview() {
    ExperienceCard(experience = experience) {}
}

val experience = ExperiencesResponse.Experience(
    id = "7f209d18-36a1-44d5-a0ed-b7eddfad48d6",
    title = "Abu Simbel Temples",
    coverPhoto = "https://aroundegypt-production.s3.eu-central-1.amazonaws.com/29/PmA89sBqFNkjDZUVOOaQ8PyEtlIXi7-metaSzNBVFFsaFU4VHhMVmkxZ253emdVNlczTEJRS3BuM1paWDg0MHIzci5qcGVn-.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIASZMRQEMKBKVP4NHO%2F20250312%2Feu-central-1%2Fs3%2Faws4_request&X-Amz-Date=20250312T073604Z&X-Amz-SignedHeaders=host&X-Amz-Expires=172800&X-Amz-Signature=1f65173e2bd01583160549a586569b696702832b45b1f8b4cf828cfede458a5b",
    description = "The Abu Simbel temples are two massive rock temples at Abu Simbel, a village in Nubia, southern Egypt, near the border with Sudan. They are situated on the western bank of Lake Nasser, about 230 km southwest of Aswan. The complex is part of the UNESCO World Heritage Site known as the \"Nubian Monuments\", which run from Abu Simbel downriver to Philae.",
    viewsNo = 35025,
    likesNo = 2596,
    recommended = 1,
    hasVideo = 0,
    tags = listOf(
        ExperiencesResponse.Experience.Tag(
            id = 9, name = "Ramesses", disable = false, topPick = 0
        ), ExperiencesResponse.Experience.Tag(
            id = 21, name = "History", disable = false, topPick = 0
        ), ExperiencesResponse.Experience.Tag(
            id = 22, name = "Pharaonic", disable = false, topPick = 0
        ), ExperiencesResponse.Experience.Tag(
            id = 25, name = "Temple", disable = false, topPick = 0
        )
    ),
    city = ExperiencesResponse.Experience.City(
        id = 10, name = "Aswan", disable = false, topPick = 0
    ),
    tourHtml = "https://aroundegypt-production.s3.eu-central-1.amazonaws.com/1589111832vtour/vtour/tour.html",
    famousFigure = "Ramesses II",
    period = ExperiencesResponse.Experience.Period(
        createdAt = "", id = "", updatedAt = "", value = ""
    ),
    era = ExperiencesResponse.Experience.Era(
        id = "d1515c38-810f-4532-8d3d-fdfeec7a311b",
        value = "The New Kingdom Period",
        createdAt = "2023-10-08T12:37:57.000000Z",
        updatedAt = "2023-10-12T08:35:40.000000Z"
    ),
    founded = "",
    detailedDescription = "Carved out of the mountain on the west bank of the Nile between 1274 and 1244 BC, this imposing main temple of the Abu Simbel complex was as much dedicated to the deified Ramses II himself as to Ra-Horakhty, Amun and Ptah. The four colossal statues of the pharaoh, which front the temple, are like gigantic sentinels watching over the incoming traffic from the south, undoubtedly designed as a warning of the strength of the pharaoh.  Over the centuries both the Nile and the desert sands shifted, and this temple was lost to the world until 1813, when it was rediscovered by chance by the Swiss explorer Jean-Louis Burckhardt. Only one of the heads was completely showing above the sand, the next head was broken off and, of the remaining two, only the crowns could be seen. Enough sand was cleared away in 1817 by Giovanni Belzoni for the temple to be entered.\n\nFrom the temple’s forecourt, a short flight of steps leads up to the terrace in front of the massive rock-cut facade, which is about 30m high and 35m wide. Guarding the entrance, three of the four famous colossal statues stare out across the water into eternity – the inner left statue collapsed in antiquity and its upper body still lies on the ground. The statues, more than 20m high, are accompanied by smaller statues of the pharaoh’s mother, Queen Tuya, his wife Nefertari and some of his favourite children. Above the entrance, between the central throned colossi, is the figure of the falcon-headed sun god Ra-Horakhty.\n\nThe roof of the large hall is decorated with vultures, symbolising the protective goddess Nekhbet, and is supported by eight columns, each fronted by an Osiride statue of Ramses II. Reliefs on the walls depict the pharaoh’s prowess in battle, trampling over his enemies and slaughtering them in front of the gods. On the north wall is a depiction of the famous Battle of Kadesh (c 1274 BC), in what is now Syria, where Ramses inspired his demoralised army so that they won the battle against the Hittites. The scene is dominated by a famous relief of Ramses in his chariot, shooting arrows at his fleeing enemies. Also visible is the Egyptian camp, walled off by its soldiers’ round-topped shields, and the fortified Hittite town, surrounded by the Orontes River.\n\nThe next hall, the four-columned vestibule where Ramses and Nefertari are shown in front of the gods and the solar barques, leads to the sacred sanctuary, where Ramses and the triad of gods of the Great Temple sit on their thrones.\n\nThe original temple was aligned in such a way that each 21 February and 21 October, Ramses’ birthday and coronation day, the first rays of the rising sun moved across the hypostyle hall, through the vestibule and into the sanctuary, where they illuminate the figures of Ra-Horakhty, Ramses II and Amun. Ptah, to the left, was never supposed to be illuminated. Since the temples were moved, this phenomenon happens one day later",
    address = "It is located on the western bank of Lake Nasser, 290 km southwest of Aswan",
    gmapLocation = ExperiencesResponse.Experience.GmapLocation(
        type = "Point", coordinates = listOf(31.62581, 22.33748)
    ),
    openingHours = ExperiencesResponse.Experience.OpeningHours(
        sunday = listOf("07:00-16:00"),
        monday = listOf("07:00-16:00"),
        tuesday = listOf("07:00-16:00"),
        wednesday = listOf("07:00-16:00"),
        thursday = listOf("07:00-16:00"),
        friday = listOf("07:00-16:00"),
        saturday = listOf("07:00-16:00")
    ),
    translatedOpeningHours = ExperiencesResponse.Experience.TranslatedOpeningHours(
        sunday = ExperiencesResponse.Experience.TranslatedOpeningHours.Sunday(
            day = "Sunday", time = "07:00-16:00"
        ), monday = ExperiencesResponse.Experience.TranslatedOpeningHours.Monday(
            day = "Monday", time = "07:00-16:00"
        ), tuesday = ExperiencesResponse.Experience.TranslatedOpeningHours.Tuesday(
            day = "Tuesday", time = "07:00-16:00"
        ), wednesday = ExperiencesResponse.Experience.TranslatedOpeningHours.Wednesday(
            day = "Wednesday", time = "07:00-16:00"
        ), thursday = ExperiencesResponse.Experience.TranslatedOpeningHours.Thursday(
            day = "Thursday", time = "07:00-16:00"
        ), friday = ExperiencesResponse.Experience.TranslatedOpeningHours.Friday(
            day = "Friday", time = "07:00-16:00"
        ), saturday = ExperiencesResponse.Experience.TranslatedOpeningHours.Saturday(
            day = "Saturday", time = "07:00-16:00"
        )
    ),
    startingPrice = 5,
    ticketPrices = listOf(
        ExperiencesResponse.Experience.TicketPrice(type = "Egyptian Student", price = 5),
        ExperiencesResponse.Experience.TicketPrice(type = "Foreigner", price = 200),
        ExperiencesResponse.Experience.TicketPrice(type = "Foreign Student", price = 100),
        ExperiencesResponse.Experience.TicketPrice(type = "Egyptian", price = 20)
    ),
    experienceTips = emptyList(),
    isLiked = false,
    reviews = listOf(
        ExperiencesResponse.Experience.Review(
            id = "54e16909-3ced-4f75-a541-2d7b042752ca",
            experience = "Abu Simbel Temples",
            name = "A",
            rating = 5,
            comment = "perfect",
            createdAt = "February 2025"
        ), ExperiencesResponse.Experience.Review(
            id = "4cfcd6db-0dbc-4a8e-b3d1-3bb6a1cec9be",
            experience = "Abu Simbel Temples",
            name = "test",
            rating = 4,
            comment = "test",
            createdAt = "January 2025"
        )
    ),
    rating = 5,
    reviewsNo = 2,
    audioUrl = "https://aroundegypt-production.s3.eu-central-1.amazonaws.com/30/7cylcx8lhhKnK2bbxjGXRuCrEgT7q7-metaMy1HcmVhdC1UZW1wbGUtb2YtQWJ1LVNpbWJsZV8wMS1WMi5tcDM%3D-.mp3",
    hasAudio = true
)