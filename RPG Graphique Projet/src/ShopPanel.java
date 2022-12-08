import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPanel extends Container {
    public ShopPanel(Play gameStart) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        add(new JLabel("<html><h1><strong><i>Shop Panel</i></strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());

        gameStart.Shop(buttons, gbc);

        gbc.weighty = 1;
        add(buttons, gbc);


    }
}
