package main.local.work.panels;

import main.local.work.Brain;
import main.local.work.datahandlers.PropertiesParser;
import main.local.work.datahandlers.WorkerOutputHandler;

import javax.swing.*;
import java.awt.*;

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

        this.setBackground(new Color(255, 250, 198));
    }
}