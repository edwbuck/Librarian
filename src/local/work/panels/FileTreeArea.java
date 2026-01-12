package local.work.panels;

import local.work.Brain;
import javax.swing.*;
import java.awt.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public class FileTreeArea extends JPanel implements BrainClient {
    private static JLabel label;
    private static Brain brain;

    public static JLabel getLabel() {
        return label;
    }

    public static void setLabel(String t) {
        label.setText(t);
    }

    @Override
    public void setBrain(Brain brain) {
        this.brain = brain;
    }

    @Override
    public void update() {}

    @Override
    public void update(String u) {
        setLabel(u);
        DirectoryStream<Path> contents = brain.getContents();
        for (Path entry : contents) {
            JLabel e = new JLabel("    " + entry);
            this.add(e);
        }
    }

    public FileTreeArea() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.YELLOW);
        this.label = new JLabel("File Tree Area");

        this.add(label, BorderLayout.NORTH);
    }
}