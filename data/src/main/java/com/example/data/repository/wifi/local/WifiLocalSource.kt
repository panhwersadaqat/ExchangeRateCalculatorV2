package com.example.data.repository.wifi.local

import com.example.data.datastore.DataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
class WifiLocalSource @Inject constructor(
    private val dataStore: DataStoreManager
) {
    suspend fun saveWifiInfo(obj: WifiInfoRequest){
        dataStore.putObject(PreferenceKeys.WIFI_CRED,obj)
    }

    suspend fun getWifiInfo(): Flow<WifiInfoRequest?> {
        return  dataStore.getObject<WifiInfoRequest>(PreferenceKeys.WIFI_CRED)
    }
}*/
