import java.util.Random;

public class Player {

    private final String[] pseudo = {"Jimmy", "Baron", "Michou", "Maël", "Igor", "Adam", "Freeman", "Camely", "Madman", "Francky", "Franchie", "Zeus", "Harry", "Prince", "Gordon",     "Denver", "Silvère", "Tony", "Boss", "Martin", "Jedi", "Jordy", "Jordan", "James", "Rolex", "Yannou", "Zorro", "Comik", "Bobby", "Jeronimo", "Kaneki", "Loki", "Futé", "Karter", "Novak", "Kennedy", "Billy", "Ragnar", "Tommy", "Ricky" };

    private String player = "";

    public Player() {
        System.out.println(getPseudo());
    }

    /**
     * Get random nickname to be more fun with a list of random pseudo
     * @return The nickname that has been defined
     */
    private String getPseudo() {
        Random random = new Random();
        player = pseudo[random.nextInt(pseudo.length)];
        return player;
    }

    public String showPseudo() {
        return player;
    }
}
