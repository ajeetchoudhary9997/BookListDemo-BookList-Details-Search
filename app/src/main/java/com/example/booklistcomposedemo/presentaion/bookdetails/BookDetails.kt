package com.example.booklistcomposedemo.presentaion.bookdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BookDetailsCore(id: String, viewModel: DetailsViewModel = koinViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.actions(DetailActions.BookDetails(id))
    }
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        BookDetails(viewModel.detailsScreenState.collectAsStateWithLifecycle().value)
    }

}

@Composable
fun BookDetails(bookDetailState: DetailsScreenState) {
    if (bookDetailState.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
    bookDetailState.book?.let { book ->
        Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(book.imageUrl).size(200).crossfade(true).build(),
                    contentDescription = book.name,
                    modifier = Modifier
                        .height(200.dp)
                        .aspectRatio(3f / 4f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(book.name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            Text(book.description)

        }
    }
}