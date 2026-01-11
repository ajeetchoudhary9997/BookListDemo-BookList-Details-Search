package com.example.booklistcomposedemo.domain

import com.example.booklistcomposedemo.data.Book
import com.example.booklistcomposedemo.repository.BooksRepository
import com.example.booklistcomposedemo.utils.Resource
import kotlinx.coroutines.flow.flow


class GetBooksUseCase (val booksRepository: BooksRepository) {
    operator fun invoke() = flow<Resource<List<Book>>> {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(booksRepository.getBooks()))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}