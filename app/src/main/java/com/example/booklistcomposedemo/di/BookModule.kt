package com.example.booklistcomposedemo.di

import com.example.booklistcomposedemo.domain.GetBookByTitleUseCase
import com.example.booklistcomposedemo.domain.GetBookUseCase
import com.example.booklistcomposedemo.domain.GetBooksUseCase
import com.example.booklistcomposedemo.presentaion.bookList.HomeViewModel
import com.example.booklistcomposedemo.presentaion.bookdetails.DetailsViewModel
import com.example.booklistcomposedemo.presentaion.booksearch.SearchViewModel
import com.example.booklistcomposedemo.repository.BooksRepository
import com.example.booklistcomposedemo.repository.BooksRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val mainModule= module {
    singleOf(::BooksRepositoryImpl) { bind<BooksRepository>() }
}
val useCaseModule=module {
    factoryOf(::GetBookUseCase)
    factoryOf(::GetBooksUseCase)
    factoryOf(::GetBookByTitleUseCase)
}
val viewModelModule=module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::SearchViewModel)
}