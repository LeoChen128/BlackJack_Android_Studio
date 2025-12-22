package com.example.project_2
class Deck {
    private val deckOfCards: ArrayList<String?>

    init {
        val suit = arrayOf<String?>("Diamonds", "Hearts", "Clubs", "Spades")
        val order = arrayOf<String?>(
            "Ace",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "Jack",
            "Queen",
            "King"
        )
        deckOfCards = ArrayList<String?>()

        for (i in order.indices) {
            for (j in suit.indices) {
                deckOfCards.add(order[i] + " of " + suit[j])
            }
        }

        deckOfCards.shuffle()
    }

    fun draw(): String? {
        if (deckOfCards.isNotEmpty()) {
            return deckOfCards.removeAt(deckOfCards.size - 1)
        }
        return null
    }
}