package com.example.project_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController) {
    val context = LocalContext.current
    var gamestate by remember { mutableStateOf(initializegame()) }
    var showdealercard by remember { mutableStateOf(false) }
    var gamemessage by remember { mutableStateOf("") }
    var updatetrigger by remember { mutableIntStateOf(0) }
    val wager = DataManager.getcurrentwager()
    val cardvalues = remember { CardValues() }

    LaunchedEffect(gamestate.gameresult) {
        if (gamestate.gameresult != GameResult.ONGOING) {
            showdealercard = true
            delay(500)

            if (gamestate.gameresult == GameResult.USER_BLACKJACK) {
                val winnings = (wager * 2.5).toInt()
                gamemessage = "BLACKJACK! You win $$winnings!"
                DataManager.addmoney(winnings, context)
            }
            if (gamestate.gameresult == GameResult.USER_WIN) {
                val winnings = wager * 2
                gamemessage = "You win $$winnings!"
                DataManager.addmoney(winnings, context)
            }
            if (gamestate.gameresult == GameResult.DEALER_WIN) {
                gamemessage = "Dealer wins. You lose $$wager."
            }
            if (gamestate.gameresult == GameResult.TIE) {
                gamemessage = "Push! Your wager is returned."
                DataManager.addmoney(wager, context)
            }
            if (gamestate.gameresult == GameResult.USER_BUST) {
                gamemessage = "Bust! You lose $$wager."
            }
            if (gamestate.gameresult == GameResult.DEALER_BUST) {
                val winnings = wager * 2
                gamemessage = "Dealer busts! You win $$winnings!"
                DataManager.addmoney(winnings, context)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Blackjack") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Money: $${DataManager.getusermoney()}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Wager: $$wager",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Dealer",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            if (showdealercard) {
                Text(
                    text = "Score: ${gamestate.dealer.score}",
                    fontSize = 14.sp
                )
            } else {
                Text(
                    text = "Score: ?",
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy((-40).dp)
            ) {
                items(gamestate.dealer.hand.size) { index ->
                    val card = gamestate.dealer.hand[index]
                    if (card != null) {
                        val issecondcard = index == 1
                        var drawable = 0

                        if (!showdealercard && issecondcard) {
                            drawable = R.drawable.card_back
                        } else {
                            drawable = cardvalues.getcarddrawable(card)
                        }

                        Image(
                            painter = painterResource(id = drawable),
                            contentDescription = card,
                            modifier = Modifier.size(width = 80.dp, height = 120.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Your Hand",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Score: ${gamestate.user.score}",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            key(updatetrigger) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy((-40).dp)
                ) {
                    items(
                        count = gamestate.user.hand.size,
                        key = { index -> "${gamestate.user.hand[index]}_${index}_$updatetrigger" }
                    ) { index ->
                        val card = gamestate.user.hand[index]
                        if (card != null) {
                            val drawable = cardvalues.getcarddrawable(card)
                            Image(
                                painter = painterResource(id = drawable),
                                contentDescription = card,
                                modifier = Modifier.size(width = 80.dp, height = 120.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (gamemessage.isNotEmpty()) {
                Text(
                    text = gamemessage,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (gamestate.gameresult == GameResult.ONGOING) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = {
                            gamestate = handlehit(gamestate)
                            updatetrigger = updatetrigger + 1
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        enabled = gamestate.isuserturn && gamestate.user.score < 21
                    ) {
                        Text("HIT", fontSize = 18.sp)
                    }

                    Button(
                        onClick = {
                            showdealercard = true
                            gamestate = handlestand(gamestate)
                            updatetrigger = updatetrigger + 1
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        enabled = gamestate.isuserturn
                    ) {
                        Text("STAND", fontSize = 18.sp)
                    }
                }
            } else {
                Button(
                    onClick = {
                        gamestate = initializegame()
                        showdealercard = false
                        gamemessage = ""
                        updatetrigger = 0
                        DataManager.subtractmoney(wager, context)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    enabled = DataManager.canaffordwager(wager)
                ) {
                    Text("NEW GAME", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(
                    onClick = { navController.navigateUp() }
                ) {
                    Text("Back to Start")
                }
            }
        }
    }
}

fun initializegame(): GameState {
    val deck = Deck()
    val user = User()
    val dealer = Dealer()

    user.drawinitialcards(deck)
    dealer.drawinitialcards(deck)

    var gameresult = GameResult.ONGOING

    if (user.hasblackjack() && !dealer.hasblackjack()) {
        gameresult = GameResult.USER_BLACKJACK
    }
    if (user.hasblackjack() && dealer.hasblackjack()) {
        gameresult = GameResult.TIE
    }
    if (dealer.hasblackjack() && !user.hasblackjack()) {
        gameresult = GameResult.DEALER_WIN
    }

    val newgame = GameState(
        deck = deck,
        user = user,
        dealer = dealer,
        isuserturn = gameresult == GameResult.ONGOING,
        isgameactive = gameresult == GameResult.ONGOING,
        gameresult = gameresult,
        showdealercard = gameresult != GameResult.ONGOING
    )

    return newgame
}

fun handlehit(state: GameState): GameState {
    state.user.drawcard(state.deck)

    if (state.user.over21()) {
        val newstate = state.copy(
            isuserturn = false,
            isgameactive = false,
            gameresult = GameResult.USER_BUST,
            showdealercard = true
        )
        return newstate
    }

    if (state.user.score == 21) {
        val newstate = handlestand(state)
        return newstate
    }

    return state
}

fun handlestand(state: GameState): GameState {
    state.dealer.rules(state.deck)

    var gameresult = GameResult.TIE

    if (state.dealer.over21()) {
        gameresult = GameResult.DEALER_BUST
    } else if (state.dealer.score > state.user.score) {
        gameresult = GameResult.DEALER_WIN
    } else if (state.dealer.score < state.user.score) {
        gameresult = GameResult.USER_WIN
    } else {
        gameresult = GameResult.TIE
    }

    val newstate = state.copy(
        isuserturn = false,
        isgameactive = false,
        gameresult = gameresult,
        showdealercard = true
    )

    return newstate
}