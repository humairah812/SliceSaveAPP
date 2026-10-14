package com.example.sliceapp.data.model

import androidx.annotation.DrawableRes
import com.example.sliceapp.R

data class Item(
    val name: String,
    val restaurant: String,
    val location: String,
    val price: Double,
    val originalPrice: Double,
    @DrawableRes val image: Int = R.drawable.pizzaa,
    val expiration: String,
    val discount: String
)
