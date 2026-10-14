package com.example.sliceapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.sliceapp.data.model.Item
import com.example.sliceapp.data.cafes
import com.example.sliceapp.data.groceries
import com.example.sliceapp.data.meals
import com.example.sliceapp.navigation.Screen
import com.example.sliceapp.ui.CartViewModel
import com.example.sliceapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, cartViewModel: CartViewModel = viewModel()) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Meals", "Groceries", "Cafés")

    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text("Good morning! 👋", fontSize = 14.sp, color = Gray)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.LocationOn, contentDescription = "Location", tint = Orange, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Kuala Lumpur, MY", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                 },
                actions = {
                    BadgedBox(badge = { if(cartViewModel.cartItems.isNotEmpty()) Badge(containerColor = Red) { Text("${cartViewModel.cartItems.size}") } }) {
                        IconButton(onClick = { navController.navigate(Screen.Cart.route) }) {
                            Icon(painter = painterResource(id = R.drawable.ic_cart), contentDescription = "Cart")
                        }
                    }
                    IconButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile",
                            modifier = Modifier.size(28.dp).clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Background)
            )
        },
        containerColor = Background
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search for food...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White, focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent
                )
            )
            
            TabRow(
                selectedTabIndex = selectedTabIndex, 
                containerColor = Background,
                contentColor = Orange,
                indicator = { tabPositions ->
                    TabRowDefaults.PrimaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = Orange
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = title) },
                        selectedContentColor = Orange,
                        unselectedContentColor = Color.Gray
                    )
                }
            }

            when (selectedTabIndex) {
                0 -> ItemList(navController = navController, items = meals, cartViewModel = cartViewModel)
                1 -> ItemList(navController = navController, items = groceries, cartViewModel = cartViewModel)
                2 -> ItemList(navController = navController, items = cafes, cartViewModel = cartViewModel)
            }
        }
    }
}

@Composable
fun ItemList(navController: NavController, items: List<Item>, cartViewModel: CartViewModel) {
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(items) { item ->
            FoodItemCard(item = item, cartViewModel = cartViewModel, onClick = { navController.navigate(Screen.Details.createRoute(item.name)) })
        }
    }
}

@Composable
fun FoodItemCard(item: Item, cartViewModel: CartViewModel, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White), onClick = onClick) {
        Column {
            Box {
                Image(painter = painterResource(id = item.image), contentDescription = item.name, modifier = Modifier.height(150.dp).fillMaxWidth(), contentScale = ContentScale.Crop)
                if (item.expiration.contains("Today") || item.expiration.contains("Tomorrow")) {
                    Text(
                        text = "Exp Soon!",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .background(Red, RoundedCornerShape(bottomEnd = 8.dp))
                            .padding(horizontal = 6.dp, vertical = 4.dp)
                            .align(Alignment.TopStart)
                    )
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = item.name, fontWeight = FontWeight.Bold)
                Text(text = item.restaurant, fontSize = 14.sp, color = Color.Gray)
                Text(text = item.expiration, fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Text(text = "RM${item.price}", color = Green, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "RM${item.originalPrice}", color = Color.Gray, fontSize = 14.sp, textDecoration = TextDecoration.LineThrough)
                    }
                    Text(text = item.discount, color = Orange, fontWeight = FontWeight.Bold, modifier = Modifier.background(Orange.copy(alpha = 0.1f), RoundedCornerShape(4.dp)).padding(horizontal = 6.dp, vertical = 2.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { cartViewModel.addToCart(item) }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Green)) {
                    Text("Order Now", color = Color.White)
                }
            }
        }
    }
}
