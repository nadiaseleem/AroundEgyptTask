package com.example.aroundegypt.features.home.presentation.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aroundegypt.common.presentation.ui.theme.Gotham
import com.example.aroundegypt.features.home.domain.models.City
import com.example.aroundegypt.features.home.domain.models.Era
import com.example.aroundegypt.features.home.domain.models.Experience
import com.example.aroundegypt.features.home.domain.models.GmapLocation
import com.example.aroundegypt.features.home.domain.models.Period
import com.example.aroundegypt.features.home.domain.models.Review
import com.example.aroundegypt.features.home.domain.models.Tag
import com.example.aroundegypt.features.home.domain.models.TicketPrice

@Composable
fun ExperienceCard(
    modifier: Modifier = Modifier,
    experience: Experience,
    isRecommended: Boolean = false,
    onLikeClick: (Experience) -> Unit,
    onExperienceClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onExperienceClick(experience.id) }
            .height(214.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExperienceImage(
            experience,
            isRecommended = isRecommended,
            modifier = Modifier.height(164.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = experience.title,
                fontFamily = Gotham,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                modifier = Modifier.weight(2f)
            )
            LikeExperience(
                modifier = Modifier.weight(1f),
                experience = experience,
                onLikeClick = { onLikeClick(it) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ExperienceCardPreview() {
    ExperienceCard(experience = experience, onExperienceClick = {}, onLikeClick = {})
}

val experience = Experience(
    id = "7f209d18-36a1-44d5-a0ed-b7eddfad48d6",
    title = "Abu Simbel Temples",
    coverPhoto = "https://aroundegypt-production.s3.eu-central-1.amazonaws.com/29/PmA89sBqFNkjDZUVOOaQ8PyEtlIXi7-metaSzNBVFFsaFU4VHhMVmkxZ253emdVNlczTEJRS3BuM1paWDg0MHIzci5qcGVn-.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIASZMRQEMKBKVP4NHO%2F20250312%2Feu-central-1%2Fs3%2Faws4_request&X-Amz-Date=20250312T073604Z&X-Amz-SignedHeaders=host&X-Amz-Expires=172800&X-Amz-Signature=1f65173e2bd01583160549a586569b696702832b45b1f8b4cf828cfede458a5b",
    description = "The Abu Simbel temples are two massive rock temples at Abu Simbel, a village in Nubia, southern Egypt, near the border with Sudan. They are situated on the western bank of Lake Nasser, about 230 km southwest of Aswan. The complex is part of the UNESCO World Heritage Site known as the \"Nubian Monuments\", which run from Abu Simbel downriver to Philae.",
    viewsNo = 35025,
    likesNo = 2596,
    recommended = 1,
    hasVideo = 0,
    tags = listOf(
        Tag(
            id = 9, name = "Ramesses", disable = false, topPick = 0
        ), Tag(
            id = 21, name = "History", disable = false, topPick = 0
        ), Tag(
            id = 22, name = "Pharaonic", disable = false, topPick = 0
        ), Tag(
            id = 25, name = "Temple", disable = false, topPick = 0
        )
    ),
    city = City(
        id = 10, name = "Aswan", disable = false, topPick = 0
    ),
    tourHtml = "https://aroundegypt-production.s3.eu-central-1.amazonaws.com/1589111832vtour/vtour/tour.html",
    famousFigure = "Ramesses II",
    period = Period(
        createdAt = "", id = "", updatedAt = "", value = ""
    ),
    era = Era(
        id = "d1515c38-810f-4532-8d3d-fdfeec7a311b",
        value = "The New Kingdom Period",
        createdAt = "2023-10-08T12:37:57.000000Z",
        updatedAt = "2023-10-12T08:35:40.000000Z"
    ),
    founded = "",
    detailedDescription = "Carved out of the mountain on the west bank of the Nile between 1274 and 1244 BC, this imposing main temple of the Abu Simbel complex was as much dedicated to the deified Ramses II himself as to Ra-Horakhty, Amun and Ptah. The four colossal statues of the pharaoh, which front the temple, are like gigantic sentinels watching over the incoming traffic from the south, undoubtedly designed as a warning of the strength of the pharaoh.  Over the centuries both the Nile and the desert sands shifted, and this temple was lost to the world until 1813, when it was rediscovered by chance by the Swiss explorer Jean-Louis Burckhardt. Only one of the heads was completely showing above the sand, the next head was broken off and, of the remaining two, only the crowns could be seen. Enough sand was cleared away in 1817 by Giovanni Belzoni for the temple to be entered.\n\nFrom the temple’s forecourt, a short flight of steps leads up to the terrace in front of the massive rock-cut facade, which is about 30m high and 35m wide. Guarding the entrance, three of the four famous colossal statues stare out across the water into eternity – the inner left statue collapsed in antiquity and its upper body still lies on the ground. The statues, more than 20m high, are accompanied by smaller statues of the pharaoh’s mother, Queen Tuya, his wife Nefertari and some of his favourite children. Above the entrance, between the central throned colossi, is the figure of the falcon-headed sun god Ra-Horakhty.\n\nThe roof of the large hall is decorated with vultures, symbolising the protective goddess Nekhbet, and is supported by eight columns, each fronted by an Osiride statue of Ramses II. Reliefs on the walls depict the pharaoh’s prowess in battle, trampling over his enemies and slaughtering them in front of the gods. On the north wall is a depiction of the famous Battle of Kadesh (c 1274 BC), in what is now Syria, where Ramses inspired his demoralised army so that they won the battle against the Hittites. The scene is dominated by a famous relief of Ramses in his chariot, shooting arrows at his fleeing enemies. Also visible is the Egyptian camp, walled off by its soldiers’ round-topped shields, and the fortified Hittite town, surrounded by the Orontes River.\n\nThe next hall, the four-columned vestibule where Ramses and Nefertari are shown in front of the gods and the solar barques, leads to the sacred sanctuary, where Ramses and the triad of gods of the Great Temple sit on their thrones.\n\nThe original temple was aligned in such a way that each 21 February and 21 October, Ramses’ birthday and coronation day, the first rays of the rising sun moved across the hypostyle hall, through the vestibule and into the sanctuary, where they illuminate the figures of Ra-Horakhty, Ramses II and Amun. Ptah, to the left, was never supposed to be illuminated. Since the temples were moved, this phenomenon happens one day later",
    address = "It is located on the western bank of Lake Nasser, 290 km southwest of Aswan",
    gmapLocation = GmapLocation(
        type = "Point", coordinates = listOf(31.62581, 22.33748)
    ),

    startingPrice = 5,
    ticketPrices = listOf(
        TicketPrice(type = "Egyptian Student", price = 5),
        TicketPrice(type = "Foreigner", price = 200),
        TicketPrice(type = "Foreign Student", price = 100),
        TicketPrice(type = "Egyptian", price = 20)
    ),
    experienceTips = emptyList(),
    isLiked = false,
    reviews = listOf(
        Review(
            id = "54e16909-3ced-4f75-a541-2d7b042752ca",
            experience = "Abu Simbel Temples",
            name = "A",
            rating = 5,
            comment = "perfect",
            createdAt = "February 2025"
        ), Review(
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