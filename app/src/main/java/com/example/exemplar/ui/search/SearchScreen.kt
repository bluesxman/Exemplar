package com.example.exemplar.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.example.exemplar.client.spotify.Item

@Composable
fun SearchScreen(viewModel: SearchViewModel = SearchViewModel()) {
    val results by viewModel.albums.observeAsState(listOf())
    val query by viewModel.query.observeAsState("")

    SearchContent(query = query, items = results, onSearch = viewModel::onQueryChange)
}

@Composable
fun SearchContent(query: String, items: List<Item>, onSearch: (String) -> Unit) {
    Column {
        SearchFieldContent(query = query, onSearch = onSearch)
        SearchResultsContent(items = items)
    }
}

@Composable
fun SearchFieldContent(query: String, onSearch: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onSearch,
        label = { Text("Search albums") }
    )
}

@Composable
fun SearchResultsContent(items: List<Item>) {

}

@Composable
fun SearchItemContent(item: Item) {

}