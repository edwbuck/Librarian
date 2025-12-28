package local.work.panels;

import local.work.Brain;
import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel implements BrainClient {
    private static JTextField addressBar;
    private static Dimension d;

    public static JTextField getAddressBar() {
        return addressBar;
    }

    public static void setAddressBar(String t) {
        addressBar.setText(t);
    }

   public void update() {}

    public void update(String u) {
        setAddressBar(u);
    }

    public Toolbar() {
        super();
        Toolbar.d = new Dimension(500, 25);
        Toolbar.addressBar = new JTextField();
        addressBar.setPreferredSize(d);

        this.add(addressBar);
        this.setBackground(Color.RED);
    }
}
