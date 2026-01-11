package com.example.booklistcomposedemo.presentaion.bookList

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.booklistcomposedemo.data.Book
import com.example.booklistcomposedemo.ui.DetailsScreen
import com.example.booklistcomposedemo.ui.SearchScreen
import com.example.booklistcomposedemo.ui.common_compose.BookItem
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.viewmodel.koinActivityViewModel

@Composable
fun BookListCore(viewModel: HomeViewModel = koinActivityViewModel(), myNavController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        BookList(viewModel.homeScreenState.collectAsStateWithLifecycle().value, {
            viewModel.actions(it)
        }, {
            myNavController.navigate(SearchScreen)
        }, {
            myNavController.navigate(DetailsScreen(it))
        })
    }
}

@Composable
fun BookList(state: BookListScreenState, onAction: (BookListActions) -> Unit, onSearch: () -> Unit, onDetails: (String) -> Unit) {
    LaunchedEffect(Unit) {
        onAction(BookListActions.LoadBooks)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Gray)
            .padding(10.dp)
            .clickable(true) {
                onSearch()
            }
    ) {
        Text("Search Book", Modifier.background(Color.Transparent))
    }
    if (state.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
    if (state.bookList.isNotEmpty()) {
        //TODO no books found case
    }
    if (state.bookList.isNotEmpty()) {
        LazyColumn(flingBehavior = ScrollableDefaults.flingBehavior()) {
            items(state.bookList) { book ->
                key(book.id) {
                    BookItem(book) {
                        onDetails(it)
                    }
                }
            }
        }
    }

}


@Preview
@Composable
private fun HomePreview() {
//    BookList(BookListScreenState(), {}, {}, {})
    BookItem(Book("1","My Name","Me","","desc",2.0f,1)){}
}