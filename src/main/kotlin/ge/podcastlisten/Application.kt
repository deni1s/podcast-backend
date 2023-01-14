package ge.podcastlisten

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import ge.podcastlisten.features.podcastlist.configurePodcastRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.netty.*
import ge.podcastlisten.plugins.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig

fun main() {
    val config = HikariConfig("hikari.properties")
    val dataSource = HikariDataSource(config)
    Database.connect(dataSource)

    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        configurePodcastRouting()
        configureSerialization()
    }.start(wait = true)
}