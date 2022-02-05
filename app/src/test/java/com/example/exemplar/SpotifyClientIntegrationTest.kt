package com.example.exemplar

import com.example.exemplar.client.spotify.searchAlbums
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SpotifyClientIntegrationTest {
    @Test
    fun searchAlbumWorks() {
        runBlocking {
            val response = searchAlbums("Moon")
            assert(response.albums.items.isNotEmpty())
        }
    }
}