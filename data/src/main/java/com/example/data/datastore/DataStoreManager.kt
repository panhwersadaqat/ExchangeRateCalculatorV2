package com.example.data.datastore

import TypeReference
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
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
    @ApplicationContext val context: Context
) {

    @Throws(JSONException::class)
    suspend fun putObject(key: Preferences.Key<String>, `object`: Any) {
        context.dataStore.edit {
            it[stringPreferencesKey("$key")]
        }
    }

    inline fun <reified T> getObject(key: Preferences.Key<String>) = context.dataStore.data.map {
        it[stringPreferencesKey("$key")]
    }.map {

    }.flowOn(Dispatchers.IO)

    inline fun <reified T> getList(key: Preferences.Key<String>) = context.dataStore.data.map {
        it[stringPreferencesKey("$key")]
    }.map {
        val type = object : TypeReference<T>() {}.type
    }.flowOn(Dispatchers.IO)


    suspend fun clear() {
        context.dataStore.edit {
            it.clear()
        }
    }
}



