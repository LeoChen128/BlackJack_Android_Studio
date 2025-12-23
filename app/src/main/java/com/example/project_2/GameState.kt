package com.example.project_2

data class GameState(
    val deck: Deck = Deck(),
    val user: User = User(),
    val dealer: Dealer = Dealer(),
    val isuserturn: Boolean = true,
    val isgameactive: Boolean = true,
    val gameresult: GameResult = GameResult.ONGOING,
    val showdealercard: Boolean = false
)

enum class GameResult {
    ONGOING,
    USER_BLACKJACK,
    USER_WIN,
    DEALER_WIN,
    TIE,
    USER_BUST,
    DEALER_BUST
}