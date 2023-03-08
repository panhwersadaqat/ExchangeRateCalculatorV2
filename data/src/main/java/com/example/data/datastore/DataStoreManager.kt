package com.example.data.datastore

import TypeReference
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.data.fromJson
import com.data.toJson
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.json.JSONException
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DATASTORE")

@Singleton
class DataStoreManager
@Inject constructor(
    val moshi: Moshi,
    @ApplicationContext val context: Context
) {

    @Throws(JSONException::class)
    suspend fun putObject(key: Preferences.Key<String>, `object`: Any) {
        var json = moshi.toJson(`object`) ?: ""
        context.dataStore.edit {
            it[stringPreferencesKey("$key")] = json
        }
    }

    inline fun <reified T> getObject(key: Preferences.Key<String>) = context.dataStore.data.map {
        it[stringPreferencesKey("$key")]
    }.map {
        moshi.fromJson<T>(it ?: "")
    }.flowOn(Dispatchers.IO)

    inline fun <reified T> getList(key: Preferences.Key<String>) = context.dataStore.data.map {
        it[stringPreferencesKey("$key")]
    }.map {
        val type = object : TypeReference<T>() {}.type
        moshi.fromJson<T>(it ?: "", type) as T?
    }.flowOn(Dispatchers.IO)


    suspend fun clear() {
        context.dataStore.edit {
            it.clear()
        }
    }
}



