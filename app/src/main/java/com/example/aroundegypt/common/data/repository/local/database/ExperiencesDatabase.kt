package com.example.aroundegypt.common.data.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.aroundegypt.features.home.data.models.entity.ExperienceEntity
import com.example.aroundegypt.features.home.data.models.entity.ExperiencesResponseEntity

@Database(
    entities = [
        ExperiencesResponseEntity::class,
        ExperienceEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ExperiencesDatabase : RoomDatabase() {
    abstract fun experiencesDao(): ExperiencesDao
}