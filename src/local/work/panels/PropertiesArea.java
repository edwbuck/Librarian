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
        parser.execute();
    }

    @Override
    public void update() {}

    @Override
    public void update(String u) {
        this.removeAll();

        if (brain.getTarget() != null) {
            start();
        }
    }

    public PropertiesArea() {
        super();

        this.setBackground(Color.YELLOW);
    }
}