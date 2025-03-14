package com.example.aroundegypt.features.home.presentation.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.aroundegypt.common.presentation.ui.theme.orange
import com.example.aroundegypt.common.presentation.ui_components.VectorIcon
import com.example.aroundegypt.common.presentation.ui_components.VectorResIcon
import com.example.aroundegypt.features.home.domain.models.Experience

@Composable
fun ExperienceImage(
    experience: Experience,
    modifier: Modifier = Modifier,
    isRecommended: Boolean,
    onViewIn360Click: () -> Unit = {},
    showExploreNowButton: Boolean = false,
    showInfoIcon: Boolean = true
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
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
                val (recommendedBadge, infoIcon, viewsCount, imageIcon, viewIn360, exploreNowBtn) = createRefs()

                // Recommended Badge
                if (isRecommended)
                    RecommendedBadge(modifier = Modifier.constrainAs(recommendedBadge) {
                        top.linkTo(parent.top, margin = 10.dp)
                        start.linkTo(parent.start, margin = 10.dp)
                    })

                if (showInfoIcon)
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
                if (showExploreNowButton)
                    ExploreNowButton(modifier = Modifier.constrainAs(exploreNowBtn) {
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })

            }
        }
    }
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

@Preview(showBackground = true)
@Composable
private fun ViewsCountPreview() {
    ViewsCount(viewsNo = 3525)
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

@Composable
fun ExploreNowButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .height(46.dp)
            .width(152.dp),
        shape = RoundedCornerShape(7.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = orange)
    ) {
        Text(
            text = stringResource(R.string.explore_now),
            fontFamily = Gotham,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExploreNowButtonPreview() {
    ExploreNowButton(onClick = {})
}

@Preview(showBackground = true)
@Composable
private fun RecommendedBadgePreview() {
    RecommendedBadge()
}

@Preview
@Composable
private fun ExperienceImagePreview() {
    ExperienceImage(
        experience = experience,
        isRecommended = false,
        showExploreNowButton = true,
        showInfoIcon = false
    )
}