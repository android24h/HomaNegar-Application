package com.example.myapplication.presentation.event

sealed class ProductEvent {
    data class Success(val message: String) : ProductEvent()
    data class DeleteSuccess(val message: String): ProductEvent()
    data class Error(val message: String): ProductEvent()

}