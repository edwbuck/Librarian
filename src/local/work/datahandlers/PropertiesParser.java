package local.work.datahandlers;

import local.work.Brain;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PropertiesParser extends SwingWorker<Void, Path> {

    private WorkerOutputHandler handler;
    private Brain brain;

    public PropertiesParser(WorkerOutputHandler handler, Brain brain) {

        this.handler = handler;
        this.brain = brain;

    }

    @Override
    protected Void doInBackground() {
        String target = brain.getTarget();
        Path p = Paths.get(target);
        publish(p);
        return null;
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
    protected void process(@NotNull List<Path> chunks) {
        for (Path p : chunks) {
            JLabel name = new JLabel("Name: " + p.getFileName().toString());
            JLabel size = new JLabel("File Size: " + getSize(p));
            JLabel type = new JLabel("File type: " + getFileType(p));
            JLabel parent = new JLabel("Parent: " + brain.getCurrentLocation());

            Dimension d = new Dimension(800, 125);
            JPanel panel = new JPanel();

            panel.add(name);
            panel.add(size);
            panel.add(type);
            panel.add(parent);

            handler.handleParserOutput(panel);
        }
    }
}
