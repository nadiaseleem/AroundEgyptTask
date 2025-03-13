package com.example.aroundegypt.common.data.repository.local.database

import androidx.room.TypeConverter
import com.example.aroundegypt.features.home.data.models.entity.ExperienceEntity
import com.example.aroundegypt.features.home.data.models.entity.ReviewEntity
import com.example.aroundegypt.features.home.data.models.entity.TagEntity
import com.example.aroundegypt.features.home.data.models.entity.TicketPriceEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromExperiencesList(value: List<ExperienceEntity>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toExperiencesList(value: String): List<ExperienceEntity> {
        val listType = object : TypeToken<List<ExperienceEntity>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromDoubleList(value: List<Double>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toDoubleList(value: String): List<Double> {
        val type = object : TypeToken<List<Double>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromReviewEntityList(reviews: List<ReviewEntity>?): String {
        return gson.toJson(reviews)
    }

    @TypeConverter
    fun toReviewEntityList(value: String): List<ReviewEntity> {
        val type = object : TypeToken<List<ReviewEntity>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromTagEntityList(tags: List<TagEntity>?): String {
        return gson.toJson(tags)
    }

    @TypeConverter
    fun toTagEntityList(value: String): List<TagEntity> {
        val type = object : TypeToken<List<TagEntity>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromTicketPriceEntityList(prices: List<TicketPriceEntity>?): String {
        return gson.toJson(prices)
    }

    @TypeConverter
    fun toTicketPriceEntityList(value: String): List<TicketPriceEntity> {
        val type = object : TypeToken<List<TicketPriceEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}