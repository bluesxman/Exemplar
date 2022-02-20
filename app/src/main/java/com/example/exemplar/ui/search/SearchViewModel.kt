package com.example.exemplar.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.exemplar.client.spotify.Item
import kotlinx.coroutines.flow.Flow

class SearchViewModel(): ViewModel() {
    val query = MutableLiveData("moon")
    var albums: Flow<PagingData<Item>> = initPager()

    // TODO: add showSpinner as a state that the screen will observe

    private fun initPager(): Flow<PagingData<Item>> {
        val q = query.value?.trim() ?: ""
        return Pager(PagingConfig(pageSize = 20)) {
            ItemSource(q)
        }.flow.cachedIn(viewModelScope)
    }

    fun onQueryChange(q: String) {
        if (q.isNotBlank()) {
            query.value = q.trim()
        }
        query.value = if(q.endsWith("\n")) {
            initPager()
            q.trim()
        } else {
            q
        }
    }
}