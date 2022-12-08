import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class HelpPanel extends Container{

    public HelpPanel() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        add(new JLabel("<html><h1><strong><i>RPG Game Music</i></strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel helpDashboard = new JPanel(new GridBagLayout());


        add(new JLabel("<html><h3>Bienvenue dans le jeu RPG Music !</h3> <br>Vous rêvez de musique depuis votre enfance, mais vous n'avez jamais franchi la ligne \uD83D\uDE2C, alors vous avez décidé de le faire ! <br>Pour commencer, il vous faudra choisir votre nom d'artiste. <br>L'objectif étant d'arriver au POINT A sur la map. <br>Mais le but est surtout de pouvoir sortir des musiques, et pour cela vous avez une boutique <br>Chaque tours vous gagnerez 500 coins (+ si vous sortez des sons) et 5 pourcent de popularité (+ si vous sortez des sons).<br>Attention il peut y avoir des polémiques qui peuvent vous faire perdre de la popularité et de l'argent <br> Bonne chance ! </html>", SwingConstants.CENTER), gbc);

        JButton back = new JButton("Retour");
        helpDashboard.add(back, gbc);

        gbc.weighty = 1;
        add(helpDashboard, gbc);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(HelpPanel.this);
                frame.setContentPane(new UIGame.MenuPanel());
                frame.revalidate();
            }
        });
    }

}
