package local.work.datahandlers;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DisplayParser extends SwingWorker<Void, Path> {

    private DirectoryStream<Path> ds;
    private WorkerOutputHandler handler;
    private ActionListener listener;

    public DisplayParser(
            DirectoryStream<Path> ds,
            WorkerOutputHandler handler,
            ActionListener listener) {

        this.ds = ds;
        this.handler = handler;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground() throws Exception {
        for (Path entry : this.ds) {
            publish(entry);
        }
        return null;
    }

    @Override
    protected void process(@NotNull List<Path> chunks) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
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
            JButton dhyperable = new JButton();
            ImageIcon icon = new ImageIcon("src/resources/close-folder.png");
            dhyperable.setIcon(icon);
            dhyperable.setActionCommand(d.getFileName().toString());
            dhyperable.addActionListener(listener);
            panel.add(dhyperable);
        }

        for (Path f : files) {
            JButton fhyperable = new JButton();
            ImageIcon icon = new ImageIcon("src/resources/close-folder.png");
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
        Dimension scP = new Dimension(600, 375);
        scrollPane.setPreferredSize(scP);
        handler.handleParserOutput(scrollPane);
    }
}
