package com.projetos.filmei.datastore.settings

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val language: Language = Language.ENGLISH,
    @Serializable(with = PersistentListLocationSerializer::class)
    val lastKnownLocations: PersistentList<Location> = persistentListOf(),
)

@Serializable
data class Location(
    val lat: Double,
    val lng: Double,
)

enum class Language {
    ENGLISH,
    PORTUGUESE,
}
