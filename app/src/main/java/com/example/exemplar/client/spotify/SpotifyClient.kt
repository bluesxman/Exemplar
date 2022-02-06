package com.example.exemplar.client.spotify

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

val tokenClient = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}

val client = HttpClient(CIO) {

    defaultRequest {
        host = "api.spotify.com"
//        header(HttpHeaders.Authorization, "Bearer ${System.getenv()["SPOTIFY_TOKEN"]}")
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        header(HttpHeaders.Accept, ContentType.Application.Json)
    }

    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }

    install(Auth) {
        lateinit var tokenInfo: TokenInfo

        bearer {
            loadTokens {
                tokenInfo = tokenClient.submitForm(
                    url = "https://accounts.spotify.com/api/token",
                    formParameters = Parameters.build {
                        append("grant_type", "client_credentials")
                        append("client_id", clientId)
                        append("client_secret", clientSecret)
                    }
                )
                BearerTokens(
                    accessToken = tokenInfo.access_token,
                    refreshToken = tokenInfo.access_token!!
                )
            }
        }
    }
}

suspend fun searchAlbums(query: String): SearchResponse {
    return client.get(path = "/v1/search") {
        parameter("q", query)
        parameter("type", "album")
        parameter("include_external", "audio")
    }
}