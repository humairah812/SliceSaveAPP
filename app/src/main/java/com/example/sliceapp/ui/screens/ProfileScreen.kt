package com.example.sliceapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sliceapp.R
import com.example.sliceapp.navigation.Screen
import com.example.sliceapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold(containerColor = Background) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .background(Orange)
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Humairah Shaik", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text("humairah@168", color = Color.White.copy(alpha = 0.8f))
                        Spacer(modifier = Modifier.height(8.dp))
                        Chip(label = "1,245 Points", icon = R.drawable.ic_money_saved)
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .offset(y = (-50).dp)
                        .padding(horizontal = 16.dp)
                ) {
                    RewardsCard()
                    Spacer(modifier = Modifier.height(16.dp))
                    EnvironmentalImpactCard()
                    Spacer(modifier = Modifier.height(16.dp))
                    MenuItems(navController) { showLogoutDialog = true }
                    Spacer(modifier = Modifier.height(16.dp))
                    TogetherWeveSavedCard()
                }
            }
        }
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Logout") },
            text = { Text("Are you sure you want to logout?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        navController.navigate(Screen.Login.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    }
                ) {
                    Text("Logout")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun Chip(label: String, icon: Int) {
    Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.2f))) {
        Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = icon), contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(label, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun RewardsCard() {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text("Your Rewards", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                Icon(painter = painterResource(id = R.drawable.ic_edit), contentDescription = "Edit Rewards", tint = Orange, modifier = Modifier.size(20.dp))
            }
            Text("100 points = RM 1 off", fontSize = 12.sp, color = Gray)
            Divider(modifier = Modifier.padding(vertical = 8.dp), color = LightGray)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Available Discount", color = Gray)
                    Text("RM 12 OFF", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Orange)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("From", color = Gray, fontSize = 12.sp)
                    Text("1,245 points", fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Use your discount on your next order! ✨", fontSize = 12.sp, color = Gray, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun EnvironmentalImpactCard() {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.ic_meals_saved), contentDescription = null, tint = Green, modifier = Modifier.size(28.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Environmental Impact", fontWeight = FontWeight.Bold)
            }
            Text("Your contribution to sustainability", fontSize = 12.sp, color = Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                ImpactItem(iconRes = R.drawable.ic_meals_saved, value = "42", label = "Meals Saved")
                ImpactItem(iconRes = R.drawable.ic_waste_prevented, value = "28kg", label = "Waste Prevented")
                ImpactItem(iconRes = R.drawable.ic_money_saved, value = "RM 125", label = "Money Saved")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                Text("Member since", color=Gray)
                Text("January 2024", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ImpactItem(iconRes: Int, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(painter = painterResource(id = iconRes), contentDescription = label, modifier = Modifier.size(32.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = label, color = Gray, fontSize = 12.sp)
    }
}

@Composable
fun MenuItems(navController: NavController, onLogoutClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier.padding(vertical=8.dp)) {
            ProfileMenuItem(icon = R.drawable.ic_edit_profile, text = "Edit Profile") { /*TODO*/ }
            Divider(modifier = Modifier.padding(horizontal=16.dp), color = LightGray)
            ProfileMenuItem(icon = R.drawable.ic_my_orders, text = "My Orders") { /*TODO*/ }
            Divider(modifier = Modifier.padding(horizontal=16.dp), color = LightGray)
            ProfileMenuItem(icon = R.drawable.ic_logout, text = "Logout", onClick = onLogoutClick)
        }
    }
}

@Composable
fun ProfileMenuItem(icon: Int, text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(0.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = icon), contentDescription = text, tint = Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text, color = Color.Black)
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Gray, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun TogetherWeveSavedCard() {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Green)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.ic_meals_saved), contentDescription = null, tint = Color.White, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("Together We've Saved", color = Color.White, fontWeight = FontWeight.Bold)
                Text("12,450 meals", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Join thousands making a difference!", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}
