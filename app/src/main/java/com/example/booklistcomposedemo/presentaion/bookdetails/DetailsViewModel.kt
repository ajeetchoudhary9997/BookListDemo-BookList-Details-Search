package com.example.booklistcomposedemo.presentaion.bookdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklistcomposedemo.domain.GetBookUseCase
import com.example.booklistcomposedemo.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(val getBookUseCase: GetBookUseCase) : ViewModel() {
    private val _detailsScreenState = MutableStateFlow(DetailsScreenState())
    val detailsScreenState = _detailsScreenState.asStateFlow()


    fun actions(actions: DetailActions) {
        when (actions) {
            is DetailActions.BookDetails->{
                loadBookDetail(actions.bookId)
            }
        }

    }

    private fun loadBookDetail(id:String) {
        viewModelScope.launch {
            getBookUseCase(id).collect { response ->
                when (response) {
                    is Resource.Error -> {
                        //todo handle failure here
                    }
                    is Resource.Loading -> {
                        _detailsScreenState.value = _detailsScreenState.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        _detailsScreenState.value=_detailsScreenState.value.copy(isLoading = false, book = response.data)
                    }
                }
            }
        }
    }

}