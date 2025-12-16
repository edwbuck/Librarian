package local.work;

import javax.swing.*;

public class Dummy extends JPanel{
    private static JLabel label;

    public Dummy() {
        Dummy.label = new JLabel("Hello World");

        this.add(label);
        label.setAlignmentY(0.5f);
    }
}
