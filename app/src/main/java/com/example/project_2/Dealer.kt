package com.example.project_2

class Dealer : CardValues() {
    val hand: ArrayList<String?> = ArrayList<String?>()
    var score: Int = 0

    fun drawInitialCards(deck: Deck) {
        for (i in 0..1) {
            val card = deck.draw()
            if (card != null) {
                hand.add(card)
                score += getValue(card)
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
            score += getValue(card)
        }
    }


    fun rules(deck: Deck) {
        while (score < 17) {
            drawCard(deck)
        }
    }
}


