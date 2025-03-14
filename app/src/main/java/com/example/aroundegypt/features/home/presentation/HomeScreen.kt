package com.example.aroundegypt.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aroundegypt.R
import com.example.aroundegypt.common.presentation.events.EventBus
import com.example.aroundegypt.common.presentation.ui_components.HomeTopAppBar
import com.example.aroundegypt.features.home.domain.models.Experience
import com.example.aroundegypt.features.home.presentation.ui_components.ContentText
import com.example.aroundegypt.features.home.presentation.ui_components.EmptyState
import com.example.aroundegypt.features.home.presentation.ui_components.ExperienceCard
import com.example.aroundegypt.features.home.presentation.ui_components.ExperienceList
import com.example.aroundegypt.features.home.presentation.ui_components.ShimmerCard
import com.example.aroundegypt.features.home.presentation.ui_components.ShimmerList
import com.example.aroundegypt.features.home.presentation.ui_components.TitleText
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    drawerState: DrawerState,
    onExperienceClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = viewModel.snackbarHostState

    // Observe events
    LaunchedEffect(Unit) {
        EventBus.events.collect { event ->
            when (event) {
                is HomeEvent.ShowError -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }
    HomeScreenContent(
        state = state,
        drawerState = drawerState,
        snackbarHostState = snackbarHostState,
        onLikeClick = { viewModel.likeExperience(it) },
        onSearchQueryChange = { viewModel.setSearchQuery(it) },
        onSearchClick = { viewModel.searchExperiences() },
        onExperienceClick = { onExperienceClick(it) },
    )
}

@Composable
fun HomeScreenContent(
    state: HomeViewStates,
    drawerState: DrawerState,
    snackbarHostState: SnackbarHostState,
    onLikeClick: (Experience) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onExperienceClick: (String) -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            HomeTopAppBar(drawerState = drawerState,
                searchQuery = state.searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                onFilterClick = {},
                onSearchClick = {
                    onSearchClick()
                })
        }) { contentPadding ->

        if (state.searchQuery.isEmpty()) {
            DefaultHomeState(
                state = state,
                contentPadding = contentPadding,
                onExperienceClick = { onExperienceClick(it) },
                onLikeClick = { onLikeClick(it) }
            )
        } else {
            SearchState(contentPadding, state,
                onLikeClick = { onLikeClick(it) },
                onExperienceClick = { onExperienceClick(it) }
            )
        }
    }
}


@Composable
private fun DefaultHomeState(
    contentPadding: PaddingValues,
    state: HomeViewStates,
    onLikeClick: (Experience) -> Unit,
    onExperienceClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(contentPadding)
            .padding(start = 18.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Welcome Text
        item {
            TitleText(title = stringResource(R.string.welcome))
        }

        // Welcome Message Text
        item {
            ContentText(content = stringResource(R.string.welcome_message))
        }

        // Recommended Experiences Text
        item {
            TitleText(title = stringResource(R.string.recommended_experiences))
        }


        // Recommended Experiences List
        item {
            ShimmerList(state.isLoading)
            if (!state.isLoading) {
                ExperienceList(
                    experiences = state.recommendedExperiences,
                    isHorizontal = true,
                    isRecommended = true,
                    onLikeClick = onLikeClick,
                    onExperienceClick = { onExperienceClick(it) }
                )
            }
        }


        // Most Recent Text
        item {
            TitleText(title = stringResource(R.string.most_recent))
        }

        // Most Recent Experiences List
        if (state.isLoading) {
            items(5) {
                ShimmerCard(modifier = Modifier.padding(end = 15.dp))
            }
        } else {
            items(state.mostRecentExperiences) { experience ->
                ExperienceCard(
                    experience = experience,
                    modifier = Modifier.padding(end = 18.dp),
                    onExperienceClick = { onExperienceClick(it) },
                    onLikeClick = { onLikeClick(it) }
                )
            }
        }
    }
}


@Composable
private fun SearchState(
    contentPadding: PaddingValues,
    state: HomeViewStates,
    onLikeClick: (Experience) -> Unit,
    onExperienceClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(contentPadding)
            .padding(horizontal = 18.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Show shimmer loading if data is loading
        if (state.isLoading) {
            items(5) {
                ShimmerCard()
            }
        }

        // Show search results if available
        if (state.experiencesSearchResult.isNotEmpty()) {
            items(state.experiencesSearchResult) { experience ->
                ExperienceCard(
                    experience = experience,
                    onExperienceClick = { onExperienceClick(it) },
                    onLikeClick = { onLikeClick(it) })
            }
        }

        // Show empty state if no results and search is performed
        else if (state.isSearchPerformed && !state.isLoading) {
            item {
                EmptyState(
                    iconResId = R.drawable.state_not_found,
                    title = stringResource(R.string.not_found),
                    message = stringResource(R.string.not_found_message),
                    modifier = Modifier.fillParentMaxSize()
                )
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreenContent(
        drawerState = DrawerState(DrawerValue.Closed),
        state = HomeViewStates(),
        snackbarHostState = SnackbarHostState(),
        onLikeClick = {},
        onSearchQueryChange = {},
        onSearchClick = {},
        onExperienceClick = {}
    )
}