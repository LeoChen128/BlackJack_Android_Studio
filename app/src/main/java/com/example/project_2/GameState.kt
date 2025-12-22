package com.example.project_2

/**
 * Data class representing the complete state of a Blackjack game.
 */
data class GameState(
    val deck: Deck = Deck(),
    val user: User = User(),
    val dealer: Dealer = Dealer(),
    val isUserTurn: Boolean = true,
    val isGameActive: Boolean = true,
    val gameResult: GameResult = GameResult.ONGOING,
    val showDealerCard: Boolean = false
)

/**
 * Enum representing all possible game outcomes.
 */
enum class GameResult {
    ONGOING,
    USER_BLACKJACK,
    USER_WIN,
    DEALER_WIN,
    TIE,
    USER_BUST,
    DEALER_BUST
}