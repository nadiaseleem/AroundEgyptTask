package com.example.aroundegypt.features.home.domain.usecases

import app.cash.turbine.test
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
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class GetExperienceUCTest {

    @Mock
    private lateinit var repository: IExperiencesRepository

    private lateinit var getExperienceUC: GetExperienceUC

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getExperienceUC = GetExperienceUC(repository)
    }

    @Test
    fun `invoke should emit Loading followed by Success when repository returns data`() = runTest {
        // Arrange
        val experienceId = "123"
        val expectedExperience = experience.copy(id = experienceId, title = "Pyramids Tour")
        whenever(repository.getExperienceById(experienceId)).thenReturn(expectedExperience)

        // Act & Assert
        getExperienceUC(experienceId).test {
            // Loading state
            assertTrue(awaitItem() is Resource.Loading)

            // Success state
            val success = awaitItem()
            assertTrue(success is Resource.Success)
            assertEquals(expectedExperience, (success as Resource.Success).data)

            // Loading completion
            val loadingCompletion = awaitItem()
            assertTrue(loadingCompletion is Resource.Loading)
            assertEquals(false, (loadingCompletion as Resource.Loading).loading)

            awaitComplete() // Flow completes
        }
    }

    @Test
    fun `invoke should emit Loading followed by Failure with UnknownHostException`() = runTest {
        // Arrange
        val experienceId = "123"
        val unknownHostException = UnknownHostException("Unknown host")
        whenever(repository.getExperienceById(experienceId)).thenAnswer { throw (unknownHostException) }

        // Act & Assert
        getExperienceUC(experienceId).test {
            // Loading state
            assertTrue(awaitItem() is Resource.Loading)

            // Failure state
            val failure = awaitItem()
            assertTrue(failure is Resource.Failure)
            assertTrue((failure as Resource.Failure).exception is AroundEgyptException.Network.UnknownHost)
            assertEquals("Unknown host", failure.exception.message)

            // Loading completion
            val loadingCompletion = awaitItem()
            assertTrue(loadingCompletion is Resource.Loading)
            assertEquals(false, (loadingCompletion as Resource.Loading).loading)

            awaitComplete() // Flow completes
        }
    }

    @Test
    fun `invoke should emit Loading followed by Failure with SocketTimeoutException`() = runTest {
        // Arrange
        val experienceId = "123"
        val socketTimeoutException = SocketTimeoutException("Timeout")
        whenever(repository.getExperienceById(experienceId)).thenAnswer { throw socketTimeoutException }

        // Act & Assert
        getExperienceUC(experienceId).test {
            // Loading state
            assertTrue(awaitItem() is Resource.Loading)

            // Failure state
            val failure = awaitItem()
            assertTrue(failure is Resource.Failure)
            assertTrue((failure as Resource.Failure).exception is AroundEgyptException.Network.Timeout)
            assertEquals("Timeout", failure.exception.message)

            // Loading completion
            val loadingCompletion = awaitItem()
            assertTrue(loadingCompletion is Resource.Loading)
            assertEquals(false, (loadingCompletion as Resource.Loading).loading)

            awaitComplete() // Flow completes
        }
    }

    @Test
    fun `invoke should emit Loading followed by Failure with UnknownException`() = runTest {
        // Arrange
        val experienceId = "123"
        val unknownException = RuntimeException("Unknown error")
        whenever(repository.getExperienceById(experienceId)).thenThrow(unknownException)

        // Act & Assert
        getExperienceUC(experienceId).test {
            // Loading state
            assertTrue(awaitItem() is Resource.Loading)

            // Failure state
            val failure = awaitItem()
            assertTrue(failure is Resource.Failure)
            assertTrue((failure as Resource.Failure).exception is AroundEgyptException.UnknownException)
            assertEquals(
                "Unknown error in GetExperienceUC: $unknownException",
                failure.exception.message
            )

            // Loading completion
            val loadingCompletion = awaitItem()
            assertTrue(loadingCompletion is Resource.Loading)
            assertEquals(false, (loadingCompletion as Resource.Loading).loading)

            awaitComplete() // Flow completes
        }
    }
}