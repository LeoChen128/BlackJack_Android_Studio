package com.example.project_2

open class CardValues {

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

}