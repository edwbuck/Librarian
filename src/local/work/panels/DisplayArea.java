package local.work.panels;

import local.work.Brain;
import local.work.datahandlers.DisplayParser;
import local.work.datahandlers.WorkerOutputHandler;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public class DisplayArea extends JPanel implements BrainClient, ActionListener, WorkerOutputHandler {
    private static Brain brain;
    private static BoxLayout layout;

    @Override
    public void actionPerformed(@NotNull ActionEvent ae) {
        System.out.println("action performed");
    }

    @Override
    public void handleParserOutput(JComponent c) {
        System.out.println(c);
        SwingUtilities.invokeLater(() -> {
            this.layout = new BoxLayout(this, BoxLayout.X_AXIS);
            this.add(c);
            this.revalidate();
            this.repaint();
        });
    }

    @Override
    public void setBrain(Brain brain) {
        this.brain = brain;
    }

    public void start() {
        DisplayParser parser = new DisplayParser(this, this, brain);
        parser.execute();
    }

    @Override
    public void update() {}

    @Override
    public void update(String u) {
        this.removeAll();

        start();
    }

    public DisplayArea() {
        super();
        this.setBackground(Color.PINK);

    }
}
