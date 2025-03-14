package com.example.aroundegypt.common.data.repository.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aroundegypt.features.home.data.models.entity.ExperienceEntity

@Dao
interface ExperiencesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveExperiences(experiences: List<ExperienceEntity>)

    @Query("select * from ExperienceEntity where recommended=1")
    suspend fun getRecommendedExperiences(): List<ExperienceEntity>

    @Query("select * from ExperienceEntity")
    suspend fun getMostRecentExperiences(): List<ExperienceEntity>

    @Query("select * from ExperienceEntity where title like '%' || :query || '%'")
    suspend fun searchExperiences(query: String): List<ExperienceEntity>

    @Query("update ExperienceEntity set is_liked=1 , likes_no=:updatedLikeNo where id=:id")
    suspend fun likeExperience(id: String, updatedLikeNo: Int)

    @Query("select * from ExperienceEntity where is_liked = 1")
    suspend fun getLikedExperiences(): List<ExperienceEntity>
}