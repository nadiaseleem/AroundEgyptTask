package com.example.aroundegypt.features.home.di

import com.example.aroundegypt.common.data.repository.local.database.ExperiencesDao
import com.example.aroundegypt.common.domain.internet_connection_handler.IInternetConnectivityChecker
import com.example.aroundegypt.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.aroundegypt.features.home.data.repository.ExperiencesRepository
import com.example.aroundegypt.features.home.data.repository.local.ExperiencesLocalDS
import com.example.aroundegypt.features.home.data.repository.remote.ExperiencesRemoteDS
import com.example.aroundegypt.features.home.domain.repository.IExperiencesRepository
import com.example.aroundegypt.features.home.domain.repository.local.IExperiencesLocalDS
import com.example.aroundegypt.features.home.domain.repository.remote.IExperiencesRemoteDS
import com.example.aroundegypt.features.home.domain.usecases.GetExperienceFromLocalUC
import com.example.aroundegypt.features.home.domain.usecases.GetExperienceUC
import com.example.aroundegypt.features.home.domain.usecases.GetLikedExperiencesUC
import com.example.aroundegypt.features.home.domain.usecases.GetMostRecentExperiencesUC
import com.example.aroundegypt.features.home.domain.usecases.GetRecommendedExperiencesUC
import com.example.aroundegypt.features.home.domain.usecases.LikeExperienceUC
import com.example.aroundegypt.features.home.domain.usecases.SaveLikedExperiencesUC
import com.example.aroundegypt.features.home.domain.usecases.SearchExperiencesUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ExperiencesModule {

    @Provides
    fun provideExperiencesLocalDS(
        experiencesDao: ExperiencesDao
    ): IExperiencesLocalDS {
        return ExperiencesLocalDS(experiencesDao = experiencesDao)
    }

    @Provides
    fun provideExperiencesRemoteDS(
        networkProvider: IRestApiNetworkProvider
    ): IExperiencesRemoteDS {
        return ExperiencesRemoteDS(networkProvider = networkProvider)
    }

    @Provides
    fun provideExperiencesRepository(
        remoteDs: IExperiencesRemoteDS,
        localDs: IExperiencesLocalDS,
        internetConnectionHandler: IInternetConnectivityChecker
    ): IExperiencesRepository {
        return ExperiencesRepository(
            remoteDS = remoteDs,
            localDS = localDs,
            connectivityChecker = internetConnectionHandler
        )
    }

    @Provides
    fun provideGetRecommendedExperiencesUC(experiencesRepository: IExperiencesRepository): GetRecommendedExperiencesUC {
        return GetRecommendedExperiencesUC(repository = experiencesRepository)
    }

    @Provides
    fun provideGetMostRecentExperiencesUC(experiencesRepository: IExperiencesRepository): GetMostRecentExperiencesUC {
        return GetMostRecentExperiencesUC(repository = experiencesRepository)
    }

    @Provides
    fun provideSearchExperiencesUC(experiencesRepository: IExperiencesRepository): SearchExperiencesUC {
        return SearchExperiencesUC(repository = experiencesRepository)
    }

    @Provides
    fun provideLikeExperienceUC(experiencesRepository: IExperiencesRepository): LikeExperienceUC {
        return LikeExperienceUC(repository = experiencesRepository)
    }

    @Provides
    fun provideGetLikedExperiencesUC(experiencesRepository: IExperiencesRepository): GetLikedExperiencesUC {
        return GetLikedExperiencesUC(repository = experiencesRepository)
    }

    @Provides
    fun provideSaveLikedExperiencesUC(experiencesRepository: IExperiencesRepository): SaveLikedExperiencesUC {
        return SaveLikedExperiencesUC(repository = experiencesRepository)
    }

    @Provides
    fun provideGetExperienceUC(experiencesRepository: IExperiencesRepository): GetExperienceUC {
        return GetExperienceUC(repository = experiencesRepository)
    }

    @Provides
    fun provideGetExperienceFromLocalUC(experiencesRepository: IExperiencesRepository): GetExperienceFromLocalUC {
        return GetExperienceFromLocalUC(repository = experiencesRepository)
    }
}