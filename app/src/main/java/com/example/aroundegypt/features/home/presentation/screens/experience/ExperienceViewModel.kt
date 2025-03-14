package com.example.aroundegypt.features.home.presentation.screens.experience

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.common.data.models.state.Resource
import com.example.aroundegypt.common.presentation.viewmodel.sendEvent
import com.example.aroundegypt.features.home.domain.models.Experience
import com.example.aroundegypt.features.home.domain.usecases.GetExperienceFromLocalUC
import com.example.aroundegypt.features.home.domain.usecases.GetExperienceUC
import com.example.aroundegypt.features.home.domain.usecases.LikeExperienceUC
import com.example.aroundegypt.features.home.domain.usecases.SaveLikedExperiencesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExperienceViewModel @Inject constructor(
    private val getExperienceDetailsUC: GetExperienceUC,
    private val getExperienceFromLocalUC: GetExperienceFromLocalUC,
    private val likeExperienceUC: LikeExperienceUC,
    private val saveLikedExperiencesUC: SaveLikedExperiencesUC
) : ViewModel() {

    val snackbarHostState = SnackbarHostState()

    private val _state = MutableStateFlow(ExperienceViewStates())
    val state = _state.asStateFlow()

    fun getExperienceDetails(id: String) {
        viewModelScope.launch {
            getExperienceFromLocal(id)
            getExperienceDetailsUC.invoke(id).collect { resource ->
                when (resource) {
                    is Resource.Failure -> resource.exception.message?.let {
                        sendEvent(ExperienceEvent.ShowError(it))
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = resource.loading) }
                    }

                    is Resource.Success -> {
                        val localIsLiked = _state.value.experienceFromLocal?.isLiked
                        val remoteData = resource.data
                        if (remoteData != null) {
                            val updatedExperience = remoteData.copy(
                                isLiked = localIsLiked ?: remoteData.isLiked
                            )
                            _state.update {
                                it.copy(
                                    selectedExperience = updatedExperience
                                )
                            }
                        } else {
                            _state.update { it.copy(selectedExperience = state.value.experienceFromLocal) }
                        }
                    }
                }

            }
        }
    }

    private fun getExperienceFromLocal(id: String) {
        viewModelScope.launch {
            when (val resource = getExperienceFromLocalUC.invoke(id)) {
                is Resource.Failure -> resource.exception.message?.let {
                    sendEvent(ExperienceEvent.ShowError(it))
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = resource.loading) }
                }

                is Resource.Success -> _state.update { it.copy(experienceFromLocal = resource.data) }
            }

        }
    }

    private var lastClickTime = 0L
    private val debounceTime = 500L

    fun likeExperience(experience: Experience) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime < debounceTime) {
            return // Ignore rapid clicks
        }
        lastClickTime = currentTime

        viewModelScope.launch {
            if (state.value.experienceFromLocal?.isLiked == true) {
                return@launch
            }
            likeExperienceUC.invoke(experience.id).collect { resource ->
                when (resource) {
                    is Resource.Failure -> resource.exception.message?.let {
                        sendEvent(ExperienceEvent.ShowError(it))
                    }

                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val updatedExperience = experience.copy(
                            isLiked = true,
                            likesNo = resource.data
                        )
                        _state.update { it.copy(selectedExperience = updatedExperience) }
                        val updatedLikedExperiences = listOf(updatedExperience)
                        saveLikedExperiencesUC.invoke(updatedLikedExperiences)
                    }
                }
            }
        }

    }
}
