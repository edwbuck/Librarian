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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DisplayArea extends JPanel implements BrainClient, ActionListener, WorkerOutputHandler {
    private static Brain brain;

    @Override
    public void actionPerformed(@NotNull ActionEvent ae) {
        String toBePassed = ae.getActionCommand();
        String dest = makeDestination(toBePassed);
        Path destination = Paths.get(dest);
        if (Files.isDirectory(destination)) {
            brain.publish(dest);
        }
        brain.setTarget(dest, this);
        System.out.println(brain.getTarget());
    }

    @Override
    public void handleParserOutput(JComponent c) {
        SwingUtilities.invokeLater(() -> {
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            this.add(c);
            this.revalidate();
            this.repaint();
        });
    }

    private @NotNull String makeDestination(String string) {
        if (brain.getCurrentLocation().equals(brain.getRootDir())) {
            String toReturn = brain.getCurrentLocation() + string;
            return toReturn;
        }
        else {
            String toReturn = brain.getCurrentLocation() + "/" + string;
            return toReturn;
        }
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
