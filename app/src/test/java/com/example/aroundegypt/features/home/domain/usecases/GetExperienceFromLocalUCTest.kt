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

class GetExperienceFromLocalUCTest {

    @Mock
    private lateinit var repository: IExperiencesRepository

    private lateinit var getExperienceFromLocalUC: GetExperienceFromLocalUC

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getExperienceFromLocalUC = GetExperienceFromLocalUC(repository)
    }

    @Test
    fun `invoke should return Resource Success with experience when repository returns data`() =
        runTest {
            // Arrange
            val experienceId = "123"
            val expectedExperience = experience.copy(id = experienceId, title = "Pyramids Tour")
            whenever(repository.getExperienceFromLocal(experienceId)).thenReturn(expectedExperience)

            // Act
            val result = getExperienceFromLocalUC(experienceId)

            // Assert
            assertTrue(result is Resource.Success)
            assertEquals(expectedExperience, (result as Resource.Success).data)
        }

    @Test
    fun `invoke should return Resource Failure with AroundEgyptException when repository throws AroundEgyptException`() =
        runTest {
            // Arrange
            val experienceId = "123"
            val expectedException =
                AroundEgyptException.Network.UnknownHost(errorMessage = "Unknown host")
            whenever(repository.getExperienceFromLocal(experienceId)).thenAnswer { throw (expectedException) }

            // Act
            val result = getExperienceFromLocalUC(experienceId)

            // Assert
            assertTrue(result is Resource.Failure)
            assertEquals(expectedException, (result as Resource.Failure).exception)
        }

    @Test
    fun `invoke should return Resource Failure with UnknownException when repository throws unknown exception`() =
        runTest {
            // Arrange
            val experienceId = "123"
            val unknownException = RuntimeException("Unknown error")
            whenever(repository.getExperienceFromLocal(experienceId)).thenThrow(unknownException)

            // Act
            val result = getExperienceFromLocalUC(experienceId)

            // Assert
            assertTrue(result is Resource.Failure)
            assertTrue((result as Resource.Failure).exception is AroundEgyptException.UnknownException)
            assertEquals(
                "Unknown error in GetExperienceFromLocalUC: $unknownException",
                result.exception.message
            )
        }
}