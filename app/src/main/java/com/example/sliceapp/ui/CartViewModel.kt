package com.example.sliceapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.sliceapp.data.model.Item

class CartViewModel : ViewModel() {
    val cartItems = mutableStateListOf<Item>()

    fun addToCart(item: Item) {
        cartItems.add(item)
    }

    fun removeFromCart(item: Item) {
        cartItems.remove(item)
    }
}