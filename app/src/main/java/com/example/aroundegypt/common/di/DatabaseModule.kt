package com.example.aroundegypt.common.di

import android.content.Context
import androidx.room.Room
import com.example.aroundegypt.common.data.repository.local.database.ExperiencesDao
import com.example.aroundegypt.common.data.repository.local.database.ExperiencesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "experiences_database"

    @Provides
    @Singleton
    fun provideExperiencesDatabase(@ApplicationContext context: Context): ExperiencesDatabase {
        return Room.databaseBuilder(
            context,
            ExperiencesDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideExperiencesDao(database: ExperiencesDatabase): ExperiencesDao {
        return database.experiencesDao()
    }
}