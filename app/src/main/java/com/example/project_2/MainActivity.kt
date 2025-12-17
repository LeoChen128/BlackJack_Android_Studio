package com.example.project_2

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import java.util.Locale
import java.util.Scanner


object Game {
    @JvmStatic
    fun main(args: Array<String>) {
        println(
            "WELCOME TO BLACKJACK\n" +
                    "------------------------------------------------------\n" +
                    "In this game of blackjack, your overall goal is to obtain the card value(score) of 21. \n" +
                    "If the dealer(the one who gives out the cards) obtains the value of 21, " +
                    "you lose unless you also have a value of 21 (will be a tie).\n" +
                    "However, if either you or the dealer goes above 21 the games ends in a tie. \n" +
                    "If the dealer's value is lower than your value then you win. \n" +
                    "Now Good luck!\n" +
                    "------------------------------------------\n"
        )

        val s = Scanner(System.`in`)
        val deck = Deck()
        val user = User()
        val dealer = Dealer()
        var game = true
        var userTurn = true
        val points by remember { mutableIntStateOf(0) }

        user.drawInitialCards(deck)
        dealer.drawInitialCards(deck)

        println("User cards: $user")

        println("Dealer cards: Blank, " + dealer.hand.get(1))

        while (game && userTurn && user.score <= 21) {
            if (user.score == 21) {
                println("You got Blackjack!")
                userTurn = false
            } else {
                println("\nDo you want to hit or stand? (h or s)")
                val input = s.nextLine().lowercase(Locale.getDefault())
                if (input == "h") {
                    user.drawCard(deck)
                    println("User cards: " + user.hand + ". Your score: " + user.score)

                    if (user.over21()) {
                        println("\nThe total value of your cards have reached over 21!\nYou Busted!")
                        println("Dealer's final cards: " + dealer.hand + ". Dealer's Score:  " + dealer.score)
                        println("Game is now over, You lost!")
                        game = false
                    }
                } else if (input == "s") {
                    println("\nYou decided to stand with: " + user.hand)
                    userTurn = false
                } else {
                    println("That move does not exist. Please use h for hit and s for stand.")
                }
            }
        }


        if (game && !userTurn && user.score <= 21) {
            println("\nDealer's Turn:")
            println("Dealer's cards: " + dealer.hand + " Dealer's score: " + dealer.score)

            dealer.rules(deck)
            println("Dealer's final cards: " + dealer.hand + " Dealer's score: " + dealer.score)

            if (dealer.over21()) {
                println("\nThe dealer card value went over 21!")
                println("You win!")
            } else {
                println("\nFinal scores: ")
                println("Dealer's cards: " + dealer.hand + " Dealer's score: " + dealer.score)
                println("User's cards: " + user.hand + " User's score: " + user.score)

                if (user.score > dealer.score) {
                    println("You won!")
                } else if (user.score < dealer.score) {
                    println("Dealer won!")
                } else {
                    println("It's a tie!")
                }
            }
        }
        s.close()
    }
}
/**
 * class MainActivity : ComponentActivity() {
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         enableEdgeToEdge()
 *         setContent {
 *             Project_2Theme {
 *                 Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
 *                     Greeting(
 *                         name = "Android",
 *                         modifier = Modifier.padding(innerPadding)
 *                     )
 *                 }
 *             }
 *         }
 *     }
 * }
 *
 * @Composable
 * fun Greeting(name: String, modifier: Modifier = Modifier) {
 *     Text(
 *         text = "Hello $name!",
 *         modifier = modifier
 *     )
 * }
 *
 * @Preview(showBackground = true)
 * @Composable
 * fun GreetingPreview() {
 *     Project_2Theme {
 *         Greeting("Android")
 *     }
 * }
 */
