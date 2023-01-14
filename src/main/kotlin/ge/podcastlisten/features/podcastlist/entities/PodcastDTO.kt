package ge.podcastlisten.features.podcastlist.entities

import kotlinx.serialization.Serializable

@Serializable
data class PodcastDTO(
    val id: String,
    val title: String,
    val description: String?,
    val number: Int,
    val lastListenTime: String,
    val createdAt: String,
    val author: String,
    val authorId: String,
    val durationInSec: Int,
    val mediaUrl: String,
    val imageUrl: String
)