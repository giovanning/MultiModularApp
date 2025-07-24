package com.projetos.filmei.protodatastore.factory

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.projetos.filmei.proto.Preferences
import com.projetos.filmei.proto.Session
import com.projetos.filmei.protodatastore.serializer.PreferencesSerializer
import com.projetos.filmei.protodatastore.serializer.SessionSerializer

val Context.sessionDataStore: DataStore<Session> by dataStore(
    fileName = "session.pb",
    serializer = SessionSerializer,
)

val Context.preferencesDataStore: DataStore<Preferences> by dataStore(
    fileName = "preferences.pb",
    serializer = PreferencesSerializer,
)
