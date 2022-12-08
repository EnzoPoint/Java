import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class Game implements GameNeeded {
    protected final String pseudoArtist;
    protected final char[][] map = {{'O', 'O', 'O', 'O', 'O', 'O', 'A'}, {'O', 'O', 'O', 'O', 'O', 'O', 'O'}, {'☟', 'O', 'O', 'O', 'O', 'O', 'O'}};
    protected Integer startedGame = 0;
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
    public void GetMonsterOrNot(JTextArea carte) {
        if (sonsSortie > 0) {
            if (Math.random() < .4) {
                carte.append("\nUne polémique apparait !!\n");
                int randIndex = (int) (Math.random() * monster.size());
                String key = (String) monster.keySet().toArray()[randIndex];
                Scanner reader = new Scanner(System.in);
                carte.append("\nUne polémique a explosé sur une de vos musiques : " + key);
                if (popularity >= monster.get(key)) {
                    popularity += monster.get(key) / 2;
                    carte.append("\nVous avez gagné la polémique, vous avez gagné " + monster.get(key) / 2 + " de popularité");
                } else {
                    popularity -= monster.get(key) / 2;
                    carte.append("\nVous avez perdu la polémique, vous avez perdu " + monster.get(key) / 2 + " de popularité");
                }
            } else {
                if (Math.random() < 0.4) {
                    /// ADD MONEY BONUS
                    money += (int) (Math.random() * (1000 - 500 + 1) + 500);
                    carte.append("\nLa case contenait un Bonus");
                }
            }
        } else {
            if (Math.random() < 0.2) {
                /// ADD MONEY BONUS
                System.out.println(Math.random() * (500 - 250 + 1) + 250);
                money += (int) (Math.random() * (500 - 250 + 1) + 250);
                carte.append("\nLa case contenait un Bonus");
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
    }

    /**
     * Affiche la map
     */
    public void Map(JTextArea carte) {

        carte.setText("Map : \n");
        for (char[] chars : map) {
            for (char aChar : chars) {
                carte.append(String.valueOf(aChar));
            }
            carte.append("\n");
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
    public void Shop(JPanel shop, GridBagConstraints gbc) {


        shop.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        shop.add(new JLabel("<html> <h1> Bienvenue dans le shop </h1> <hr> </html>"), gbc);
        gbc.gridy = 1;
        shop.add(new JLabel("Vous avez " + money + "€, voulez-vous achetez quelque chose ?"), gbc);
        gbc.gridy = 2;

        for (String key : shopItems.keySet()) {
            gbc.gridy++;
            JButton button = new JButton(key + " (" + shopItems.get(key) + "€)");
            button.addActionListener(e -> {
                if (money >= shopItems.get(key)) {
                    money -= shopItems.get(key);
                    if (key.equals("Sortir un sons")) {
                        sonsSortie++;
                    } else if (key.equals("Payer une séance studio 1h + Mix (1 sons)")) {
                        sonsDone++;
                    } else if (key.equals("Payer une séance studio 2h + Mix (2 sons)")) {
                        sonsDone += 2;
                    }
                    shop.removeAll();
                    Shop(shop, gbc);
                    shop.revalidate();
                    shop.repaint();
                }
            });
            shop.add(button, gbc);
        }

        gbc.gridy++;

        JButton back = new JButton("Back");
        shop.add(back, gbc);

        back.addActionListener(e -> {
            shop.removeAll();
            shop.revalidate();
            shop.repaint();
        });
    }

    public abstract void checkIfLevelDone();

    public abstract void addMoney();

    public abstract void addPopularity();
}
