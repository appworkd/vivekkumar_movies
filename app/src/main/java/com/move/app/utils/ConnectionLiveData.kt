package com.move.app.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

class ConnectionLiveData(context: Context) : LiveData<Boolean>() {

    private lateinit var networkCallBack: ConnectivityManager.NetworkCallback
    private val connectivityManager =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager


    override fun onInactive() {
        connectivityManager.unregisterNetworkCallback(networkCallBack)
    }

    override fun onActive() {
        networkCallBack = checkNetworkAvailability()
        val networkRequest = NetworkRequest.Builder().addCapability(NET_CAPABILITY_INTERNET).build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallBack)

    }

    private fun checkNetworkAvailability() = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            val hasInternetCapabilities =
                networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET)

            if (hasInternetCapabilities == true) {
                this@ConnectionLiveData.postValue(true)
            }
        }

        override fun onLost(network: Network) {
            this@ConnectionLiveData.postValue(false)
        }
    }
}