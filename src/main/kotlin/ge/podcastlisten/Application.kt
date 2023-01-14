package ge.podcastlisten

import ge.podcastlisten.features.podcastlist.configurePodcastRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import ge.podcastlisten.plugins.*
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/podcast-listener", driver = "org.postgresql.Driver", user = "postgres", password = "22")
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configurePodcastRouting()
    configureSerialization()
}
