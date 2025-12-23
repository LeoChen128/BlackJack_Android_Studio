package com.example.project_2

import java.util.Collections

class Deck {
    private val deckofcards: ArrayList<String?>

    init {
        val suits = arrayOf<String?>("Diamonds", "Hearts", "Clubs", "Spades")
        val ranks = arrayOf<String?>("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King")

        deckofcards = ArrayList<String?>()

        var i = 0
        while (i < ranks.size) {
            var j = 0
            while (j < suits.size) {
                deckofcards.add(ranks[i] + " of " + suits[j])
                j = j + 1
            }
            i = i + 1
        }

        Collections.shuffle(deckofcards)
    }

    fun draw(): String? {
        if (deckofcards.isNotEmpty()) {
            val lastcard = deckofcards.removeAt(deckofcards.size - 1)
            return lastcard
        }
        return null
    }
}