package local.work.panels;

import javax.swing.*;

public class PropertiesArea extends JPanel {
    private static JLabel label;

    public PropertiesArea() {
        PropertiesArea.label = new JLabel("Properties Area");

        this.add(label);
        label.setAlignmentY(0.5f);
    }
}