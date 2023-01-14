package ge.podcastlisten.features.podcastlist.entities

import kotlinx.serialization.Serializable

@Serializable
data class GenreDTO(
    val id: String,
    val name: String,
    val imageUrl: String
)