package com.example.booklistcomposedemo.presentaion.bookList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklistcomposedemo.domain.GetBooksUseCase
import com.example.booklistcomposedemo.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(val getBooksUseCase: GetBooksUseCase) : ViewModel() {
    private val _homeScreenState = MutableStateFlow(BookListScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()


    fun actions(actions: BookListActions) {
        when (actions) {
            BookListActions.LoadBooks -> loadBooks()
        }

    }

    private fun loadBooks() {
        viewModelScope.launch {
            getBooksUseCase().collect { response ->
                when (response) {
                    is Resource.Error -> {
                        //todo handle failure here
                    }
                    is Resource.Loading -> {
                        _homeScreenState.value = _homeScreenState.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        _homeScreenState.value=_homeScreenState.value.copy(isLoading = false, bookList = response.data)
                    }
                }
            }
        }
    }

}