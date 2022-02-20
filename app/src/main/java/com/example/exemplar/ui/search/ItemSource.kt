package com.example.exemplar.ui.search

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.exemplar.client.spotify.Item
import com.example.exemplar.client.spotify.moreResults
import com.example.exemplar.client.spotify.searchAlbums
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


//sealed class ItemPagingKey {
//    data class New(val query: String) : ItemPagingKey()
//    data class More(val url: String) : ItemPagingKey()
//    object None : ItemPagingKey()
//
//}

class ItemSource(
    val query: String
): PagingSource<String, Item>() {
    private val refreshIndex = mapOf<Item, String>()

    override fun getRefreshKey(state: PagingState<String, Item>): String {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.let { item ->
                refreshIndex[item]
            }
        } ?: query
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Item> {
        val key = params.key ?: return LoadResult.Error(Throwable("No query / url"))
        if (key == "") return LoadResult.Error(Throwable("Query is empty string"))

        // Does ktor client use IO scope automatically?
        val response = when (params) {
            is LoadParams.Refresh -> {
                searchAlbums(key)
            }
            is LoadParams.Append -> {
                moreResults(key)
            }
            is LoadParams.Prepend -> {
                moreResults(key)
            }
        }
        return LoadResult.Page(
            data = response.albums.items,
            prevKey = response.albums.previous,
            nextKey = response.albums.next
        )
    }
}
//
//class ItemSource: PagingSource<ItemPagingKey, Item>() {
//    override fun getRefreshKey(state: PagingState<ItemPagingKey, Item>): ItemPagingKey {
//        return state.anchorPosition
//    }
//
//    override suspend fun load(params: LoadParams<ItemPagingKey>): LoadResult<ItemPagingKey, Item> {
//        val key = params.key
//        val l = listOf<String>()
//        if (key is ItemPagingKey.More) {
//            val response = moreResults(key.url)
//            return LoadResult.Page(
//                data = response.albums.items,
//                prevKey = moreOf(response.albums.previous),
//                nextKey = moreOf(response.albums.next)
//            )
//        }
//    }
//
//    private fun moreOf(url: String?) = url?.let { ItemPagingKey.More(url) }
//
//}