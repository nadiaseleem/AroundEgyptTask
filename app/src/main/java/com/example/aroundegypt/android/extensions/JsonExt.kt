package com.example.aroundegypt.android.extensions

import com.google.gson.Gson
import java.lang.reflect.Type

fun <T> T.toJson(): String = Gson().toJson(this)

fun <T> String.fromJson(type: Type): T {
    return Gson().fromJson(this, type)
}