package com.example.booklistcomposedemo.presentaion.bookdetails

sealed class DetailActions {
    class BookDetails(val bookId: String): DetailActions()
}