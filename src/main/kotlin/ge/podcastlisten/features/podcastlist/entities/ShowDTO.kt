package ge.podcastlisten.features.podcastlist.entities

import kotlinx.serialization.Serializable

@Serializable
data class ShowDTO(
    val `data`: DataDTO
)