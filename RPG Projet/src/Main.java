import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the Music RPG :) \n");
        System.out.println("Vous rêvez de musique depuis votre enfance, mais vous n'avez jamais franchi la ligne \uD83D\uDE2C, alors vous avez décidé de le faire !\n");
        System.out.println("Pour commencer, sélectionnez votre nom d'artiste. \n");
        Scanner reader = new Scanner(System.in);
        System.out.println("Veuillez entrer votre nom d'artiste : \n");
        String pseudoArtist = reader.next();
        System.out.println("Super ! maintenant votre nom d'artiste est " + pseudoArtist + "\nC'est partie vous venez de commencez une partie, l'objectif étant d'arriver au POINT A sur la map.\nMais le but est surtout de pouvoir sortir des musiques, et pour cela vous avez une boutique\nChaque tours vous gagnerez 500 coins (+ si vous sortez des sons) et 5 pourcent de popularité (+ si vous sortez des sons).\nAttention il peut y avoir des polémiques qui peuvent vous faire perdre de la popularité et de l'argent");

        Play GameStart =  new Play(pseudoArtist, 1000, "Independent", 0, 0, 0);

        int rounds = 0;

        while(GameStart.startedGame) {

            if(rounds == 0) {
                /// Ajout du contenu du shop de début
                GameStart.addShopStock();
                GameStart.addMonster();
            }

            boolean roundsBoolean = true;

            while (roundsBoolean) {
                System.out.println("\n" + pseudoArtist + ", voulez-vous faire quelque chose ?");
                System.out.println("\nVoir vos stats ? (1) :");
                System.out.println("Accéder au Shop ? (2) :");
                System.out.println("Accéder a votre Inventaire ? (3) :");
                System.out.println("Continuez le jeu (4)");
                int choix = Integer.parseInt(reader.next());
                if (choix == 1) {
                    GameStart.Status();
                } else if (choix == 2) {
                    GameStart.Shop();
                } else if (choix == 3) {
                    GameStart.Inventaire();
                } else {
                    roundsBoolean = false;
                }
            }
            GameStart.Map();
            System.out.println("\nVous devez vous déplacer de la MAP (1 = gauche / 2 = haut / 3 = bas / 4 = droite) : \n");
            int move = Integer.parseInt(reader.next());
            GameStart.ByMove(move);

            GameStart.GetMonsterOrNot();

            /// Add Money a chaque tours
            GameStart.addMoney();
            /// Add Popularity a chaque tours;
            GameStart.addPopularity();

            rounds += 1;
        }

        System.out.println("Merci d'avoir joué a mon tout premier jeux et d'avoir gagné le niveau 1 !");
    }
}