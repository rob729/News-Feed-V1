package com.robin.news30.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.techyourchance.dagger2course.common.dependnecyinjection.app.AppScoped
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AppScoped
class PreferenceRepository @Inject constructor(context: Context) {

    private val dataStore = context.createDataStore(
        name = "app_preference"
    )

    private val keyAppLaunchCounter =
        preferencesKey<String>(name = "news_source")

    suspend fun setNewsSource(source: String) {
        dataStore.edit { preferences ->
            preferences[keyAppLaunchCounter] = source
        }
    }

    fun getNewsSource() = dataStore.data.map { preferences ->
        preferences[keyAppLaunchCounter] ?: "the-verge"
    }

}