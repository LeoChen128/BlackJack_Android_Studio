package com.example.project_2

class User: CardValues() {
    val hand: ArrayList<String?> = ArrayList<String?>()
    var score: Int = 0
        private set
    var acecount: Int = 0
        private set

    fun drawinitialcards(deck: Deck) {
        hand.clear()
        score = 0
        acecount = 0

        var i = 0
        while (i <= 1) {
            val card = deck.draw()
            if (card != null) {
                hand.add(card)
                addcardtoscore(card)
            }
            i = i + 1
        }
    }

    fun drawcard(deck: Deck) {
        val card = deck.draw()
        if (card != null) {
            hand.add(card)
            addcardtoscore(card)
        }
    }

    private fun addcardtoscore(card: String) {
        val cardvalue = getvalue(card)

        if (card.startsWith("Ace")) {
            acecount = acecount + 1
            score = score + 11
        } else {
            score = score + cardvalue
        }

        while (score > 21 && acecount > 0) {
            score = score - 10
            acecount = acecount - 1
        }
    }

    fun over21(): Boolean {
        if (score > 21) {
            return true
        } else {
            return false
        }
    }

    fun hasblackjack(): Boolean {
        if (hand.size == 2 && score == 21) {
            return true
        } else {
            return false
        }
    }

    fun reset() {
        hand.clear()
        score = 0
        acecount = 0
    }

    override fun toString(): String {
        return hand.toString() + " User's score: " + score
    }
}