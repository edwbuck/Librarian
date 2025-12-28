package local.work.panels;

import local.work.Brain;
import javax.swing.*;
import java.awt.*;

public class DisplayArea extends JPanel implements BrainClient{
    private static JLabel label;

    public static JLabel getLabel() {
        return label;
    }

    public static void setLabel(String t) {
        label.setText(t);
    }

    public void update() {}

    public void update(String u) {
        setLabel(u);
    }

    public DisplayArea() {
        super();
        this.setBackground(Color.PINK);
        DisplayArea.label = new JLabel("Display Area");

        this.add(label);
        label.setAlignmentY(0.5f);
    }
}
