package local.work.panels;

import javax.swing.*;
import java.awt.*;

public class FileTreeArea extends JPanel{
    private static JLabel label;

    public FileTreeArea() {
        this.setBackground(Color.YELLOW);
        FileTreeArea.label = new JLabel("File Tree Area");

        this.add(label);
        label.setAlignmentY(0f);
    }
}