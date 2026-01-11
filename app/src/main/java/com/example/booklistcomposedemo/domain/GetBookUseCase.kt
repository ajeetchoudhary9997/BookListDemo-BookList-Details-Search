package com.example.booklistcomposedemo.domain

import com.example.booklistcomposedemo.data.Book
import com.example.booklistcomposedemo.repository.BooksRepository
import com.example.booklistcomposedemo.utils.Resource
import kotlinx.coroutines.flow.flow

class GetBookUseCase (val booksRepository: BooksRepository) {
    operator fun invoke(id: String) = flow<Resource<Book>> {
        emit(Resource.Loading)
        try {
            booksRepository.getBook(id)?.let {
                emit(Resource.Success(it))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}