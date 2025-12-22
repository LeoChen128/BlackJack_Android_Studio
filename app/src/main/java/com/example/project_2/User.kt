package com.example.project_2

class User: CardValues() {
    val hand: ArrayList<String?> = ArrayList<String?>()
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


    fun drawCard(deck: Deck) {
        val card = deck.draw()
        if (card != null) {
            hand.add(card)
            addCardToScore(card)
        }
    }


    fun addCardToScore(card: String) {
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


    fun over21(): Boolean {
        return score > 21
    }


    fun hasBlackjack(): Boolean {
        return hand.size == 2 && score == 21
    }


    fun reset() {
        hand.clear()
        score = 0
        aceCount = 0
    }

    override fun toString(): String {
        return hand.toString() + " User's score: " + score
    }
}