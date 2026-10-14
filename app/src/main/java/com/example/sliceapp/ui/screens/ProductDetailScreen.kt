package com.example.sliceapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sliceapp.R
import com.example.sliceapp.data.cafes
import com.example.sliceapp.data.groceries
import com.example.sliceapp.data.meals
import com.example.sliceapp.navigation.Screen
import com.example.sliceapp.ui.CartViewModel
import com.example.sliceapp.ui.theme.Background
import com.example.sliceapp.ui.theme.Green
import com.example.sliceapp.ui.theme.LightGray
import com.example.sliceapp.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(navController: NavController, itemName: String?, cartViewModel: CartViewModel = viewModel()) {
    val allItems = meals + groceries + cafes
    val item = allItems.find { it.name == itemName } ?: return

    var quantity by remember { mutableIntStateOf(1) }
    val total = item.price * quantity
    val saved = (item.originalPrice - item.price) * quantity

    Box(modifier = Modifier.fillMaxSize().background(Background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Image Section
            Box(modifier = Modifier.height(300.dp)) {
                Image(
                    painter = painterResource(id = item.image),
                    contentDescription = item.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // Details Section
            Column {
                // Restaurant Info Card
                Box(
                    modifier = Modifier
                        .offset(y = (-40).dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_restaurant),
                                contentDescription = "Restaurant",
                                modifier = Modifier
                                    .background(Green.copy(alpha = 0.1f), CircleShape)
                                    .padding(8.dp)
                                    .size(24.dp),
                                tint = Green
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Restaurant", color = Color.Gray, fontSize = 12.sp)
                                Text(item.restaurant, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            }
                            Icon(painter = painterResource(id = R.drawable.ic_clock), contentDescription = "Time", tint = Orange)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("2 hours", color = Orange, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                // Main Product Details
                Column(modifier = Modifier.padding(horizontal = 16.dp).offset(y = (-20).dp)) {
                    Text(item.name, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(id = R.drawable.ic_location), contentDescription = "Location", tint = Color.Gray, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("0.8 km away", color = Color.Gray, fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("•", color = Color.Gray, fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Ready in 15 mins", color = Color.Gray, fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("RM${String.format("%.2f", item.price)}", color = Green, fontWeight = FontWeight.Bold, fontSize = 28.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("RM${String.format("%.2f", item.originalPrice)}", color = Color.Gray, fontSize = 16.sp, textDecoration = TextDecoration.LineThrough)
                        Spacer(modifier = Modifier.width(8.dp))
                        Card(shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Green.copy(alpha = 0.1f))){
                            Text("Save RM${String.format("%.2f", item.originalPrice - item.price)}", color = Green, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 12.sp)
                        }
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = LightGray)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Quantity", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Card(shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = Background)) {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(4.dp)) {
                                IconButton(onClick = { if (quantity > 1) quantity-- }, modifier = Modifier.size(32.dp)) {
                                    Icon(painter = painterResource(id = R.drawable.ic_minus), contentDescription = "Minus", tint = Color.Gray)
                                }
                                Text("$quantity", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 8.dp))
                                Button(onClick = { quantity++ }, modifier = Modifier.size(32.dp), shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(containerColor = Orange), contentPadding = PaddingValues(0.dp)) {
                                    Icon(painter = painterResource(id = R.drawable.ic_plus), contentDescription = "Plus", tint = Color.White)
                                }
                            }
                        }
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = LightGray)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("Total Amount", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Column(horizontalAlignment = Alignment.End) {
                            Text("RM${String.format("%.2f", total)}", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Green)
                            Text("You save RM${String.format("%.2f", saved)}", color = Color.Gray, fontSize = 12.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        // Top Bar Overlay
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.background(Color.Black.copy(alpha = 0.3f), CircleShape)) {
                Icon(painter = painterResource(id = R.drawable.ic_back_arrow), contentDescription = "Back", tint = Color.White)
            }
            Card(shape = CircleShape, colors = CardDefaults.cardColors(containerColor = Orange)){
                Text(item.discount, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
            }
        }

        // Bottom Button
        Button(
            onClick = {
                repeat(quantity) {
                    cartViewModel.addToCart(item)
                }
                navController.navigate(Screen.Cart.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Orange)
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_cart), contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Add to Cart", fontSize = 18.sp, color = Color.White)
        }
    }
}
