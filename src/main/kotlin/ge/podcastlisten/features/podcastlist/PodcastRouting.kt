package ge.podcastlisten.features.podcastlist

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configurePodcastRouting() {

    routing {
        get("/banners") {
            PodcastController(this.call).getBanners()
        }
        get("/podcasts") {
            val genreId = call.parameters["genreId"]
            val authorId = call.parameters["authorId"]
            val offset = call.parameters["offset"]?.toLong()
            when {
                genreId == "wave" -> PodcastController(this.call).createWave(offset ?: 0)
                genreId != null -> PodcastController(this.call).getGenrePodcasts(genreId, offset?: 0)
                authorId != null -> PodcastController(this.call).getAuthorPodcasts(authorId, offset?: 0)
            }
        }
        get("/genres") {
            val offset = call.parameters["offset"]!!.toInt()
            val limit = call.parameters["limit"]!!.toInt()

            PodcastController(this.call).getAllGenres(offset, limit)
        }
        get("/authors") {
            val offset = call.parameters["offset"]!!.toInt()
            val limit = call.parameters["limit"]!!.toInt()

            PodcastController(this.call).getAllAuthors(offset, limit)
        }
        get("/popular-authors") {
            PodcastController(this.call).getPopularAuthors()
        }
        get("/popular-genres") {
            PodcastController(this.call).getPopularGenres()
        }
    }
}