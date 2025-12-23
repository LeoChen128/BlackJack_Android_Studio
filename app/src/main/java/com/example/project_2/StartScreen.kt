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

@Composable
fun StartScreen(navController: NavController) {
    val context = LocalContext.current
    var usermoney by remember { mutableIntStateOf(DataManager.getusermoney()) }
    var wager by remember { mutableIntStateOf(10) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "BLACKJACK",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Your Money",
            fontSize = 18.sp
        )
        Text(
            text = "$$usermoney",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (wager >= 10) {
                        wager = wager - 10
                    }
                },
                enabled = wager > 10
            ) {
                Text("-10")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    if (wager + 10 <= usermoney) {
                        wager = wager + 10
                    }
                },
                enabled = wager + 10 <= usermoney
            ) {
                Text("+10")
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                DataManager.setcurrentwager(wager)
                navController.navigate("game")
            },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(56.dp),
            enabled = wager <= usermoney && wager > 0
        ) {
            Text(
                text = "PLAY",
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                DataManager.resetmoney(context)
                usermoney = DataManager.getusermoney()
            }
        ) {
            Text("Reset Money")
        }
    }
}