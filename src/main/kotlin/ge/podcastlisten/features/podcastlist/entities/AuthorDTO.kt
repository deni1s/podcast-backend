package ge.podcastlisten.features.podcastlist.entities

import kotlinx.serialization.Serializable

@Serializable
data class AuthorDTO(
    val id: String,
    val name: String,
    val imageUrl: String
)