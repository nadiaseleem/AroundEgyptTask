package com.example.aroundegypt.features.home.domain.usecases

import com.example.aroundegypt.common.data.models.exception.AroundEgyptException
import com.example.aroundegypt.common.data.models.state.Resource
import com.example.aroundegypt.features.home.domain.models.experience
import com.example.aroundegypt.features.home.domain.repository.IExperiencesRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class GetLikedExperiencesUCTest {

    @Mock
    private lateinit var repository: IExperiencesRepository

    private lateinit var getLikedExperiencesUC: GetLikedExperiencesUC

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getLikedExperiencesUC = GetLikedExperiencesUC(repository)
    }

    @Test
    fun `invoke should return Resource Success with list of liked experiences when repository returns data`() =
        runTest {
            // Arrange
            val likedExperiences = listOf(
                experience.copy(id = "1", title = "Pyramids Tour"),
                experience.copy(id = "2", title = "Nile Cruise")
            )
            whenever(repository.getLikedExperiences()).thenReturn(likedExperiences)

            // Act
            val result = getLikedExperiencesUC()

            // Assert
            assertTrue(result is Resource.Success)
            assertEquals(likedExperiences, (result as Resource.Success).data)
        }

    @Test
    fun `invoke should return Resource Success with empty list when repository returns no data`() =
        runTest {
            // Arrange
            whenever(repository.getLikedExperiences()).thenReturn(emptyList())

            // Act
            val result = getLikedExperiencesUC()

            // Assert
            assertTrue(result is Resource.Success)
            assertTrue((result as Resource.Success).data.isEmpty())
        }

    @Test
    fun `invoke should return Resource Failure with AroundEgyptException when repository throws AroundEgyptException`() =
        runTest {
            // Arrange
            val expectedException =
                AroundEgyptException.Network.UnknownHost(errorMessage = "Unknown host")
            whenever(repository.getLikedExperiences()).thenAnswer { throw (expectedException) }

            // Act
            val result = getLikedExperiencesUC()

            // Assert
            assertTrue(result is Resource.Failure)
            assertEquals(expectedException, (result as Resource.Failure).exception)
        }

    @Test
    fun `invoke should return Resource Failure with UnknownException when repository throws unknown exception`() =
        runTest {
            // Arrange
            val unknownException = RuntimeException("Unknown error")
            whenever(repository.getLikedExperiences()).thenThrow(unknownException)

            // Act
            val result = getLikedExperiencesUC()

            // Assert
            assertTrue(result is Resource.Failure)
            assertTrue((result as Resource.Failure).exception is AroundEgyptException.UnknownException)
            assertEquals(
                "Unknown error in GetExperienceFromLocalUC: $unknownException",
                result.exception.message
            )
        }
}