package com.example.booklistcomposedemo.presentaion.booksearch

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.booklistcomposedemo.ui.DetailsScreen
import com.example.booklistcomposedemo.ui.common_compose.BookItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookSearchCore(viewModel: SearchViewModel = koinViewModel(), myNavController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        BookSearch(viewModel.detailsScreenState.collectAsStateWithLifecycle().value, {
            viewModel.actions(SearchActions.BookSearch(it))
        }, {
            myNavController.popBackStack()
            myNavController.navigate(DetailsScreen(it))
        })
    }
}

@Composable
fun BookSearch(searchScreenState: SearchScreenState, onSearch: (String) -> Unit, onDetails: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .border(1.dp, Color.Gray,RoundedCornerShape(20.dp)),
            value = searchScreenState.searchText,
            onValueChange = {
                    onSearch(it)
            },
            placeholder = {Text("Search here...")}
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (searchScreenState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        if (searchScreenState.bookList.isNotEmpty()) {
            //TODO no books found case
        }
        if (searchScreenState.bookList.isNotEmpty()) {
            LazyColumn(flingBehavior = ScrollableDefaults.flingBehavior()) {
                items(searchScreenState.bookList) { book ->
                    key(book.id) {
                        BookItem(book) {
                            onDetails(it)
                        }
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun BookSearchPreview() {
    BookSearch(SearchScreenState(false, "search", emptyList()), {}, {})
}