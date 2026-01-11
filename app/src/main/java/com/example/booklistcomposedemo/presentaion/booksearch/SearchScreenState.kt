package com.example.booklistcomposedemo.presentaion.booksearch

import com.example.booklistcomposedemo.data.Book

data class SearchScreenState(
    var isLoading: Boolean = false,
    var searchText: String = "",
    var bookList: List<Book> = emptyList()
) {
}