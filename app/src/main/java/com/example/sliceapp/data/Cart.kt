package com.example.sliceapp.data

import androidx.compose.runtime.mutableStateListOf
import com.example.sliceapp.data.model.Item

object Cart {
    private val _items = mutableStateListOf<Item>()
    val items: List<Item> = _items

    fun addItem(item: Item) {
        if (!_items.contains(item)) {
            _items.add(item)
        }
    }

    fun removeItem(item: Item) {
        _items.remove(item)
    }
}