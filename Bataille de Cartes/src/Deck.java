import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    //  final String[] CARDS_VALUES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };

    /// IDK HOW TO USE FOR THE MOMENT :)
    private final String[] CARDS_COLORS = {"Clubs", "Diamonds", "Hearts", "Spades"};

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

    private void shuffleCards(ArrayList<Integer> deckCards) {
        Collections.shuffle(deckCards);
    }

    private ArrayList[] deliveryCardsByUser(ArrayList<Integer> deckCards) {
        ArrayList<Integer> first = new ArrayList<>(deckCards.subList(0, (deckCards.size()) / 2));
        ArrayList<Integer> second = new ArrayList<>(deckCards.subList((deckCards.size()) / 2, deckCards.size()));
        return new ArrayList[]{first, second};
    }

    private int fightOn(ArrayList<Integer> deckPlayerOne, ArrayList<Integer> deckPlayerTwo) {

        int nbRound = 0;
        int winner = 0;
        boolean end = false;

        while (!end) {

            if (deckPlayerOne.get(0) > deckPlayerTwo.get(0)) {
                System.out.println("Player 1 win at round " + nbRound);
                deckPlayerOne.add(deckPlayerTwo.get(0));
                deckPlayerTwo.remove(0);
                System.out.println("Number of card of Player 1 : " + deckPlayerOne.size() + " VS for the Player 2 : " + deckPlayerTwo.size());
            } else if (deckPlayerTwo.get(0) > deckPlayerOne.get(0)) {
                System.out.println("Player 2 win at round " + nbRound);
                deckPlayerTwo.add(deckPlayerOne.get(0));
                deckPlayerOne.remove(0);
                System.out.println("Number of card of Player 2 : " + deckPlayerTwo.size() + " VS for the Player 1 : " + deckPlayerOne.size());
            } else {
                if (deckPlayerOne.size() >= 3 && deckPlayerTwo.size() >= 3) {
                        if (deckPlayerOne.get(2) > deckPlayerTwo.get(2)) {
                        System.out.println("Player 1 win at round " + nbRound);
                        deckPlayerOne.add(deckPlayerTwo.get(0));
                        deckPlayerOne.add(deckPlayerTwo.get(1));
                        deckPlayerOne.add(deckPlayerTwo.get(2));
                        deckPlayerTwo.subList(0, 3).clear();
                        System.out.println("Number of card of Player 1 : " + deckPlayerOne.size() + " VS for the Player 2 : " + deckPlayerTwo.size());
                    } else if (deckPlayerTwo.get(2) > deckPlayerOne.get(2)) {
                        System.out.println("Player 2 win at round " + nbRound);
                        deckPlayerTwo.add(deckPlayerTwo.get(0));
                        deckPlayerTwo.add(deckPlayerTwo.get(1));
                        deckPlayerTwo.add(deckPlayerTwo.get(2));
                        deckPlayerOne.subList(0, 3).clear();
                        System.out.println("Number of card of Player 2 : " + deckPlayerTwo.size() + " VS for the Player 1 : " + deckPlayerOne.size());
                    } else {
                        shuffleCards(deckPlayerOne);
                        shuffleCards(deckPlayerTwo);
                    }
                } else {
                    shuffleCards(deckPlayerOne);
                    shuffleCards(deckPlayerTwo);
                }
            }

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
