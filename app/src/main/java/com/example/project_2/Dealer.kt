package com.example.project_2

/**
 * Represents the dealer in a Blackjack game.
 * Follows standard casino rules: hit on 16 or below, stand on 17 or above.
 */
class Dealer : CardValues() {
    val hand: ArrayList<String?> = ArrayList<String?>()
    var score: Int = 0
        private set
    var aceCount: Int = 0
        private set

    /**
     * Draw the initial two cards for the dealer.
     */
    fun drawInitialCards(deck: Deck) {
        hand.clear()
        score = 0
        aceCount = 0

        for (i in 0..1) {
            val card = deck.draw()
            if (card != null) {
                hand.add(card)
                addCardToScore(card)
            }
        }
    }

    /**
     * Check if the dealer has busted (score over 21).
     */
    fun over21(): Boolean {
        return score > 21
    }

    /**
     * Draw one additional card from the deck.
     */
    fun drawCard(deck: Deck) {
        val card = deck.draw()
        if (card != null) {
            hand.add(card)
            addCardToScore(card)
        }
    }

    /**
     * Add a card to the score with proper Ace handling.
     * Aces start as 11 and are reduced to 1 if needed to avoid bust.
     */
    private fun addCardToScore(card: String) {
        val value = getValue(card)
        if (card.startsWith("Ace")) {
            aceCount++
            score += 11 // Start with 11 for Ace
        } else {
            score += value
        }

        // Adjust for Aces if bust
        while (score > 21 && aceCount > 0) {
            score -= 10
            aceCount--
        }
    }

    /**
     * Execute dealer rules: hit on 16 or below, stand on 17 or above.
     */
    fun rules(deck: Deck) {
        while (score < 17) {
            drawCard(deck)
        }
    }

    /**
     * Reset the dealer's hand and score.
     */
    fun reset() {
        hand.clear()
        score = 0
        aceCount = 0
    }

    /**
     * Get the value of the dealer's first (visible) card.
     */
    fun getFirstCardValue(): Int {
        return if (hand.isNotEmpty() && hand[0] != null) {
            getValue(hand[0]!!)
        } else {
            0
        }
    }

    /**
     * Check if the dealer has a blackjack (21 with exactly 2 cards).
     */
    fun hasBlackjack(): Boolean {
        return hand.size == 2 && score == 21
    }
}