package com.example.exemplar.client.spotify

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val albums: Albums
)

@Serializable
data class Albums(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: String?,
    val total: Int
)

@Serializable
data class Item(
    val album_type: String,
    val artists: List<Artist>,
    val available_markets: List<String>,
    val external_urls: ExternalUrlsX,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val type: String,
    val uri: String
)

@Serializable
data class Artist(
    val external_urls: ExternalUrls,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)

@Serializable
data class ExternalUrlsX(
    val spotify: String
)

@Serializable
data class Image(
    val height: Int,
    val url: String,
    val width: Int
)

@Serializable
data class ExternalUrls(
    val spotify: String
)