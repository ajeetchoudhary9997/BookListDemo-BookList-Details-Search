package com.example.booklistcomposedemo.presentaion.bookList

import com.example.booklistcomposedemo.data.Book

data class BookListScreenState(
    var isLoading: Boolean = false,
    var bookList: List<Book> = emptyList()
) {
}