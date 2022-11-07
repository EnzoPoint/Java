import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class Game implements GameNeeded {
    protected final String pseudoArtist;
    protected final char[][] map = {{'O', 'O', 'O', 'O', 'O', 'O', 'A'}, {'O', 'O', 'O', 'O', 'O', 'O', 'O'}, {'☟', 'O', 'O', 'O', 'O', 'O', 'O'}};
    protected Boolean startedGame = true;
    protected Integer money;
    protected String label;
    protected double popularity;
    protected Map<String, Integer> shopItems;
    protected Map<Integer, Object> positionItems;
    protected Map<String, Integer> monster;
    protected Integer sonsDone;
    protected Integer sonsSortie;
    private int x = 0;
    private int y = map.length - 1;

    public Game(String pseudoArtist, Integer money, String label, Integer popularity, Integer sonsDone, Integer sonsSortie) {
        this.pseudoArtist = pseudoArtist;
        this.money = money;
        this.label = label;
        this.popularity = popularity;
        this.sonsDone = sonsDone;
        this.sonsSortie = sonsSortie;
        this.shopItems = new HashMap<>();
        this.positionItems = new HashMap<>();
        this.monster = new HashMap<>();
    }

    /**
     * Fight avec le monstre en question vs nos stats
     */
    public void GetMonsterOrNot() {
        if (sonsSortie > 0) {
            if (Math.random() < .4) {
                System.out.println("\nUne polémique apparait !!");
                int randIndex = (int) (Math.random() * monster.size());
                String key = (String) monster.keySet().toArray()[randIndex];
                Scanner reader = new Scanner(System.in);
                System.out.println("\nUne polémique a explosé sur une de vos musiques : " + key + " \nVoulez-vous vous répondre ? (Oui = 1 / Non = 2)");
                int nbChoix = Integer.parseInt(reader.next());
                if (nbChoix == 1) {
                    if (popularity >= monster.get(key)) {
                        popularity += monster.get(key) / 2;
                        System.out.println("\nVous avez gagnez la polémique et gagné " + monster.get(key) / 2 + " popularité");
                    } else {
                        popularity -= monster.get(key) / 2;
                        System.out.println("\nVous avez perdu " +  monster.get(key) / 2 + ", il vous reste que " + popularity + "%");
                    }
                } else {
                    popularity -= monster.get(key) / 2;
                    System.out.println("\nVous avez choisi de fuir et vous perdez " + monster.get(key) / 2 + " de popularité");
                }
            } else {
                if (Math.random() < 0.4) {
                    /// ADD MONEY BONUS
                    money += (int) (Math.random() * (1000 - 500 + 1) + 500);
                    System.out.println("\nLa case contenait un Bonus d'Argent !");
                }
            }
        } else {
            if (Math.random() < 0.2) {
                /// ADD MONEY BONUS
                System.out.println(Math.random() * (500 - 250 + 1) + 250);
                money += (int) (Math.random() * (500 - 250 + 1) + 250);
                System.out.println("\nLa case contenait un Bonus d'Argent !");
            }
        }
    }

    /**
     * Déplacement dans la MAP
     */
    public void ByMove(int Move) {
        if (Move < 0 || Move > 4) return;

        if (map[y][x] != 'A') map[y][x] = 'X';

        if (Move == 1 && x > 0) {
            // Bouge vers la gauche
            x--;
        } else if (Move == 2 && y > 0) {
            // Bouge vers le haut
            y--;
        } else if (Move == 3 && y < map.length - 1) {
            // Bouge vers le bas
            y++;
        } else if (Move == 4 && y < map[y].length - 1) {
            // Bouge vers la droite
            x++;
        }

        if (map[y][x] == 'A') {
            checkIfLevelDone();
        } else {
            map[y][x] = '☟';
        }

        Map();
    }

    /**
     * Affiche la map
     */
    public void Map() {
        System.out.println("\nMap :");
        for (char[] chars : map) {
            System.out.println(chars);
        }
    }

    /**
     * Ajoute les "monstres" a monser qui est une HashMap
     */
    public void addMonster() {
        monster.put("Booba dit que vous faites du ", 60);
        monster.put("Rohff à sortie un freestyle ou il vous plagiat dans sont sons", 30);
        monster.put("Il semblerait que Kaaris souhaite faire un FIGHT vs " + pseudoArtist + " (vous)" , 20);
    }

    /**
     * Ajoute les items au SHOP qui est une HashMap
     */
    public void addShopStock() {
        shopItems.put("Payer une séance studio 2h + Mix (2 sons)", 2500);
        shopItems.put("Payer une séance studio 1h + Mix (1 sons)", 1500);
        shopItems.put("Sortir un sons", 1000);
    }

    /**
     * Affiche le shop
     */
    public void Shop() {

        System.out.println("\n");

        int rounds = 1;

        for (Object key : shopItems.keySet()) {
            System.out.println(shopItems.get(key) + " coins = " + key);
            positionItems.put(rounds, key);
            rounds += 1;
        }
        /// Merci pour ce moyen, source : https://touilleur-express.fr/2003/12/13/et-si-on-regardait-ce-quil-y-a-dans-cette-hashmap/#:~:text=Pour%20lister%20le%20contenu%20d,Iterator%20pour%20lister%20le%20contenu.

        achatShop();
    }

    public abstract void checkIfLevelDone();

    public abstract void Inventaire();

    public abstract void convertAchatToInventaire(Integer items);

    public abstract void achatShop();

    public abstract void Status();

    public abstract void addMoney();

    public abstract void addPopularity();
}
