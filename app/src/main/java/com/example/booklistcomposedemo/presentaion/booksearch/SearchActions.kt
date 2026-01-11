package com.example.booklistcomposedemo.presentaion.booksearch

sealed class SearchActions {
    class BookSearch(val title: String): SearchActions()
}