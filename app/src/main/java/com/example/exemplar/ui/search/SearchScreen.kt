package com.example.exemplar.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.exemplar.client.spotify.Item

@Composable
fun SearchScreen(viewModel: SearchViewModel = SearchViewModel()) {
    val results by viewModel.albums.observeAsState(listOf())

    SearchContent(items = results, onSearch = viewModel::search)
}

@Composable
fun SearchContent(items: List<Item>, onSearch: (String) -> Unit) {
    Column {
        SearchFieldContent(onSearch = onSearch)
        SearchResultsContent(items = items)
    }
}

@Composable
fun SearchFieldContent(onSearch: (String) -> Unit) {
    OutlinedTextField(
        value = "",
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