package com.example.aroundegypt.features.home.presentation.screens.experience

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aroundegypt.R
import com.example.aroundegypt.common.presentation.events.EventBus
import com.example.aroundegypt.common.presentation.ui.theme.Gotham
import com.example.aroundegypt.common.presentation.ui.theme.cityColor
import com.example.aroundegypt.common.presentation.ui.theme.white30
import com.example.aroundegypt.common.presentation.ui_components.VectorIcon
import com.example.aroundegypt.common.presentation.ui_components.VectorResIcon
import com.example.aroundegypt.features.home.domain.models.Experience
import com.example.aroundegypt.features.home.presentation.ui_components.ContentText
import com.example.aroundegypt.features.home.presentation.ui_components.ExperienceImage
import com.example.aroundegypt.features.home.presentation.ui_components.LikeExperience
import com.example.aroundegypt.features.home.presentation.ui_components.ShimmerCard
import com.example.aroundegypt.features.home.presentation.ui_components.TitleText
import kotlinx.serialization.Serializable

@Serializable
data class ExperienceRoute(val id: String)

@Composable
fun ExperienceScreen(
    modifier: Modifier = Modifier,
    viewModel: ExperienceViewModel = hiltViewModel(),
    onBack: () -> Unit = {},
    id: String
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = viewModel.snackbarHostState

    LaunchedEffect(Unit) {
        viewModel.getExperienceDetails(id)
        EventBus.events.collect { event ->
            when (event) {
                is ExperienceEvent.ShowError -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    ExperienceContent(
        state = state,
        onLikeClick = { viewModel.likeExperience(it) },
        snackbarHostState = viewModel.snackbarHostState,
        onBack = { onBack() })
}

@Composable
fun ExperienceContent(
    state: ExperienceViewStates,
    onLikeClick: (Experience) -> Unit,
    snackbarHostState: SnackbarHostState,
    onBack: () -> Unit = {}
) {
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { contentPadding ->
        Dialog(
            onDismissRequest = { onBack() },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.surface
            ) {
                if (state.isLoading) {
                    ShimmerCard(imageHeight = 285.dp, modifier = Modifier.fillMaxSize())
                } else if (state.selectedExperience == null) {
                    ErrorState(onBack)
                } else {
                    ExperienceDetails(onBack, state.selectedExperience, onLikeClick)
                }
            }
        }
    }
}

@Composable
private fun ExperienceDetails(
    onBack: () -> Unit,
    selectedExperience: Experience,
    onLikeClick: (Experience) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            ExperienceImage(
                selectedExperience,
                isRecommended = false,
                showInfoIcon = false,
                showExploreNowButton = true,
                modifier = Modifier.height(285.dp)
            )
            VectorIcon(
                icon = Icons.Default.Close,
                contentDescription = stringResource(R.string.close),
                size = 24.dp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .clickable { onBack() }
            )
        }
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = selectedExperience.title,
                    fontFamily = Gotham,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(2f)
                )
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    VectorResIcon(
                        icon = R.drawable.ic_share,
                        height = 16.dp,
                        contentDescription = stringResource(R.string.share),
                        modifier = Modifier
                            .padding(end = 13.dp)
                    )
                    LikeExperience(
                        experience = selectedExperience,
                        onLikeClick = { onLikeClick(it) },
                        fontSize = 16.sp,
                        isIconFirst = true
                    )
                }
            }
            ContentText(
                content = stringResource(
                    R.string.egypt,
                    selectedExperience.city.name
                ),
                fontSize = 16.sp,
                color = cityColor
            )
            HorizontalDivider(
                color = white30,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            TitleText(title = stringResource(R.string.description))
            Spacer(modifier = Modifier.height(10.dp))
            ContentText(content = selectedExperience.description)
        }
    }
}

@Composable
private fun ErrorState(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Experience not found",
            fontFamily = Gotham,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.close),
            color = MaterialTheme.colorScheme.primary,
            fontFamily = Gotham,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            modifier = Modifier.clickable { onBack() }
        )
    }
}

@Preview
@Composable
private fun ExperienceContentPreview() {
    ExperienceScreen(id = "")
}