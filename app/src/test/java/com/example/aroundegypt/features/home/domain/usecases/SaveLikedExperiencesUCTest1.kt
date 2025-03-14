package com.example.aroundegypt.features.home.domain.usecases

import com.example.aroundegypt.features.home.domain.models.experience
import com.example.aroundegypt.features.home.domain.repository.IExperiencesRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class SaveLikedExperiencesUCTest {

    private lateinit var repository: IExperiencesRepository
    private lateinit var saveLikedExperiencesUC: SaveLikedExperiencesUC
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        saveLikedExperiencesUC = SaveLikedExperiencesUC(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should call repository saveLikedExperiences with correct data`() = runTest {
        // Arrange
        val likedExperiences = listOf(
            experience.copy(id = "1", title = "Experience 1"),
            experience.copy(id = "2", title = "Experience 2")
        )

        // Act
        saveLikedExperiencesUC(likedExperiences)

        // Assert
        verify(repository).saveLikedExperiences(likedExperiences)
    }

    @Test
    fun `invoke should propagate exceptions thrown by repository`() = runTest {
        // Arrange
        val likedExperiences = listOf(
            experience.copy(id = "1", title = "Experience 1"),
            experience.copy(id = "2", title = "Experience 2")
        )
        val exception = RuntimeException("Failed to save liked experiences")
        whenever(repository.saveLikedExperiences(likedExperiences)).thenThrow(exception)

        // Act & Assert
        try {
            saveLikedExperiencesUC(likedExperiences)
        } catch (e: RuntimeException) {
            assertEquals("Failed to save liked experiences", e.message)
        }
    }
}