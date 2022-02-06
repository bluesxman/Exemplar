package com.example.exemplar.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exemplar.client.spotify.Item
import com.example.exemplar.client.spotify.searchAlbums
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(): ViewModel() {
    val albums = MutableLiveData<List<Item>>(listOf())
    val query = MutableLiveData("")
    // TODO: add showSpinner as a state that the screen will observe

    fun search(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = searchAlbums(query)
            albums.postValue(response.albums.items)
        }
    }

    fun onQueryChange(q: String) {
        query.value = if(q.endsWith("\n")) {
            search(q.trim())
            q.trim()
        } else {
            q
        }
    }
}