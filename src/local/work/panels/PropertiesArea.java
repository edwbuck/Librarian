package local.work.panels;

import local.work.Brain;
import local.work.datahandlers.PropertiesParser;
import local.work.datahandlers.WorkerOutputHandler;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PropertiesArea extends JPanel implements BrainClient, WorkerOutputHandler {
    private static Brain brain;


    @Override
    public void setBrain(Brain brain) {
        this.brain = brain;
    }

    @Override
    public void handleParserOutput(JComponent c) {
        SwingUtilities.invokeLater(() -> {
            this.add(c);
            this.revalidate();
            this.repaint();
        });
    }

    public void start() {
        PropertiesParser parser = new PropertiesParser(this, brain);
    }

    @Override
    public void update() {}

    @Override
    public void update(String u) {
        this.removeAll();

        start();
    }

    public PropertiesArea() {
        super();

        this.setBackground(Color.LIGHT_GRAY);
    }
}