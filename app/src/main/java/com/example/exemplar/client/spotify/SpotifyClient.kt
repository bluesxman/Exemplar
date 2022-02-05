package com.example.exemplar.client.spotify

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*

val client = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}

suspend fun searchAlbums(query: String): SearchResponse {
    return client.get("https://api.spotify.com/v1/search") {
        header(HttpHeaders.Authorization, "Bearer ${System.getenv()["SPOTIFY_TOKEN"]}")
        parameter("q", query)
        parameter("type", "album")
        parameter("include_external", "audio")
    }
}