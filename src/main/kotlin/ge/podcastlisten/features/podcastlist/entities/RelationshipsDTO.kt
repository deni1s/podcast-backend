package ge.podcastlisten.features.podcastlist.entities

import kotlinx.serialization.Serializable

@Serializable
data class RelationshipsDTO(
    val show: ShowDTO
)