import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class UIGame {

    public static void main(String[] args) {
        new UIGame();
    }

    public UIGame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("RPG Music");
                frame.add(new MenuPanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setSize(800, 400);
            }
        });
    }

    public static class MenuPanel extends JPanel {

        public MenuPanel() {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            add(new JLabel("<html><h1><strong><i>RPG Game Music</i></strong></h1><hr></html>"), gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JPanel buttons = new JPanel(new GridBagLayout());
            JButton start = new JButton("Start");
            buttons.add(start, gbc);
            JButton help = new JButton("Help");
            buttons.add(help, gbc);
            JButton exit = new JButton("Exit");
            buttons.add(exit, gbc);

            gbc.weighty = 1;
            add(buttons, gbc);

            start.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getRoot(MenuPanel.this);
                    frame.setContentPane(new GamePanel(false, ""));
                    frame.revalidate();
                }
            });

            help.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JFrame frame = (JFrame) SwingUtilities.getRoot(MenuPanel.this);
                    frame.setContentPane(new HelpPanel());
                    frame.revalidate();

                }
            });

            exit.addActionListener(e -> System.exit(0));
        }

    }

}