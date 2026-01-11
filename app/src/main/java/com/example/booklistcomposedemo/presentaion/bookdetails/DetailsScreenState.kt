package com.example.booklistcomposedemo.presentaion.bookdetails

import com.example.booklistcomposedemo.data.Book

data class DetailsScreenState(
    var isLoading: Boolean = false,
    var book: Book? = null
) {
}