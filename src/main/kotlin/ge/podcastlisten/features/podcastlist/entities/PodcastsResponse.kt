package ge.podcastlisten.features.podcastlist.entities

data class PodcastsResponse(
    val data: List<PodcastDTO>,
    val meta: EpisodesMetaDto
)