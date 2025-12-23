package com.example.project_2

open class CardValues {

    open fun getvalue(card: String): Int {
        val space = card.indexOf(" ")
        val rank = card.substring(0, space)

        if (rank == "Ace") {
            return 1
        }
        if (rank == "Jack") {
            return 10
        }
        if (rank == "Queen") {
            return 10
        }
        if (rank == "King") {
            return 10
        }

        val number = rank.toInt()
        return number
    }

    fun getcarddrawable(card: String): Int {
        val parts = card.split(" of ")
        val rank = parts[0]
        val suit = parts[1].lowercase()

        var rankcode = ""

        if (rank == "Ace") {
            rankcode = "a"
        } else if (rank == "2") {
            rankcode = "02"
        } else if (rank == "3") {
            rankcode = "03"
        } else if (rank == "4") {
            rankcode = "04"
        } else if (rank == "5") {
            rankcode = "05"
        } else if (rank == "6") {
            rankcode = "06"
        } else if (rank == "7") {
            rankcode = "07"
        } else if (rank == "8") {
            rankcode = "08"
        } else if (rank == "9") {
            rankcode = "09"
        } else if (rank == "10") {
            rankcode = "10"
        } else if (rank == "Jack") {
            rankcode = "j"
        } else if (rank == "Queen") {
            rankcode = "q"
        } else if (rank == "King") {
            rankcode = "k"
        }

        if (suit == "hearts") {
            if (rankcode == "a") return R.drawable.card_hearts_a
            if (rankcode == "02") return R.drawable.card_hearts_02
            if (rankcode == "03") return R.drawable.card_hearts_03
            if (rankcode == "04") return R.drawable.card_hearts_04
            if (rankcode == "05") return R.drawable.card_hearts_05
            if (rankcode == "06") return R.drawable.card_hearts_06
            if (rankcode == "07") return R.drawable.card_hearts_07
            if (rankcode == "08") return R.drawable.card_hearts_08
            if (rankcode == "09") return R.drawable.card_hearts_09
            if (rankcode == "10") return R.drawable.card_hearts_10
            if (rankcode == "j") return R.drawable.card_hearts_j
            if (rankcode == "q") return R.drawable.card_hearts_q
            if (rankcode == "k") return R.drawable.card_hearts_k
        }

        if (suit == "diamonds") {
            if (rankcode == "a") return R.drawable.card_diamonds_a
            if (rankcode == "02") return R.drawable.card_diamonds_02
            if (rankcode == "03") return R.drawable.card_diamonds_03
            if (rankcode == "04") return R.drawable.card_diamonds_04
            if (rankcode == "05") return R.drawable.card_diamonds_05
            if (rankcode == "06") return R.drawable.card_diamonds_06
            if (rankcode == "07") return R.drawable.card_diamonds_07
            if (rankcode == "08") return R.drawable.card_diamonds_08
            if (rankcode == "09") return R.drawable.card_diamonds_09
            if (rankcode == "10") return R.drawable.card_diamonds_10
            if (rankcode == "j") return R.drawable.card_diamonds_j
            if (rankcode == "q") return R.drawable.card_diamonds_q
            if (rankcode == "k") return R.drawable.card_diamonds_k
        }

        if (suit == "clubs") {
            if (rankcode == "a") return R.drawable.card_clubs_a
            if (rankcode == "02") return R.drawable.card_clubs_02
            if (rankcode == "03") return R.drawable.card_clubs_03
            if (rankcode == "04") return R.drawable.card_clubs_04
            if (rankcode == "05") return R.drawable.card_clubs_05
            if (rankcode == "06") return R.drawable.card_clubs_06
            if (rankcode == "07") return R.drawable.card_clubs_07
            if (rankcode == "08") return R.drawable.card_clubs_08
            if (rankcode == "09") return R.drawable.card_clubs_09
            if (rankcode == "10") return R.drawable.card_clubs_10
            if (rankcode == "j") return R.drawable.card_clubs_j
            if (rankcode == "q") return R.drawable.card_clubs_q
            if (rankcode == "k") return R.drawable.card_clubs_k
        }

        if (suit == "spades") {
            if (rankcode == "a") return R.drawable.card_spades_a
            if (rankcode == "02") return R.drawable.card_spades_02
            if (rankcode == "03") return R.drawable.card_spades_03
            if (rankcode == "04") return R.drawable.card_spades_04
            if (rankcode == "05") return R.drawable.card_spades_05
            if (rankcode == "06") return R.drawable.card_spades_06
            if (rankcode == "07") return R.drawable.card_spades_07
            if (rankcode == "08") return R.drawable.card_spades_08
            if (rankcode == "09") return R.drawable.card_spades_09
            if (rankcode == "10") return R.drawable.card_spades_10
            if (rankcode == "j") return R.drawable.card_spades_j
            if (rankcode == "q") return R.drawable.card_spades_q
            if (rankcode == "k") return R.drawable.card_spades_k
        }

        return R.drawable.card_back
    }
}