package com.example.booklistcomposedemo.utils

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    class Success<out T>(val data: T) : Resource<T>()
    class Error(val errorMsg: String?) : Resource<Nothing>()
}