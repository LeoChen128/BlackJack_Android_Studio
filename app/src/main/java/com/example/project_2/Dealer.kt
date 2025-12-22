package com.example.project_2

class Dealer : CardValues() {
    val hand: ArrayList<String?> = ArrayList()
    var score: Int = 0
        private set
    var aceCount: Int = 0
        private set

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

    fun over21(): Boolean {
        return score > 21
    }

    fun drawCard(deck: Deck) {
        val card = deck.draw()
        if (card != null) {
            hand.add(card)
            addCardToScore(card)
        }
    }

    private fun addCardToScore(card: String) {
        val value = getValue(card)
        if (card.startsWith("Ace")) {
            aceCount++
            score += 11
        } else {
            score += value
        }

        while (score > 21 && aceCount > 0) {
            score -= 10
            aceCount--
        }
    }


    fun rules(deck: Deck) {
        while (score < 17) {
            drawCard(deck)
        }
    }

    fun reset() {
        hand.clear()
        score = 0
        aceCount = 0
    }

    fun getFirstCardValue(): Int {
        return if (hand.isNotEmpty() && hand[0] != null) {
            getValue(hand[0]!!)
        } else {
            0
        }
    }

    fun hasBlackjack(): Boolean {
        return hand.size == 2 && score == 21
    }
}