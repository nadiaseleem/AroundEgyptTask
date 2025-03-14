package com.example.aroundegypt.features.home.domain.usecases


import app.cash.turbine.test
import com.example.aroundegypt.common.data.models.exception.AroundEgyptException
import com.example.aroundegypt.common.data.models.state.Resource
import com.example.aroundegypt.features.home.domain.repository.IExperiencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@OptIn(ExperimentalCoroutinesApi::class)
class LikeExperienceUCTest {

    private lateinit var repository: IExperiencesRepository
    private lateinit var likeExperienceUC: LikeExperienceUC
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        likeExperienceUC = LikeExperienceUC(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should emit Loading, Success, and Loading(false) on successful like`() = runTest {
        // Arrange
        val query = "experienceId"
        val updatedLikeNo = 5
        whenever(repository.likeExperience(query)).thenReturn(updatedLikeNo)

        // Act & Assert
        likeExperienceUC(query).test {
            assertEquals(Resource.Loading(), awaitItem()) // Loading state
            assertEquals(Resource.Success(updatedLikeNo), awaitItem()) // Success state
            assertEquals(Resource.Loading(false), awaitItem()) // Loading(false) state
            awaitComplete()
        }
    }

    @Test
    fun `invoke should emit Loading and Failure on UnknownHostException`() = runTest {
        // Arrange
        val query = "experienceId"
        val exception = UnknownHostException("No internet connection")
        whenever(repository.likeExperience(query)).thenAnswer { throw (exception) }

        // Act & Assert
        likeExperienceUC(query).test {
            assertEquals(Resource.Loading(), awaitItem()) // Loading state
            val failure = awaitItem() as Resource.Failure
            assertEquals(
                AroundEgyptException.Network.UnknownHost("No internet connection"),
                failure.exception
            )
            assertEquals(Resource.Loading(false), awaitItem()) // Loading(false) state
            awaitComplete()
        }
    }

    @Test
    fun `invoke should emit Loading and Failure on SocketTimeoutException`() = runTest {
        // Arrange
        val query = "experienceId"
        val exception = SocketTimeoutException("Request timed out")
        whenever(repository.likeExperience(query)).thenAnswer { throw (exception) }

        // Act & Assert
        likeExperienceUC(query).test {
            assertEquals(Resource.Loading(), awaitItem()) // Loading state
            val failure = awaitItem() as Resource.Failure
            assertEquals(
                AroundEgyptException.Network.Timeout("Request timed out"),
                failure.exception
            )
            assertEquals(Resource.Loading(false), awaitItem()) // Loading(false) state
            awaitComplete()
        }
    }

    @Test
    fun `invoke should emit Loading and Failure on generic exception`() = runTest {
        // Arrange
        val query = "experienceId"
        val exception = RuntimeException("Something went wrong")
        whenever(repository.likeExperience(query)).thenThrow(exception)

        // Act & Assert
        likeExperienceUC(query).test {
            assertEquals(Resource.Loading(), awaitItem()) // Loading state
            val failure = awaitItem() as Resource.Failure
            assertEquals(
                AroundEgyptException.UnknownException("Unknown error in LikeExperienceUC: $exception"),
                failure.exception
            )
            assertEquals(Resource.Loading(false), awaitItem()) // Loading(false) state
            awaitComplete()
        }
    }
}