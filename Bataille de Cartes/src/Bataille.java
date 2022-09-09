public class Bataille {

    /// DEUX JOUEUR, UN PAQUET DE CARTE DE CARTE CLASSIQUE (52 carte), DONNE LES CARTES ALEATOIRE

    public static void main(String[] args) {

        System.out.println("Welcome in the Battle of Cards :) \n");
        System.out.println("Two \uD83E\uDD16 will be in ⚔️, who gonna \uD83D\uDC51 ? \n");
        System.out.print("Player 1 : ");

        Player p1 = new Player();

        System.out.print("Player 2 : ");

        Player p2 = new Player();

        System.out.println();

        /// START LAUNCH DECK

        Deck deck = new Deck(p1, p2);

    }
    /// Arraylist, Collections
}
