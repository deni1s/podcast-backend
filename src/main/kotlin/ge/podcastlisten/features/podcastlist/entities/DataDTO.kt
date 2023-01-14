package ge.podcastlisten.features.podcastlist.entities

import kotlinx.serialization.Serializable

@Serializable
data class DataDTO(
    val id: String,
    val type: String
)