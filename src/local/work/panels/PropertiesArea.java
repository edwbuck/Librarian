package local.work.panels;

import javax.swing.*;
import java.awt.*;

public class PropertiesArea extends JPanel {
    private static JLabel label;

    public static JLabel getLabel() {
        return label;
    }

    public static void setLabel(String t) {
        label.setText(t);
    }

    public PropertiesArea() {
        PropertiesArea.label = new JLabel("Properties Area");

        label.setAlignmentY(0.5f);
        this.add(label);
        this.setBackground(Color.BLUE);
    }
}