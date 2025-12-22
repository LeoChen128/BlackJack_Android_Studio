package com.example.project_2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

/**
 * Start screen where users can view their money and set their wager.
 */
@Composable
fun StartScreen(navController: NavController) {
    val context = LocalContext.current
    var userMoney by remember { mutableIntStateOf(DataManager.getUserMoney()) }
    var wager by remember { mutableIntStateOf(10) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "BLACKJACK",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Money display
        Text(
            text = "Your Money",
            fontSize = 18.sp
        )
        Text(
            text = "$$userMoney",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Wager display
        Text(
            text = "Wager Amount",
            fontSize = 18.sp
        )
        Text(
            text = "$$wager",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Wager adjustment buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (wager >= 10) {
                        wager -= 10
                    }
                },
                enabled = wager > 10
            ) {
                Text("-10")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    if (wager + 10 <= userMoney) {
                        wager += 10
                    }
                },
                enabled = wager + 10 <= userMoney
            ) {
                Text("+10")
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Play button
        Button(
            onClick = {
                DataManager.setCurrentWager(wager)
                navController.navigate("game")
            },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(56.dp),
            enabled = wager <= userMoney && wager > 0
        ) {
            Text(
                text = "PLAY",
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Reset button
        TextButton(
            onClick = {
                DataManager.resetMoney(context)
                userMoney = DataManager.getUserMoney()
            }
        ) {
            Text("Reset Money")
        }
    }
}