package com.example.aroundegypt.common.data.internet_connection_Handler

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.aroundegypt.common.domain.internet_connection_handler.IInternetConnectivityChecker

class InternetConnectivityChecker(private val context: Context) : IInternetConnectivityChecker {
    override fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}