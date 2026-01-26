package local.work.datahandlers;

import local.work.Brain;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DisplayParser extends SwingWorker<Void, Path> {

    private WorkerOutputHandler handler;
    private ActionListener listener;
    private Brain brain;

    public DisplayParser(WorkerOutputHandler handler,
                         ActionListener listener,
                         Brain brain) {

        this.handler = handler;
        this.listener = listener;
        this.brain = brain;

    }

    @Override
    protected Void doInBackground() {
        brain.resetContents();
        for (Path entry : brain.getContents()) {
            publish(entry);
        }
        return null;
    }

    @Override
    protected void process(@NotNull List<Path> chunks) {
        Dimension scP = new Dimension(600, 375);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 3, 50, 10));
        panel.setBorder(BorderFactory.createLineBorder(new Color(189, 204, 206)));
        ArrayList<Path> directories = new ArrayList<Path>();
        ArrayList<Path> files = new ArrayList<Path>();
        for (Path p : chunks) {
            if (Files.isDirectory(p)) {
                directories.add(p);
            }
            else {
                files.add(p);
            }
        }

        for (Path d : directories) {
            JButton dhyperable = new JButton(d.getFileName().toString());
            dhyperable.setBackground(new Color(255, 253, 242));
            dhyperable.setPreferredSize(new Dimension(120, 40));
            ImageIcon icon = new ImageIcon("src/resources/close-folder.png");
            dhyperable.setIcon(icon);
            dhyperable.setActionCommand(d.getFileName().toString());
            dhyperable.addActionListener(listener);
            panel.add(dhyperable);
        }

        for (Path f : files) {
            JButton fhyperable = new JButton(f.getFileName().toString());
            fhyperable.setBackground(new Color(255, 253, 242));
            fhyperable.setPreferredSize(new Dimension(120, 40));
            ImageIcon icon = new ImageIcon("src/resources/file.png");
            fhyperable.setIcon(icon);
            fhyperable.setActionCommand(f.getFileName().toString());
            fhyperable.addActionListener(listener);
            panel.add(fhyperable);
        }

        JScrollPane scrollPane = new JScrollPane(
                panel,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        scrollPane.setPreferredSize(scP);
        scrollPane.getViewport().getView().setBackground(new Color(255, 250, 198));
        handler.handleParserOutput(scrollPane);
    }

    @Override
    protected void done() {
        try {
            if (brain.getContents() != null) {
                DirectoryStream<Path> ds = brain.getContents();
                ds.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
