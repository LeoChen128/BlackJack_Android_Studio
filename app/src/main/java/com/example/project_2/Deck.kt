package com.example.project_2

import java.util.Collections

/**
 * Represents a standard 52-card deck that is shuffled on initialization.
 */
class Deck {
    private val deckOfCards: ArrayList<String?>

    init {
        val cards: MutableList<String?> = ArrayList<String?>()
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

        // Create all 52 cards
        for (i in order.indices) {
            for (j in suit.indices) {
                deckOfCards.add(order[i] + " of " + suit[j])
            }
        }

        // Shuffle the deck
        Collections.shuffle(deckOfCards)
    }

    /**
     * Draw a card from the deck.
     * @return The drawn card, or null if deck is empty.
     */
    fun draw(): String? {
        if (deckOfCards.isNotEmpty()) {
            return deckOfCards.removeAt(deckOfCards.size - 1)
        }
        return null
    }
}