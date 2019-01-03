package UI;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

import Objects.Dealer;
import Objects.Deck;
import Objects.Player;

public class BlackjackApp {

    private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance(Locale.US);
    private static final Scanner SCANNER = new Scanner(System.in);

    private static double bet;
    private static double bet2;
    private static String anotherHand;
    private static String playAgain;
    private static boolean anotherHandBool = true;
    private static boolean playAgainBool = false;
    private static Player player;
    private static Dealer dealer;
    private static Deck deck;

    public static void main(String[] args) {
        New_Game:
        while (true) {
            do {

                /* ***************************
                 *    Initialize Game!!!!    *
                 *************************** */

                System.out.println(" ______     __" +
                                "\n/      \\  _/  |" +
                                "\n/$$$$$$  |/ $$ |" +
                                "\n$$____$$ |$$$$ |     Welcome to the Blackjack app!" +
                                "\n/    $$/   $$ |" +
                                "\n/$$$$$$/    $$ |     Author: beatzz" +
                                "\n$$ |_____  _$$ |_" +
                                "\n$$       |/ $$   |" +
                                "\n$$$$$$$$/ $$$$$$/\n");

                // create Dealer, Player, and the deck
                double cash = getDouble("How much ca$h would you like to $tart with? ");
                dealer = new Dealer();
                player = new Player(cash);
                deck = new Deck();
                System.out.println("\nThe Dealer shuffles the deck...\n");

                /* ***************************
                 *       New Hand!!!!        *
                 *************************** */


                New_Hand:
                while (true) {
                    while (player.getCash() >= 10 && anotherHandBool) {

                        // place bet
                        System.out.println("You currently have " + CURRENCY_FORMATTER.format(player.getCash()) + " available.");
                        bet = getDouble("Place your bet: ");
                        player.setCash(player.getCash() - bet);

                        // deal
                        dealHands();

                        System.out.println("\nThe Dealer's hand: | " + dealer.getCard(0).toString() + " | ??? of ??? | (Hand Value: " + dealer.getCard(0).getValue() + ")");
                        System.out.println("        Your hand: " + player.showHand1() + "\n");

                        /* ***************************
                         *       Naturals!!!!        *
                         *************************** */

                        if (dealer.getHandValue() == 21)
                            System.out.println("The Dealer reveals a " + dealer.getCard(1).toString() + " (Hand Value: " + dealer.getHandValue() + ")");

                        if (dealer.getHandValue() == 21 && player.getHandValue(player.getHand1()) != 21) {

                            // Dealer is dealt a natural Blackjack, Dealer wins
                            System.out.println("Blackjack! The Dealer wins!\n");
                            if (player.getCash() >= 10) {
                                anotherHand = getString("Play another hand (yes/no) ? ");
                                if (anotherHand.equalsIgnoreCase("yes")) {
                                    clearHands();
                                    continue New_Hand;
                                } else if (anotherHand.equalsIgnoreCase("no")) {
                                    anotherHandBool = false;
                                    // report game stats here...
                                }
                            } else {
                                System.out.println("Better luck next time!");
                                // report game stats here...
                            }
                            playAgain = getString("Run the app again (yes/no)? ");
                            if (playAgain.equalsIgnoreCase("yes")) {
                                anotherHandBool = true;
                                playAgainBool = true;
                                continue New_Game;
                            } else if (playAgain.equalsIgnoreCase("no")) {
                                playAgainBool = false;
                                System.out.println("\nGoodbye!");
                                break New_Game;
                            }
                        } else if (player.getHandValue(player.getHand1()) == 21 && dealer.getHandValue() == 21) {

                            // Dealer and Player dealt natural Blackjacks, Dealer and Player tie
                            System.out.println("Dealer ties with a Blackjack, your bet is returned to you.\n");
                            player.setCash(player.getCash() + bet);
                            if (player.getCash() >= 10) {
                                anotherHand = getString("Play another hand (yes/no) ? ");
                                if (anotherHand.equalsIgnoreCase("yes")) {
                                    clearHands();
                                    continue New_Hand;
                                } else if (anotherHand.equalsIgnoreCase("no")) {
                                    anotherHandBool = false;
                                    // report game stats here...
                                }
                            } else {
                                System.out.println("Better luck next time!");
                                // report game stats here...
                            }
                            playAgain = getString("Run the app again (yes/no)? ");
                            if (playAgain.equalsIgnoreCase("yes")) {
                                anotherHandBool = true;
                                playAgainBool = true;
                                continue New_Game;
                            } else if (playAgain.equalsIgnoreCase("no")) {
                                playAgainBool = false;
                                System.out.println("\nGoodbye!");
                                break New_Game;
                            }
                        } else if (player.getHandValue(player.getHand1()) == 21 && dealer.getHandValue() != 21) {

                            // Player dealt a natural Blackjack, Player wins
                            System.out.println("Blackjack! Player wins! The dealer gives you " + CURRENCY_FORMATTER.format(bet * 1.5) + "\n");
                            player.setCash(player.getCash() + (bet * 1.5));
                            if (player.getCash() >= 10) {
                                anotherHand = getString("Play another hand (yes/no) ? ");
                                if (anotherHand.equalsIgnoreCase("yes")) {
                                    clearHands();
                                    continue New_Hand;
                                } else if (anotherHand.equalsIgnoreCase("no")) {
                                    anotherHandBool = false;
                                    // report game stats here...
                                }
                            } else {
                                System.out.println("Better luck next time!");
                                // report game stats here...
                            }
                            playAgain = getString("Run the app again (yes/no)? ");
                            if (playAgain.equalsIgnoreCase("yes")) {
                                anotherHandBool = true;
                                playAgainBool = true;
                                continue New_Game;
                            } else if (playAgain.equalsIgnoreCase("no")) {
                                playAgainBool = false;
                                System.out.println("\nGoodbye!");
                                break New_Game;
                            }
                        } else {

                            /* ****************************
                             *       Player's Turn!!!!    *
                             **************************** */
                            int hands = 1;
                            int cardIndex = 2;
                            if (player.getCard1(0).getRank() == player.getCard1(1).getRank()) {
                                String splitDecision = getString("You have a pair of " + player.getCard1(0).getRank() + "s, would you like to split your hand? ");
                                if (splitDecision.equalsIgnoreCase("yes")) {

                                    bet2 = getDouble("Place your bet for the second hand! ");
                                    System.out.println("\nThe Dealer splits your hand and deals a second card for each of them.\n");
                                    player.split();
                                    player.hit(deck.draw(),player.getHand1());
                                    player.hit(deck.draw(),player.getHand2());
                                    System.out.println("Your first hand: " + player.showHand1());
                                    System.out.println("Your second hand: " + player.showHand2() + "\n");
                                    hands = 2;

                                }
                            }

                            for (int i = 0; i < hands; i++) {
                                if (i == 0) {
                                    Players_Turn:
                                    while (player.getHandValue(player.getHand1()) <= 21) {
                                        String play = getString("Your turn, do you stand or hit? ");
                                        switch (play.toLowerCase()) {
                                            case ("stand"):
                                                break Players_Turn;
                                            case ("hit"):
                                                player.hit(deck.draw(), player.getHand1());
                                                System.out.println("\nYou are dealt a " + player.getCard1(cardIndex));
                                                System.out.println("\nThe Dealer's hand: | " + dealer.getCard(0).toString() + "| ??? of ??? | (Hand Value: " + dealer.getCard(0).getValue() + ")");
                                                System.out.println("Your hand: " + player.showHand1() + "\n");
                                                cardIndex++;
                                        }
                                        if (player.getHandValue(player.getHand1()) == 21) {
                                            System.out.println("Blackjack! Player wins! The dealer gives you " + CURRENCY_FORMATTER.format(bet * 1.5));
                                            player.setCash(player.getCash() + (bet * 1.5));
                                            if (player.getCash() >= 10) {
                                                anotherHand = getString("Play another hand (yes/no) ? ");
                                                if (anotherHand.equalsIgnoreCase("yes")) {
                                                    clearHands();
                                                    continue New_Hand;
                                                } else if (anotherHand.equalsIgnoreCase("no")) {
                                                    anotherHandBool = false;
                                                    // report game stats here...
                                                }
                                            } else {
                                                System.out.println("Better luck next time!");
                                                // report game stats here...
                                            }

                                            playAgain = getString("Run the app again (yes/no)? ");
                                            if (playAgain.equalsIgnoreCase("yes")) {
                                                anotherHandBool = true;
                                                playAgainBool = true;
                                                continue New_Game;
                                            } else if (playAgain.equalsIgnoreCase("no")) {
                                                playAgainBool = false;
                                                System.out.println("\nGoodbye!");
                                                break New_Game;
                                            }
                                            break New_Hand;

                                        } else if (player.getHandValue(player.getHand1()) > 21) {
                                            System.out.println("Bust! You lose this hand.");
                                            if (player.getCash() >= 10) {
                                                anotherHand = getString("Play another hand (yes/no) ? ");
                                                if (anotherHand.equalsIgnoreCase("yes")) {
                                                    clearHands();
                                                    continue New_Hand;
                                                } else if (anotherHand.equalsIgnoreCase("no")) {
                                                    anotherHandBool = false;
                                                    // report game stats here...
                                                }
                                            } else {
                                                System.out.println("Better luck next time!");
                                                // report game stats here...
                                            }

                                            playAgain = getString("Run the app again (yes/no)? ");
                                            if (playAgain.equalsIgnoreCase("yes")) {
                                                anotherHandBool = true;
                                                playAgainBool = true;
                                                continue New_Game;
                                            } else if (playAgain.equalsIgnoreCase("no")) {
                                                playAgainBool = false;
                                                System.out.println("\nGoodbye!");
                                                break New_Game;
                                            }
                                            break New_Hand;
                                        }
                                    }


                                } else if (i == 1) {
                                    cardIndex = 2;
                                    Players_Turn:
                                    while (player.getHandValue(player.getHand2()) <= 21) {
                                        String play = getString("Your turn, do you stand or hit? ");
                                        switch (play.toLowerCase()) {
                                            case ("stand"):
                                                break Players_Turn;
                                            case ("hit"):
                                                player.hit(deck.draw(), player.getHand2());
                                                System.out.println("\nYou are dealt a " + player.getCard2(cardIndex));
                                                System.out.println("\nThe Dealer's hand: | " + dealer.getCard(0).toString() + "| ??? of ??? | (Hand Value: " + dealer.getCard(0).getValue() + ")");
                                                System.out.println("Your hand: " + player.showHand2() + "\n");
                                                cardIndex++;
                                        }
                                        if (player.getHandValue(player.getHand2()) == 21) {
                                            System.out.println("Blackjack! Player wins! The dealer gives you " + CURRENCY_FORMATTER.format(bet2 * 1.5));
                                            player.setCash(player.getCash() + (bet2 * 1.5));
                                            if (player.getCash() >= 10) {
                                                anotherHand = getString("Play another hand (yes/no) ? ");
                                                if (anotherHand.equalsIgnoreCase("yes")) {
                                                    clearHands2();
                                                    continue New_Hand;
                                                } else if (anotherHand.equalsIgnoreCase("no")) {
                                                    anotherHandBool = false;
                                                    // report game stats here...
                                                }
                                            } else {
                                                System.out.println("Better luck next time!");
                                                // report game stats here...
                                            }

                                            playAgain = getString("Run the app again (yes/no)? ");
                                            if (playAgain.equalsIgnoreCase("yes")) {
                                                anotherHandBool = true;
                                                playAgainBool = true;
                                                continue New_Game;
                                            } else if (playAgain.equalsIgnoreCase("no")) {
                                                playAgainBool = false;
                                                System.out.println("\nGoodbye!");
                                                break New_Game;
                                            }
                                            break New_Hand;

                                        } else if (player.getHandValue(player.getHand2()) > 21) {
                                            System.out.println("Bust! You lose this hand.");
                                            if (player.getCash() >= 10) {
                                                anotherHand = getString("Play another hand (yes/no) ? ");
                                                if (anotherHand.equalsIgnoreCase("yes")) {
                                                    clearHands2();
                                                    continue New_Hand;
                                                } else if (anotherHand.equalsIgnoreCase("no")) {
                                                    anotherHandBool = false;
                                                    // report game stats here...
                                                }
                                            } else {
                                                System.out.println("Better luck next time!");
                                                // report game stats here...
                                            }

                                            playAgain = getString("Run the app again (yes/no)? ");
                                            if (playAgain.equalsIgnoreCase("yes")) {
                                                anotherHandBool = true;
                                                playAgainBool = true;
                                                continue New_Game;
                                            } else if (playAgain.equalsIgnoreCase("no")) {
                                                playAgainBool = false;
                                                System.out.println("\nGoodbye!");
                                                break New_Game;
                                            }
                                            break New_Hand;
                                        }
                                    }
                                }
                            }

                            /* ****************************
                             *       Dealer's Turn!!!!    *
                             **************************** */

                            System.out.println("The Dealer reveals a | " + dealer.getCard(1).toString() + " | (Hand Value: " + dealer.getHandValue() + ")");
                            if (dealer.getHandValue() >= 17) {
                                System.out.println("The Dealer stands at " + dealer.getHandValue());
                                if (dealer.getHandValue() >= player.getHandValue(player.getHand1())) {
                                    System.out.println("The Dealer wins!\n");
                                } else {
                                    System.out.println("Player wins! The Dealer gives you " + CURRENCY_FORMATTER.format(bet * 1.5) + "\n");
                                    player.setCash(player.getCash() + (bet * 1.5));
                                }
                                if (hands == 2 &&  dealer.getHandValue() >= player.getHandValue(player.getHand2())) {
                                    System.out.println("The Dealer wins against your second hand!\n");
                                } else if (hands == 2 &&  player.getHandValue(player.getHand2()) >= dealer.getHandValue()) {
                                    System.out.println("Player wins! The Dealer gives you " + CURRENCY_FORMATTER.format(bet2 * 1.5) + "\n");
                                    player.setCash(player.getCash() + (bet2 * 1.5));
                                }
                            } else if (dealer.getHandValue() < 17) {
                                int j = 2;
                                while (dealer.getHandValue() < 17) {
                                    dealer.hit(deck.draw());
                                    System.out.println("The Dealer draws a | " + dealer.getCard(j).toString() + " | (Hand Value: " + dealer.getHandValue() + ")");
                                    j++;
                                }
                                if (dealer.getHandValue() > 21) {
                                    System.out.println("The Dealer busts!");
                                    System.out.println("Player wins! The Dealer gives you " + CURRENCY_FORMATTER.format((bet * 1.5) + (bet2 * 1.5)) + "\n");
                                    player.setCash(player.getCash() + (bet * 1.5) + (bet2 * 1.5));
                                } else if (dealer.getHandValue() == 21) {
                                    System.out.println("Blackjack! The Dealer wins!\n");
                                } else if (player.getHandValue(player.getHand1()) > dealer.getHandValue()) {
                                    System.out.println("Player wins! The Dealer gives you " + CURRENCY_FORMATTER.format(bet * 1.5));
                                    player.setCash(player.getCash() + (bet * 1.5));
                                }  else if (player.getHandValue(player.getHand2()) > dealer.getHandValue()) {
                                    System.out.println("Player wins! The Dealer gives you " + CURRENCY_FORMATTER.format(bet2 * 1.5));
                                    player.setCash(player.getCash() + (bet2 * 1.5));
                                } else if (dealer.getHandValue() >= player.getHandValue(player.getHand1())) {
                                    System.out.println("The Dealer wins!\n");
                                }
                            }

                            /* ****************************
                             *       Hand Finished!!!!    *
                             **************************** */

                            if (player.getCash() >= 10) {
                                anotherHand = getString("Play another hand (yes/no) ? ");
                                if (anotherHand.equalsIgnoreCase("yes")) {
                                    clearHands();
                                    continue New_Hand;
                                } else if (anotherHand.equalsIgnoreCase("no")) {
                                    anotherHandBool = false;
                                    // report game stats here...
                                }
                            } else {
                                System.out.println("Better luck next time!");
                                // report game stats here...
                            }

                            playAgain = getString("Run the app again (yes/no)? ");
                            if (playAgain.equalsIgnoreCase("yes")) {
                                anotherHandBool = true;
                                playAgainBool = true;
                                continue New_Game;
                            } else if (playAgain.equalsIgnoreCase("no")) {
                                playAgainBool = false;
                                System.out.println("\nGoodbye!");
                                break New_Game;
                            }
                        }
                    }
                }

            } while (playAgainBool);
        }
    }


    // helper methods

    private static double getDouble(String prompt) {
        double d = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (SCANNER.hasNextDouble()) {
                d = SCANNER.nextDouble();
                isValid = true;
            } else
                System.out.println("Error, invalid entry.");
            SCANNER.nextLine();
        }
        return d;
    }

    private static String getString(String prompt) {
        String s = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (SCANNER.hasNext()) {
                s = SCANNER.next().trim();
                isValid = true;
            } else
                System.out.println("Error, invalid entry.");
        }
        return s;
    }

    private static void dealHands() {
        for (int i = 0; i < 2; i++) {
            player.hit(deck.draw(), player.getHand1());
            dealer.hit(deck.draw());
        }
    }

    private static void clearHands() {
        System.out.println();
        player.clearHand1();
        dealer.clearHand();
    }

    private static void clearHands2() {
        System.out.println();
        player.clearHand2();
        dealer.clearHand();
    }
}
