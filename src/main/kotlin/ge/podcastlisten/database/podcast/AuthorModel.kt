package ge.podcastlisten.database.podcast

import ge.podcastlisten.features.podcastlist.entities.AuthorDTO
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object AuthorModel: Table("authors") {
    private val id = AuthorModel.varchar("id", 25).uniqueIndex()
    private val title = AuthorModel.varchar("title", 50)
    private val imageUrl = AuthorModel.varchar("imageUrl", 256)
    private val isPopular = AuthorModel.bool("isPopular")

    fun fetchAuthors(count: Int): List<AuthorDTO> {
        return transaction {
            mapToDtoModel(AuthorModel.selectAll().sortedBy { AuthorModel.title }.take(count))
        }
    }

    fun fetchAllAuthors(offset: Long, limit: Int): List<AuthorDTO> {
        return transaction {
            mapToDtoModel(AuthorModel.selectAll().limit(limit, offset).sortedBy { AuthorModel.title })
        }
    }

    fun fetchPopularAuthors(): List<AuthorDTO> {
        return transaction {
            val authors = AuthorModel.select { isPopular.eq(true) }
            authors.map {
                AuthorDTO(
                    id = it[AuthorModel.id],
                    name = it[title],
                    imageUrl = it[imageUrl],
                )
            }
        }
    }

    private fun mapToDtoModel(podcasts: List<ResultRow>): List<AuthorDTO> {
        return podcasts.map {
            AuthorDTO(
                id = it[AuthorModel.id],
                name = it[title],
                imageUrl = it[imageUrl],
            )
        }
    }
}