package local.work.panels;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel{
    private static JTextField addressBar;
    private static Dimension d;

    public Toolbar() {
        Toolbar.d = new Dimension(400, 25);
        Toolbar.addressBar = new JTextField();
        addressBar.setPreferredSize(d);

        this.add(addressBar);
        addressBar.setAlignmentY(0.25f);
    }
}
