package com.keshav.internproject.utlies

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo


class ConnectionManager {
//        fun checkConectivity(context: Context) : Boolean {
//            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            val activeNetwork : NetworkInfo? = connectivityManager.activeNetworkInfo
//            if(activeNetwork?.isConnected != null){
//                return activeNetwork.isConnected
//            }
//            else{
//                return false
//            }

//        }
    fun checkConectivity(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }
    }
