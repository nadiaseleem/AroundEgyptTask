package com.example.aroundegypt.common.domain.internet_connection_handler

interface IInternetConnectivityChecker {
    fun isInternetAvailable(): Boolean
}