package com.example.exemplar.client.spotify

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*

val client = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
    defaultRequest {
        host = "api.spotify.com"
        header(HttpHeaders.Authorization, "Bearer ${System.getenv()["SPOTIFY_TOKEN"]}")
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        header(HttpHeaders.Accept, ContentType.Application.Json)
    }
}

suspend fun searchAlbums(query: String): SearchResponse {
    return client.get(path = "/v1/search") {
        parameter("q", query)
        parameter("type", "album")
        parameter("include_external", "audio")
    }
}