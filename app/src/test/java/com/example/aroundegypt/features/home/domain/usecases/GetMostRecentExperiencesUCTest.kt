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

class GetMostRecentExperiencesUCTest {

    @Mock
    private lateinit var repository: IExperiencesRepository

    private lateinit var getMostRecentExperiencesUC: GetMostRecentExperiencesUC

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getMostRecentExperiencesUC = GetMostRecentExperiencesUC(repository)
    }

    @Test
    fun `invoke should emit Loading followed by Success when repository returns data`() = runTest {
        // Arrange
        val recentExperiences = listOf(
            experience.copy(id = "1", title = "Pyramids Tour"),
            experience.copy(id = "2", title = "Nile Cruise")
        )
        whenever(repository.getMostRecentExperiences()).thenReturn(recentExperiences)

        // Act & Assert
        getMostRecentExperiencesUC().test {
            // Loading state
            assertTrue(awaitItem() is Resource.Loading)

            // Success state
            val success = awaitItem()
            assertTrue(success is Resource.Success)
            assertEquals(recentExperiences, (success as Resource.Success).data)

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
        val unknownHostException = UnknownHostException("Unknown host")
        whenever(repository.getMostRecentExperiences()).thenAnswer { throw (unknownHostException) }

        // Act & Assert
        getMostRecentExperiencesUC().test {
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
        val socketTimeoutException = SocketTimeoutException("Timeout")
        whenever(repository.getMostRecentExperiences()).thenThrow(socketTimeoutException)

        // Act & Assert
        getMostRecentExperiencesUC().test {
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
        val unknownException = RuntimeException("Unknown error")
        whenever(repository.getMostRecentExperiences()).thenThrow(unknownException)

        // Act & Assert
        getMostRecentExperiencesUC().test {
            // Loading state
            assertTrue(awaitItem() is Resource.Loading)

            // Failure state
            val failure = awaitItem()
            assertTrue(failure is Resource.Failure)
            assertTrue((failure as Resource.Failure).exception is AroundEgyptException.UnknownException)
            assertEquals(
                "Unknown error in GetMostRecentExperiencesUC: $unknownException",
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