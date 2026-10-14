package com.example.sliceapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sliceapp.R
import com.example.sliceapp.data.model.Item
import com.example.sliceapp.ui.CartViewModel
import com.example.sliceapp.ui.theme.Background
import com.example.sliceapp.ui.theme.Green
import com.example.sliceapp.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, cartViewModel: CartViewModel = viewModel()) {
    val cartItems = cartViewModel.cartItems
    val subtotal = cartItems.sumOf { it.price }
    val deliveryFee = 5.99
    val discount = subtotal * 0.05
    val total = subtotal + deliveryFee - discount

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Cart (${cartItems.size} items)") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.ic_back_arrow), contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Background)
            )
        },
        containerColor = Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (cartItems.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Your cart is empty.", fontSize = 20.sp, color = Color.Gray)
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(cartItems) { item ->
                        CartItem(item = item, onRemove = { cartViewModel.removeFromCart(item) })
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        RewardCard()
                    }
                }
                PriceSummaryRow("Subtotal", "RM${String.format("%.2f", subtotal)}")
                PriceSummaryRow("Delivery Fee", "RM${String.format("%.2f", deliveryFee)}")
                PriceSummaryRow("Rescue Discount (5%)", "-RM${String.format("%.2f", discount)}", isDiscount = true)
                Spacer(modifier = Modifier.height(8.dp))
                PriceSummaryRow("Total", "RM${String.format("%.2f", total)}", isTotal = true)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* TODO: Implement Checkout */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Green)
                ) {
                    Text("Proceed to Checkout", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
fun CartItem(item: Item, onRemove: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = item.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(horizontal = 8.dp).weight(1f)) {
                Text(item.name, fontWeight = FontWeight.Bold)
                Text(item.restaurant, fontSize = 12.sp, color = Color.Gray)
                Text(item.expiration, fontSize = 12.sp, color = Color.Gray)
            }
            IconButton(onClick = onRemove) {
                Icon(painter = painterResource(id = R.drawable.ic_delete), contentDescription = "Delete")
            }
        }
    }
}

@Composable
fun RewardCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = LightGray),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_meals_saved), contentDescription = "Reward")
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text("Rescue Reward Applied!", fontWeight = FontWeight.Bold)
                Text("Extra 5% off for saving food", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text("-RM0.50", color = Green, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PriceSummaryRow(label: String, value: String, isDiscount: Boolean = false, isTotal: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = if (isDiscount) Green else Color.Gray)
        Text(value, fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal, fontSize = if (isTotal) 20.sp else 16.sp)
    }
}
