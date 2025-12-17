package local.work.panels;

import javax.swing.*;

public class DisplayArea extends JPanel{
    private static JLabel label;

    public DisplayArea() {
        DisplayArea.label = new JLabel("Hello World");

        this.add(label);
        label.setAlignmentY(0.5f);
    }
}
