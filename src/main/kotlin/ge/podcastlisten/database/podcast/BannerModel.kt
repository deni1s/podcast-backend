package ge.podcastlisten.database.podcast

import ge.podcastlisten.database.podcast.GenreModel.uniqueIndex
import ge.podcastlisten.features.podcastlist.entities.BannerDto
import ge.podcastlisten.features.podcastlist.entities.PodcastDTO
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object BannerModel : Table("banners") {
    private val id = BannerModel.varchar("id", 50).uniqueIndex()
    private val image_url = BannerModel.varchar("imageUrl", 256)
    private val type = BannerModel.varchar("type", 10)

    fun fetchAll(): List<BannerDto> {
        return transaction {
            val podcasts =
                BannerModel.selectAll()
            podcasts.map {
                BannerDto(
                    id = it[BannerModel.id],
                    imageUrl = it[image_url],
                    type = it[type]
                )
            }
        }
    }
}