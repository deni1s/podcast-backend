package ge.podcastlisten.database.podcast

import ge.podcastlisten.features.podcastlist.entities.PodcastDTO
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.transactions.transaction

object PodcastModel : Table("podcasts") {
    private val id = PodcastModel.varchar("id", 50).uniqueIndex()
    private val title = PodcastModel.varchar("title", 50)
    private val description = PodcastModel.varchar("description", 50)
    private val number = PodcastModel.integer("number")
    private val lastListenTime = PodcastModel.date("lastListenTime")
    private val author = PodcastModel.varchar("author", 50)
    private val authorId = PodcastModel.varchar("authorId", 25)
    private val duration_in_sec = PodcastModel.integer("durationInSec")
    private val media_url = PodcastModel.varchar("mediaUrl", 256)
    private val createdAt = PodcastModel.date("createdAt")
    private val genreId = PodcastModel.varchar("genreId", 25)
    private val genreTitle = PodcastModel.varchar("genreTitle", 50)
    private val image_url = PodcastModel.varchar("imageUrl", 256)

    fun fetchPodcastsByAuthorId(id: String, limit: Int, offset: Long): List<PodcastDTO> {
        return transaction {
            val podcasts =
                PodcastModel.select { authorId.eq(id) }.limit(limit, offset).sortedBy { createdAt }
            podcasts.map {
                PodcastDTO(
                    id = it[PodcastModel.id],
                    title = it[title],
                    description = it[description],
                    number = it[number],
                    lastListenTime = it[lastListenTime].toString(),
                    author = it[author],
                    authorId = it[authorId],
                    durationInSec = it[duration_in_sec],
                    mediaUrl = it[media_url],
                    createdAt = it[createdAt].toString(),
                    imageUrl = it[image_url]
                )
            }
        }
    }

    fun fetchAllPodcastsAuthorId(id: String, limit: Int, offset: Long): List<PodcastDTO> {
        return transaction {
            val podcasts =
                PodcastModel.select { authorId.eq(id) }.limit(limit, offset).sortedBy { createdAt }
            podcasts.map {
                PodcastDTO(
                    id = it[PodcastModel.id],
                    title = it[title],
                    description = it[description],
                    number = it[number],
                    lastListenTime = it[lastListenTime].toString(),
                    author = it[author],
                    authorId = it[authorId],
                    durationInSec = it[duration_in_sec],
                    mediaUrl = it[media_url],
                    createdAt = it[createdAt].toString(),
                    imageUrl = it[image_url]
                )
            }
        }
    }

    fun fetchPodcastsGenreId(id: String, limit: Int, offset: Long): List<PodcastDTO> {
        return transaction {
            val podcasts =
                PodcastModel.select { genreId.eq(id) }.limit(limit, offset).sortedBy { createdAt }
            podcasts.map {
                PodcastDTO(
                    id = it[PodcastModel.id],
                    title = it[title],
                    description = it[description],
                    number = it[number],
                    lastListenTime = it[lastListenTime].toString(),
                    author = it[author],
                    authorId = it[authorId],
                    durationInSec = it[duration_in_sec],
                    mediaUrl = it[media_url],
                    createdAt = it[createdAt].toString(),
                    imageUrl = it[image_url]
                )
            }
        }
    }
}