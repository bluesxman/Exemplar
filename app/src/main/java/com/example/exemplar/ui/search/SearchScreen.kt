package com.example.exemplar.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.Pager
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.exemplar.R
import com.example.exemplar.client.spotify.Item

@Composable
fun SearchScreen(viewModel: SearchViewModel = SearchViewModel()) {
    val results = viewModel.albums.collectAsLazyPagingItems()
    val query by viewModel.query.observeAsState("")

    SearchContent(query = query, items = results, onSearch = viewModel::onQueryChange)
}

@Composable
fun SearchContent(query: String, items: LazyPagingItems<Item>, onSearch: (String) -> Unit) {
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
        label = { Text("Search albums") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(R.color.purple_500),
            unfocusedBorderColor = colorResource(R.color.purple_200)
        )
    )
}

@Composable
fun SearchResultsContent(items: LazyPagingItems<Item>) {

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.purple_200)),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items) { item ->
            if (item != null) {
                SearchItemContent(item = item)
            } else {
                Text("I'm a paging placeholder")
            }
        }
    }
}

@Composable
fun SearchItemContent(item: Item) {
    Card(modifier = Modifier.fillMaxWidth().shadow(4.dp).height(100.dp)) {
        Text(item.name, fontSize = 30.sp)
    }
}