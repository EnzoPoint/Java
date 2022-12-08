import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends Container {

    boolean rounds = false;
    String pseudoArtist;

    public GamePanel(boolean rounds, String pseudoArtist) {
        if(rounds == false) {
            initialize();
        } else {

            if(pseudoArtist.equals("")){
                initialize();
            }
            nextStep(pseudoArtist);
        }
    }

    private void initialize() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        add(new JLabel("<html><h1><strong><i>RPG Game Music</i></strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel gameDashboard = new JPanel(new GridBagLayout());

        add(new JLabel("<html><h4>Vous devez d'abord définir votre pseudo !</h4>", SwingConstants.CENTER), gbc);

        JTextField pseudoInput = new JTextField();

        pseudoInput.setPreferredSize(new Dimension(200, 30));
        gameDashboard.add(pseudoInput, gbc);

        JButton submit = new JButton("Start Game");
        gameDashboard.add(submit, gbc);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pseudoArtist = pseudoInput.getText();
                rounds = true;

                JFrame frame = (JFrame) SwingUtilities.getRoot(GamePanel.this);
                frame.setContentPane(new GamePanel(rounds, pseudoArtist));
                frame.revalidate();
            }

        });

        gbc.weighty = 1;
        add(gameDashboard, gbc);
    }

    private void nextStep(String pseudo) {

        Play GameStart =  new Play(pseudo, 1000, "Independent", 0, 0, 1);

        setLayout(new BorderLayout());
        JPanel gauche = new JPanel();
        gauche.setLayout(new BoxLayout(gauche, BoxLayout.Y_AXIS));
        add(gauche, BorderLayout.WEST);
        JPanel droite = new JPanel();
        droite.setLayout(new BoxLayout(droite, BoxLayout.Y_AXIS));
        add(droite, BorderLayout.EAST);
        JPanel bas = new JPanel();
        bas.setLayout(new BoxLayout(bas, BoxLayout.X_AXIS));
        add(bas, BorderLayout.SOUTH);
        JPanel haut = new JPanel();
        haut.setLayout(new BoxLayout(haut, BoxLayout.X_AXIS));
        add(haut, BorderLayout.NORTH);

        JTextArea carte = new JTextArea();
        carte.setEditable(false);
        carte.setLineWrap(true);
        carte.setWrapStyleWord(true);
        add(carte, BorderLayout.CENTER);

        haut.add(new JLabel("<html><h1><strong><i>RPG Game Music</i></strong></h1><hr> <h2> Pseudo : " + pseudo + " </h2> </html>"), BorderLayout.NORTH);

        JLabel infoLabel = new JLabel("<html>Argent : 0 <br>Popularité : 0 </html>");
        haut.add(infoLabel, BorderLayout.NORTH);

        JLabel infoMusic = new JLabel("<html> Music Réaliser : 0 <br>Music Sortie : 0 </html>");
        haut.add(infoMusic, BorderLayout.NORTH);

        JLabel descriptionCommand = new JLabel("<html> <hr> <h3> Command for MAP : </h3> <hr> </html>");
        gauche.add(descriptionCommand, BorderLayout.NORTH);

        JButton bouton4 = new JButton("Haut");
        gauche.add(bouton4, BorderLayout.CENTER);

        JButton bouton = new JButton("Bas");
        gauche.add(bouton, BorderLayout.CENTER);

        JButton bouton2 = new JButton("Gauche");
        gauche.add(bouton2, BorderLayout.CENTER);

        JButton bouton3 = new JButton("Droite");
        gauche.add(bouton3, BorderLayout.CENTER);

        bouton.setPreferredSize(new Dimension(200, 50));
        bouton2.setPreferredSize(new Dimension(200, 50));
        bouton3.setPreferredSize(new Dimension(200, 50));
        bouton4.setPreferredSize(new Dimension(200, 50));

        JButton bouton5 = new JButton("Exit this game");
        bas.add(bouton5, BorderLayout.SOUTH);

        JButton bouton6 = new JButton("Access to the Shop");
        bas.add(bouton6, BorderLayout.SOUTH);

        int stats = 0;

        if(stats == 0) {
            /// Ajout du contenu du shop de début
            GameStart.addShopStock();
            GameStart.addMonster();
            stats = 1;
        }


        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int move = 3;

                infoLabel.setText("<html>Argent : " + GameStart.money + " <br>Popularité : " + GameStart.popularity + " </html>");
                infoMusic.setText("<html> Music Réaliser : " + GameStart.sonsDone + " <br>Music Sortie : " + GameStart.sonsSortie + " </html>");

                GameStart.Map(carte);

                GameStart.ByMove(move);

                if(GameStart.startedGame == 1) {
                    disableAllButton(bouton, bouton2, bouton3, bouton4, bouton5);
                    carte.setText("Bien joué !! Tu est arrivé la case A, tu a donc fini le niveau 1 \nMalheuresement pendant le passage au niveau 2, tu est tombé sur une polémique tellement grande que tu a perdu toute ta popularité, il faudra donc dans le niveau 2, regagné ta popularité !!");
                } else if (GameStart.startedGame == 2) {
                    carte.setText("Malheuresement tu est arrivé a la case A mais tu n'a pas les prérequis pour passé le niveau 1, il te faut 100% de popularité ou plus mais tu n'en a que " + GameStart.popularity  + "% continue a naviguer sur la MAP pour gagné en popularité et reviens sur la case quand tu a les prérequis pour gagner !");
                    GameStart.startedGame = 0;
                }

                GameStart.GetMonsterOrNot(carte);

                /// Add Money a chaque tours
                GameStart.addMoney();
                /// Add Popularity a chaque tours;
                GameStart.addPopularity();

                revalidate();
            }
        });

        bouton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int move = 1;

                infoLabel.setText("<html>Argent : " + GameStart.money + " <br>Popularité : " + GameStart.popularity + " </html>");
                infoMusic.setText("<html> Music Réaliser : " + GameStart.sonsDone + " <br>Music Sortie : " + GameStart.sonsSortie + " </html>");

                GameStart.Map(carte);

                GameStart.ByMove(move);

                if(GameStart.startedGame == 1) {
                    disableAllButton(bouton, bouton2, bouton3, bouton4, bouton5);
                    carte.setText("Bien joué !! Tu est arrivé la case A, tu a donc fini le niveau 1 \nMalheuresement pendant le passage au niveau 2, tu est tombé sur une polémique tellement grande que tu a perdu toute ta popularité, il faudra donc dans le niveau 2, regagné ta popularité !!");
                } else if (GameStart.startedGame == 2) {
                    carte.setText("Malheuresement tu est arrivé a la case A mais tu n'a pas les prérequis pour passé le niveau 1, il te faut 100% de popularité ou plus mais tu n'en a que " + GameStart.popularity  + "% continue a naviguer sur la MAP pour gagné en popularité et reviens sur la case quand tu a les prérequis pour gagner !");
                    GameStart.startedGame = 0;
                }

                GameStart.GetMonsterOrNot(carte);

                /// Add Money a chaque tours
                GameStart.addMoney();
                /// Add Popularity a chaque tours;
                GameStart.addPopularity();

                revalidate();
            }
        });

        bouton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int move = 4;

                infoLabel.setText("<html>Argent : " + GameStart.money + " <br>Popularité : " + GameStart.popularity + " </html>");
                infoMusic.setText("<html> Music Réaliser : " + GameStart.sonsDone + " <br>Music Sortie : " + GameStart.sonsSortie + " </html>");

                GameStart.Map(carte);

                GameStart.ByMove(move);

                if(GameStart.startedGame == 1) {
                    disableAllButton(bouton, bouton2, bouton3, bouton4, bouton5);
                    carte.setText("Bien joué !! Tu est arrivé la case A, tu a donc fini le niveau 1 \nMalheuresement pendant le passage au niveau 2, tu est tombé sur une polémique tellement grande que tu a perdu toute ta popularité, il faudra donc dans le niveau 2, regagné ta popularité !!");
                } else if (GameStart.startedGame == 2) {
                    carte.setText("Malheuresement tu est arrivé a la case A mais tu n'a pas les prérequis pour passé le niveau 1, il te faut 100% de popularité ou plus mais tu n'en a que " + GameStart.popularity  + "% continue a naviguer sur la MAP pour gagné en popularité et reviens sur la case quand tu a les prérequis pour gagner !");
                    GameStart.startedGame = 0;
                }

                GameStart.GetMonsterOrNot(carte);

                /// Add Money a chaque tours
                GameStart.addMoney();
                /// Add Popularity a chaque tours;
                GameStart.addPopularity();

                revalidate();
            }
        });

        bouton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int move = 2;

                infoLabel.setText("<html>Argent : " + GameStart.money + " <br>Popularité : " + GameStart.popularity + " </html>");
                infoMusic.setText("<html> Music Réaliser : " + GameStart.sonsDone + " <br>Music Sortie : " + GameStart.sonsSortie + " </html>");

                GameStart.Map(carte);

                GameStart.ByMove(move);

                if(GameStart.startedGame == 1) {
                    disableAllButton(bouton, bouton2, bouton3, bouton4, bouton5);
                    carte.setText("Bien joué !! Tu est arrivé la case A, tu a donc fini le niveau 1 \nMalheuresement pendant le passage au niveau 2, tu est tombé sur une polémique tellement grande que tu a perdu toute ta popularité, il faudra donc dans le niveau 2, regagné ta popularité !!");
                } else if (GameStart.startedGame == 2) {
                    carte.setText("Malheuresement tu est arrivé a la case A mais tu n'a pas les prérequis pour passé le niveau 1, il te faut 100% de popularité ou plus mais tu n'en a que " + GameStart.popularity  + "% continue a naviguer sur la MAP pour gagné en popularité et reviens sur la case quand tu a les prérequis pour gagner !");
                    GameStart.startedGame = 0;
                }

                GameStart.GetMonsterOrNot(carte);

                /// Add Money a chaque tours
                GameStart.addMoney();
                /// Add Popularity a chaque tours;
                GameStart.addPopularity();

                revalidate();
            }
        });

        bouton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(GamePanel.this);
                frame.setContentPane(new UIGame.MenuPanel());
                frame.revalidate();
            }
        });



    }

    public void disableAllButton(JButton bouton, JButton bouton2, JButton bouton3, JButton bouton4, JButton bouton5) {
        bouton.setVisible(false);
        bouton2.setVisible(false);
        bouton3.setVisible(false);
        bouton4.setVisible(false);
        bouton5.setVisible(false);
    }
}
