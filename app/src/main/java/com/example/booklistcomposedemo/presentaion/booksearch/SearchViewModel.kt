package com.example.booklistcomposedemo.presentaion.booksearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklistcomposedemo.domain.GetBookByTitleUseCase
import com.example.booklistcomposedemo.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SearchViewModel(val getBookByTitleUseCase: GetBookByTitleUseCase) : ViewModel() {
    private val _detailsScreenState = MutableStateFlow(SearchScreenState())
    val detailsScreenState = _detailsScreenState.asStateFlow()
    private val _searchText = MutableStateFlow("")

    init {
        viewModelScope.launch {
            _searchText.debounce(300).distinctUntilChanged().collect {
                searchBookDetail(it)
            }
        }
    }

    fun actions(actions: SearchActions) {
        when (actions) {
            is SearchActions.BookSearch -> {
                _detailsScreenState.value = _detailsScreenState.value.copy(searchText = actions.title)
                _searchText.value = actions.title
            }
        }
    }

    private fun searchBookDetail(title: String) {
        if (title.isEmpty())
            _detailsScreenState.value = _detailsScreenState.value.copy(isLoading = false, bookList = emptyList())
        else
            viewModelScope.launch {
                getBookByTitleUseCase(title).collect { response ->
                    when (response) {
                        is Resource.Error -> {
                            //todo handle failure here
                        }

                        is Resource.Loading -> {
                            _detailsScreenState.value = _detailsScreenState.value.copy(isLoading = true)
                        }

                        is Resource.Success -> {
                            _detailsScreenState.value = _detailsScreenState.value.copy(isLoading = false, bookList = response.data)
                        }
                    }
                }
            }
    }

}