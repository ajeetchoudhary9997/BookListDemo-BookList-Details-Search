package com.example.booklistcomposedemo.domain

import com.example.booklistcomposedemo.data.Book
import com.example.booklistcomposedemo.repository.BooksRepository
import com.example.booklistcomposedemo.utils.Resource
import kotlinx.coroutines.flow.flow

class GetBookByTitleUseCase(val booksRepository: BooksRepository) {
    operator fun invoke(title: String) = flow<Resource<List<Book>>> {
        emit(Resource.Loading)
        try {
            booksRepository.searchBook(title).let {
                emit(Resource.Success(it))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}