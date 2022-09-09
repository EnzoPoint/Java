import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    ///  final String[] CARDS_VALUES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
    /// IDK HOW TO USE FOR THE MOMENT :)

    /// private final String[] CARDS_COLORS = {"Clubs", "Diamonds", "Hearts", "Spades"};

    public Deck(Player p1, Player p2) {

        ArrayList<Integer> deckCards = new ArrayList<>();

        /// We need to generate 13 cards for the 4 types
        for (int c = 0; c < 4; c++) {
            /// I said 15 because I will start the for at index 2 then I can said 11 = Jack...
            for (int i = 2; i < 15; i++) {
                /// We add to deck "NB_CARDS" cards
                deckCards.add(i);
            }
        }

        // DO SHUFFLE
        shuffleCards(deckCards);

        ArrayList[] deck = deliveryCardsByUser(deckCards);

        ArrayList<Integer> deckPlayerOne = deck[0];
        ArrayList<Integer> deckPlayerTwo = deck[1];

        System.out.println("Deck of the player " + p1.showPseudo() + " " + deckPlayerOne);
        System.out.println("Deck of the player " + p2.showPseudo() + " " + deckPlayerTwo);
        System.out.println();
        System.out.println("Let's play !");
        System.out.println();

        if (fightOn(deckPlayerOne, deckPlayerTwo) == 2) {
            System.out.println();
            System.out.println("Player 2 AKA " + p2.showPseudo() + " is the fucking WINNER !");
        } else {
            System.out.println();
            System.out.println("Player 1 AKA " + p1.showPseudo() + " is the fucking WINNER !");
        }
    }

    /**
     * will do a shuffle of deckCards
     * @param deckCards
     */
    private void shuffleCards(ArrayList<Integer> deckCards) {
        Collections.shuffle(deckCards);
    }

    /**
     * Separate deckCards in 2 player (deckCards Size / 2)
     * @param deckCards
     * @return deck of player 1 and 2 in ArrayList
     */
    private ArrayList[] deliveryCardsByUser(ArrayList<Integer> deckCards) {
        ArrayList<Integer> first = new ArrayList<>(deckCards.subList(0, (deckCards.size()) / 2));
        ArrayList<Integer> second = new ArrayList<>(deckCards.subList((deckCards.size()) / 2, deckCards.size()));
        return new ArrayList[]{first, second};
    }

    /**
     * Let's start the game we insert 2 deck (player 1, player 2) then we do the game
     * first he will check if Player One win vs Player Two and do the reverse (Player Two win vs Player One)
     * when player win a round, he wins the card of the round and we erase this card from the player loose
     * if equal game we will play the next 3 cards and we retest only the last to see who gonna win all card (6 cards he's going to win in total)
     * @param deckPlayerOne
     * @param deckPlayerTwo
     * @return the WINNER !
     */
    private int fightOn(ArrayList<Integer> deckPlayerOne, ArrayList<Integer> deckPlayerTwo) {

        int nbRound = 0;
        int winner = 0;
        boolean end = false;

        while (!end) {

            // If player One win Vs player Two
            if (deckPlayerOne.get(0) > deckPlayerTwo.get(0)) {
                System.out.println("Player 1 win at round " + nbRound);
                // Give the card of player two to one
                deckPlayerOne.add(deckPlayerTwo.get(0));
                // Remove it to the deck of player Two
                deckPlayerTwo.remove(0);
                System.out.println("Number of card of Player 1 : " + deckPlayerOne.size() + " VS for the Player 2 : " + deckPlayerTwo.size());
            } else if (deckPlayerTwo.get(0) > deckPlayerOne.get(0)) {
                System.out.println("Player 2 win at round " + nbRound);
                // Give the card of player one to two
                deckPlayerTwo.add(deckPlayerOne.get(0));
                // Remove it to the deck of player One
                deckPlayerOne.remove(0);
                System.out.println("Number of card of Player 2 : " + deckPlayerTwo.size() + " VS for the Player 1 : " + deckPlayerOne.size());
            } else {
                if (deckPlayerOne.size() >= 3 && deckPlayerTwo.size() >= 3) {
                        if (deckPlayerOne.get(2) > deckPlayerTwo.get(2)) {
                        System.out.println("Player 1 win at round " + nbRound);
                        // Give the 3 cards to player one
                        deckPlayerOne.add(deckPlayerTwo.get(0));
                        deckPlayerOne.add(deckPlayerTwo.get(1));
                        deckPlayerOne.add(deckPlayerTwo.get(2));
                        // Will remove the 3 cards that were given to player 1
                        deckPlayerTwo.subList(0, 3).clear();
                        System.out.println("Number of card of Player 1 : " + deckPlayerOne.size() + " VS for the Player 2 : " + deckPlayerTwo.size());
                    } else if (deckPlayerTwo.get(2) > deckPlayerOne.get(2)) {
                        System.out.println("Player 2 win at round " + nbRound);
                        // Give the 3 cards to player two
                        deckPlayerTwo.add(deckPlayerTwo.get(0));
                        deckPlayerTwo.add(deckPlayerTwo.get(1));
                        deckPlayerTwo.add(deckPlayerTwo.get(2));
                        // Will remove the 3 cards that were given to player 2
                        deckPlayerOne.subList(0, 3).clear();
                        System.out.println("Number of card of Player 2 : " + deckPlayerTwo.size() + " VS for the Player 1 : " + deckPlayerOne.size());
                    } else {
                        // it is very unlikely but it is possible that there are 2 ties in a row in this case we shuffle the two games
                        shuffleCards(deckPlayerOne);
                        shuffleCards(deckPlayerTwo);
                    }
                } else {
                    // If one of the two players does not have enough cards to get that 3rd card in their deck then the cards are reshuffled
                    shuffleCards(deckPlayerOne);
                    shuffleCards(deckPlayerTwo);
                }
            }

            // If deck one or deck two as no card we said the game is done !
            if (deckPlayerOne.size() == 0 || deckPlayerTwo.size() == 0) {
                end = true;
            }

            nbRound++;
        }

        if (deckPlayerTwo.size() == 0) winner = 1;
        else winner = 2;

        return winner;
    }
}
