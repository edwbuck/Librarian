package local.work.panels;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel{
    private static JTextField addressBar;
    private static Dimension d;

    public static JTextField getAddressBar() {
        return addressBar;
    }

    public static void setAddressBar(String t) {
        addressBar.setText(t);
    }

    public Toolbar() {
        Toolbar.d = new Dimension(500, 25);
        Toolbar.addressBar = new JTextField();
        addressBar.setPreferredSize(d);

        this.add(addressBar);
        this.setBackground(Color.RED);
    }
}
