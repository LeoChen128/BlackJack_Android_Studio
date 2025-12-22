package com.example.project_2

/**
 * Base class for card value calculations and drawable resource mapping.
 */
open class CardValues {

    /**
     * Get the numeric value of a card.
     * Aces return 1 (adjusted to 11 in User/Dealer classes).
     */
    open fun getValue(card: String): Int {
        val space = card.indexOf(" ")
        val special = card.substring(0, space)

        if (special == "Ace") {
            return 1
        } else if (special == "Jack" || special == "Queen" || special == "King") {
            return 10
        } else {
            return special.toInt()
        }
    }

    /**
     * Get the drawable resource ID for a given card.
     * Card format: "Rank of Suit" (e.g., "Ace of Hearts")
     * Drawable format:
     * - Number cards: card_[suit]_[number].png (e.g., card_hearts_02.png)
     * - Face cards: card_[suit]_[letter].png (e.g., card_hearts_j.png, card_hearts_q.png, card_hearts_k.png)
     * - Aces: card_[suit]_a.png (e.g., card_hearts_a.png)
     */
    fun getCardDrawable(card: String): Int {
        val parts = card.split(" of ")
        val rank = parts[0]
        val suit = parts[1].lowercase()

        // Convert rank to drawable format
        val rankCode = when (rank) {
            "Ace" -> "a"
            "2" -> "02"
            "3" -> "03"
            "4" -> "04"
            "5" -> "05"
            "6" -> "06"
            "7" -> "07"
            "8" -> "08"
            "9" -> "09"
            "10" -> "10"
            "Jack" -> "j"
            "Queen" -> "q"
            "King" -> "k"
            else -> "back"
        }

        return when (suit) {
            "hearts" -> when (rankCode) {
                "a" -> R.drawable.card_hearts_a
                "02" -> R.drawable.card_hearts_02
                "03" -> R.drawable.card_hearts_03
                "04" -> R.drawable.card_hearts_04
                "05" -> R.drawable.card_hearts_05
                "06" -> R.drawable.card_hearts_06
                "07" -> R.drawable.card_hearts_07
                "08" -> R.drawable.card_hearts_08
                "09" -> R.drawable.card_hearts_09
                "10" -> R.drawable.card_hearts_10
                "j" -> R.drawable.card_hearts_j
                "q" -> R.drawable.card_hearts_q
                "k" -> R.drawable.card_hearts_k
                else -> R.drawable.card_back
            }
            "diamonds" -> when (rankCode) {
                "a" -> R.drawable.card_diamonds_a
                "02" -> R.drawable.card_diamonds_02
                "03" -> R.drawable.card_diamonds_03
                "04" -> R.drawable.card_diamonds_04
                "05" -> R.drawable.card_diamonds_05
                "06" -> R.drawable.card_diamonds_06
                "07" -> R.drawable.card_diamonds_07
                "08" -> R.drawable.card_diamonds_08
                "09" -> R.drawable.card_diamonds_09
                "10" -> R.drawable.card_diamonds_10
                "j" -> R.drawable.card_diamonds_j
                "q" -> R.drawable.card_diamonds_q
                "k" -> R.drawable.card_diamonds_k
                else -> R.drawable.card_back
            }
            "clubs" -> when (rankCode) {
                "a" -> R.drawable.card_clubs_a
                "02" -> R.drawable.card_clubs_02
                "03" -> R.drawable.card_clubs_03
                "04" -> R.drawable.card_clubs_04
                "05" -> R.drawable.card_clubs_05
                "06" -> R.drawable.card_clubs_06
                "07" -> R.drawable.card_clubs_07
                "08" -> R.drawable.card_clubs_08
                "09" -> R.drawable.card_clubs_09
                "10" -> R.drawable.card_clubs_10
                "j" -> R.drawable.card_clubs_j
                "q" -> R.drawable.card_clubs_q
                "k" -> R.drawable.card_clubs_k
                else -> R.drawable.card_back
            }
            "spades" -> when (rankCode) {
                "a" -> R.drawable.card_spades_a
                "02" -> R.drawable.card_spades_02
                "03" -> R.drawable.card_spades_03
                "04" -> R.drawable.card_spades_04
                "05" -> R.drawable.card_spades_05
                "06" -> R.drawable.card_spades_06
                "07" -> R.drawable.card_spades_07
                "08" -> R.drawable.card_spades_08
                "09" -> R.drawable.card_spades_09
                "10" -> R.drawable.card_spades_10
                "j" -> R.drawable.card_spades_j
                "q" -> R.drawable.card_spades_q
                "k" -> R.drawable.card_spades_k
                else -> R.drawable.card_back
            }
            else -> R.drawable.card_back
        }
    }
}