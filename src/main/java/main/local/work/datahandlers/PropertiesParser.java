package main.local.work.datahandlers;

import main.local.work.Brain;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PropertiesParser extends SwingWorker<Path, Void> {

    private WorkerOutputHandler handler;
    private Brain brain;

    public PropertiesParser(WorkerOutputHandler handler, Brain brain) {

        this.handler = handler;
        this.brain = brain;

    }

    @Override
    protected Path doInBackground() {
        String target = brain.getTarget();
        Path p = Paths.get(target);
        return p;
    }

    private String getFileType(Path p) {
        try {
            return Files.probeContentType(p);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Unknown file type";
        }
    }

    private @NotNull String getSize(Path p) {
        try {
        long size = Files.size(p);
        return String.valueOf(size);
    }
    catch (Exception e) {
        e.printStackTrace();
        return "Unknown size";
        }
    }

    @Override
    protected void done() {
        try {
            Path p = get();

            if (p != null) {
                JLabel name = new JLabel("Name: " + p);
                JLabel size = new JLabel("File Size: " + getSize(p) + " Bytes");
                JLabel type = new JLabel("File type: " + getFileType(p));
                JLabel parent = new JLabel("Parent: " + brain.getCurrentLocation());

                Dimension d = new Dimension(800, 125);
                JPanel panel = new JPanel();
                panel.setPreferredSize(d);
                panel.setLayout(new GridLayout(2, 2, 200, 10));
                panel.setBackground(new Color(255, 250, 198));

                panel.add(name);
                panel.add(size);
                panel.add(type);
                panel.add(parent);

                handler.handleParserOutput(panel);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
