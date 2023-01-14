package ge.podcastlisten.features.podcastlist.entities

data class ShowsResponse(
    val data: List<String>,
    val meta: EpisodesMetaDto
)