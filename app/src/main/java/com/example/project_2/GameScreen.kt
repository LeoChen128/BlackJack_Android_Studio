package com.example.project_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
    var gameState by remember { mutableStateOf(initializeGame()) }
    var showDealerCard by remember { mutableStateOf(false) }
    var gameMessage by remember { mutableStateOf("") }
    var updateTrigger by remember { mutableIntStateOf(0) }
    val wager = DataManager.getCurrentWager()
    val cardValues = remember { CardValues() }

    LaunchedEffect(gameState.gameResult) {
        if (gameState.gameResult != GameResult.ONGOING) {
            showDealerCard = true
            delay(500)

            when (gameState.gameResult) {
                GameResult.USER_BLACKJACK -> {
                    val winnings = (wager * 2.5).toInt()
                    gameMessage = "BLACKJACK! You win $$winnings!"
                    DataManager.addMoney(winnings, context)
                }
                GameResult.USER_WIN -> {
                    val winnings = wager * 2
                    gameMessage = "You win $$winnings!"
                    DataManager.addMoney(winnings, context)
                }
                GameResult.DEALER_WIN -> {
                    gameMessage = "Dealer wins. You lose $$wager."
                }
                GameResult.TIE -> {
                    gameMessage = "Push! Your wager is returned."
                    DataManager.addMoney(wager, context)
                }
                GameResult.USER_BUST -> {
                    gameMessage = "Bust! You lose $$wager."
                }
                GameResult.DEALER_BUST -> {
                    val winnings = wager * 2
                    gameMessage = "Dealer busts! You win $$winnings!"
                    DataManager.addMoney(winnings, context)
                }
                GameResult.ONGOING -> {}
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
                    text = "Money: $${DataManager.getUserMoney()}",
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

            // Dealer section
            Text(
                text = "Dealer",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = if (showDealerCard) "Score: ${gameState.dealer.score}" else "Score: ?",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy((-40).dp)
            ) {
                items(gameState.dealer.hand) { card ->
                    card?.let {
                        val isSecondCard = gameState.dealer.hand.indexOf(card) == 1
                        val drawable = if (!showDealerCard && isSecondCard) {
                            R.drawable.card_back
                        } else {
                            cardValues.getCardDrawable(it)
                        }

                        Image(
                            painter = painterResource(id = drawable),
                            contentDescription = if (!showDealerCard && isSecondCard) "Hidden card" else it,
                            modifier = Modifier.size(width = 80.dp, height = 120.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // User section
            Text(
                text = "Your Hand",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Score: ${gameState.user.score}",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // User's cards
            key(updateTrigger) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy((-40).dp)
                ) {
                    items(
                        count = gameState.user.hand.size,
                        key = { index -> "${gameState.user.hand[index]}_${index}_$updateTrigger" }
                    ) { index ->
                        gameState.user.hand[index]?.let { card ->
                            Image(
                                painter = painterResource(id = cardValues.getCardDrawable(card)),
                                contentDescription = card,
                                modifier = Modifier.size(width = 80.dp, height = 120.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Game message
            if (gameMessage.isNotEmpty()) {
                Text(
                    text = gameMessage,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Action buttons
            if (gameState.gameResult == GameResult.ONGOING) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = {
                            gameState = handleHit(gameState)
                            updateTrigger++
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        enabled = gameState.isUserTurn && gameState.user.score < 21
                    ) {
                        Text("HIT", fontSize = 18.sp)
                    }

                    Button(
                        onClick = {
                            showDealerCard = true
                            gameState = handleStand(gameState)
                            updateTrigger++
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        enabled = gameState.isUserTurn
                    ) {
                        Text("STAND", fontSize = 18.sp)
                    }
                }
            } else {
                Button(
                    onClick = {
                        gameState = initializeGame()
                        showDealerCard = false
                        gameMessage = ""
                        updateTrigger = 0
                        DataManager.subtractMoney(wager, context)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    enabled = DataManager.canAffordWager(wager)
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

private fun initializeGame(): GameState {
    val deck = Deck()
    val user = User()
    val dealer = Dealer()

    user.drawInitialCards(deck)
    dealer.drawInitialCards(deck)

    val gameResult = when {
        user.hasBlackjack() && !dealer.hasBlackjack() -> GameResult.USER_BLACKJACK
        user.hasBlackjack() && dealer.hasBlackjack() -> GameResult.TIE
        dealer.hasBlackjack() -> GameResult.DEALER_WIN
        else -> GameResult.ONGOING
    }

    return GameState(
        deck = deck,
        user = user,
        dealer = dealer,
        isUserTurn = gameResult == GameResult.ONGOING,
        isGameActive = gameResult == GameResult.ONGOING,
        gameResult = gameResult,
        showDealerCard = gameResult != GameResult.ONGOING
    )
}

private fun handleHit(state: GameState): GameState {
    state.user.drawCard(state.deck)

    return if (state.user.over21()) {
        state.copy(
            isUserTurn = false,
            isGameActive = false,
            gameResult = GameResult.USER_BUST,
            showDealerCard = true
        )
    } else if (state.user.score == 21) {
        handleStand(state)
    } else {
        state
    }
}

private fun handleStand(state: GameState): GameState {
    state.dealer.rules(state.deck)

    val gameResult = when {
        state.dealer.over21() -> GameResult.DEALER_BUST
        state.dealer.score > state.user.score -> GameResult.DEALER_WIN
        state.dealer.score < state.user.score -> GameResult.USER_WIN
        else -> GameResult.TIE
    }

    return state.copy(
        isUserTurn = false,
        isGameActive = false,
        gameResult = gameResult,
        showDealerCard = true
    )
}