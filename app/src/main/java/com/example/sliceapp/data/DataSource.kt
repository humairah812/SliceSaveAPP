package com.example.sliceapp.data

import com.example.sliceapp.R
import com.example.sliceapp.data.model.Item

val meals = listOf(
    Item("Margherita Pizza (2 Slices)", "Tony's Pizza House", "0.8 km away", 9.99, 25.99, expiration = "Expires in 2 hours", discount = "62% OFF",image = R.drawable.pizzaa),
    Item("Fresh Garden Salad Bowl", "Green & Grain", "1.5 km away", 7.99, 19.99, image = R.drawable.salad, expiration = "Expires in 1 hour", discount = "60% OFF"),
    Item("Chicken Biryani Bowl", "Spice Route Kitchen", "2.0 km away", 12.99, 28.99, expiration = "Expires in 3 hours", discount = "55% OFF",image = R.drawable.biriyani),
    Item("Nasi Lemak", "Warung Kita", "1.2 km away", 5.50, 12.00, expiration = "Expires in 2 hours", discount = "54% OFF",image = R.drawable.nasilemak),
    Item("Spaghetti Carbonara", "Pasta Perfect", "3.5 km away", 15.00, 32.00, expiration = "Expires in 4 hours",image = R.drawable.carbonara, discount = "53% OFF "),
    Item("Classic Beef Burger", "Burger Joint", "0.5 km away", 10.00, 22.00, expiration = "Expires in 1 hour",image = R.drawable.burger, discount = "55% OFF")
)
val groceries = listOf(
    Item("Fresh Eggs (10 pcs)", "Farm Fresh Market", "2.1 km away", 4.90, 11.50, expiration = "Expires Tomorrow",image = R.drawable.eggs, discount = "57% OFF"),
    Item("Cheddar Cheese Block (200g)", "Dairy Delights", "2.3 km away", 7.90, 16.90, expiration = "Expires Today",image = R.drawable.cheese, discount = "53% OFF"),
    Item("Red Apples (1kg)", "Fresh Harvest", "2.5 km away", 6.50, 14.50, expiration = "Expires Tomorrow",image = R.drawable.apples, discount = "55% OFF"),
    Item("Whole Milk (1L)", "Daily Dairy", "1.8 km away", 3.50, 7.00, expiration = "Expires in 2 days",image = R.drawable.milk, discount = "50% OFF"),
    Item("Loaf of Bread", "Bakery Bliss", "1.9 km away", 2.50, 5.50, expiration = "Expires Today", image = R.drawable.bread,discount = "55% OFF"),
    Item("Bananas (1 bunch)", "Fruit Fanatic", "2.2 km away", 4.00, 8.50, expiration = "Expires Tomorrow", image = R.drawable.banana,discount = "53% OFF")
)
val cafes = listOf(
    Item("Cappuccino (Grande)", "Beans & Cream Cafe", "1.2 km away", 5.90, 14.90, expiration = "Expires in 2 hours",image = R.drawable.cappicino, discount = "60% OFF"),
    Item("Chocolate Cake Slice", "Sweet Moments Bakery", "2.5 km away", 6.90, 16.50, expiration = "Expires in 4 hours", image = R.drawable.cake,discount = "58% OFF"),
    Item("Club Sandwich", "The Corner Cafe", "3.1 km away", 7.50, 18.90, expiration = "Expires in 1 hour", image = R.drawable.sandwich,discount = "60% OFF"),
    Item("Iced Latte", "Cool Beans", "0.9 km away", 6.00, 13.00, expiration = "Expires in 1 hour", image = R.drawable.latte,discount = "54% OFF"),
    Item("Blueberry Muffin", "Muffin Man", "1.5 km away", 4.50, 9.00, expiration = "Expires in 3 hours",image = R.drawable.muffin, discount = "50% OFF"),
    Item("Americano", "Daily Grind", "2.8 km away", 5.00, 11.00, expiration = "Expires in 2 hours", image = R.drawable.coffee,discount = "55% OFF")
)
