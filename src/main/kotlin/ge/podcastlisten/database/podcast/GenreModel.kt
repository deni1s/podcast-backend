package ge.podcastlisten.database.podcast

import ge.podcastlisten.features.podcastlist.entities.AuthorDTO
import ge.podcastlisten.features.podcastlist.entities.GenreDTO
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object GenreModel: Table("genres") {
    private val id = GenreModel.varchar("id", 25).uniqueIndex()
    private val title = GenreModel.varchar("title", 50)
    private val imageUrl = GenreModel.varchar("imageUrl", 256)
    private val isPopular = GenreModel.bool("isPopular")

    fun fetchGenres(): List<GenreDTO> {
        return transaction {
            val genres = GenreModel.selectAll().sortedBy { GenreModel.title }
            genres.map {
                 GenreDTO(
                    id = it[GenreModel.id],
                    name = it[title],
                    imageUrl = it[imageUrl],
                )
            }
        }
    }

    fun fetchGenres(offset: Long, limit: Int): List<GenreDTO> {
        return transaction {
            val genres = GenreModel.selectAll().limit(limit, offset).sortedBy { GenreModel.title }
            genres.map {
                GenreDTO(
                    id = it[GenreModel.id],
                    name = it[title],
                    imageUrl = it[imageUrl],
                )
            }
        }
    }

    fun fetchPopularGenres(): List<GenreDTO> {
        return transaction {
            val authors = GenreModel.select { GenreModel.isPopular.eq(true) }
            authors.map {
                GenreDTO(
                    id = it[GenreModel.id],
                    name = it[GenreModel.title],
                    imageUrl = it[GenreModel.imageUrl],
                )
            }
        }
    }
}