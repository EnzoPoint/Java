import java.util.Scanner;

public class Play extends Game {

    public Play(String pseudoArtist, Integer money, String label, Integer popularity, Integer sonsDone, Integer sonsSortie) {
        super(pseudoArtist, money, label, popularity, sonsDone, sonsSortie);
    }

    /**
     * Ajoute de l'argent
     */
    @Override
    public void addMoney() {
        //if (label != "Independent") money += 500 * 20 / 100;
        if (sonsSortie > 0) {
            if (sonsSortie != 1) money += sonsSortie * 500;
            money += 750;
        } else {
            money += 500;
        }
    }

    /**
     * Ajoute de la popularité
     */
    @Override
    public void addPopularity() {
        if (sonsSortie > 0) {
            if (sonsSortie != 1) popularity += sonsSortie * 10;
            popularity += 15;
        } else {
            popularity += 10;
        }
    }


    /**
     * Check si le level est fini :)
     */
    @Override
    public void checkIfLevelDone() {
        if (popularity >= 100) {
            System.out.println("Bien joué !! Tu est arrivé la case A, tu a donc fini le niveau 1 \nMalheuresement pendant le passage au niveau 2, tu est tombé sur une polémique tellement grande que tu a perdu toute ta popularité, il faudra donc dans le niveau 2, regagné ta popularité !!");
            startedGame = 1;
        } else {
            startedGame = 2;
            System.out.println("Malheuresement tu est arrivé a la case A mais tu n'a pas les prérequis pour passé le niveau 1, il te faut 100% de popularité ou plus mais tu n'en a que " + popularity + "% continue a naviguer sur la MAP pour gagné en popularité et reviens sur la case quand tu a les prérequis pour gagner !");
        }
    }
}
