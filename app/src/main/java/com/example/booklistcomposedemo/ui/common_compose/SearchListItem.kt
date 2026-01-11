package com.example.booklistcomposedemo.ui.common_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.booklistcomposedemo.data.Book

@Composable
fun BookItem(book: Book, onBookClicked: (String) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            ImageRequest.Builder(LocalContext.current)
                .data(book.imageUrl)
                .size(100)
                .crossfade(true)
                .build(),
            book.name,
            Modifier.height(100.dp).aspectRatio(3f/4f)
        )

        Spacer(modifier = Modifier.width(5.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(book.name, maxLines = 1, overflow = TextOverflow.Clip)
            Text(book.description, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Text("${book.rating}(${book.reviewCount})")
        }
        Spacer(modifier = Modifier.width(5.dp))
        Button(onClick = {
            onBookClicked(book.id)
        }) {
            Text("Details")
        }
    }
}
