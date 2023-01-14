package ge.podcastlisten.features.podcastlist

import ge.podcastlisten.database.podcast.AuthorModel
import ge.podcastlisten.database.podcast.BannerModel
import ge.podcastlisten.database.podcast.GenreModel
import ge.podcastlisten.database.podcast.PodcastModel
import ge.podcastlisten.features.podcastlist.entities.PodcastDTO
import io.ktor.server.application.*
import io.ktor.server.response.*

private const val PODCAST_ITEMS_LIMIT = 20

class PodcastController(private val call: ApplicationCall) {

    suspend fun createWave(offset: Long) {
        val authors = AuthorModel.fetchAuthors(2)
        val podcastList = mutableListOf<PodcastDTO>()
        authors.forEach { podcastList.addAll(PodcastModel.fetchPodcastsByAuthorId(it.id, PODCAST_ITEMS_LIMIT, offset)) }
        call.respond(podcastList)
    }

    suspend fun getAuthorPodcasts(authorId: String, offset: Long) {
        call.respond(PodcastModel.fetchAllPodcastsAuthorId(authorId, PODCAST_ITEMS_LIMIT, offset))
    }

    suspend fun getGenrePodcasts(genreId: String, offset: Long) {
        call.respond(PodcastModel.fetchPodcastsGenreId(genreId, PODCAST_ITEMS_LIMIT, offset))
    }

    suspend fun getBanners() {
        val banners = BannerModel.fetchAll()
        call.respond(banners)
    }

    suspend fun getAllGenres(offset: Int, limit: Int) {
        val genres = GenreModel.fetchGenres(offset.toLong(), limit)
        call.respond(genres)
    }

    suspend fun getAllAuthors(offset: Int, limit: Int) {
        val genres = AuthorModel.fetchAllAuthors(offset.toLong(), limit)
        call.respond(genres)
    }

    suspend fun getPopularAuthors() {
        val genres = AuthorModel.fetchPopularAuthors()
        call.respond(genres)
    }

    suspend fun getPopularGenres() {
        val genres = GenreModel.fetchPopularGenres()
        call.respond(genres)
    }
}