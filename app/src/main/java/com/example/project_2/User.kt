package com.example.project_2




class User: CardValues() {
    val hand: ArrayList<String?> = ArrayList<String?>()
    var score: Int = 0
        private set


    fun drawInitialCards(deck: Deck) {
        for (i in 0..1) {
            val card = deck.draw()
            if (card != null) {
                hand.add(card)
                score += getValue(card)
            }
        }
    }

    fun drawCard(deck: Deck) {
        val card = deck.draw()
        if (card != null) {
            hand.add(card)
            score += getValue(card)
        }
    }


    fun over21(): Boolean {
        return score > 21
    }

    override fun toString(): String {
        return hand.toString() + " User's score: " + score
    }
}
