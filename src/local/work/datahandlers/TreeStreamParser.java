package local.work.datahandlers;

import local.work.Brain;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

//This class to be exclusively implemented by the FileTreeArea

public class TreeStreamParser extends SwingWorker<Void, Path> {

    private DirectoryStream<Path> ds;
    private WorkerOutputHandler handler;
    private ActionListener listener;
    private Brain brain;

    public TreeStreamParser(DirectoryStream<Path> ds,
                            WorkerOutputHandler handler,
                            ActionListener listener,
                            Brain brain) {

        this.ds = ds;
        this.handler = handler;
        this.listener = listener;
        this.brain = brain;

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
        Insets m = new Insets(4, 4, 4, 2);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (Path p : chunks) {
            if (Files.isDirectory(p)) {
                JButton hyperable = new JButton(p.getFileName().toString());
                ImageIcon icon = new ImageIcon("src/resources/close-folder.png");
                hyperable.setIcon(icon);
                hyperable.setMargin(m);
                hyperable.setActionCommand(p.getFileName().toString());
                hyperable.addActionListener(listener);
                panel.add(hyperable);
            }
        }
        JScrollPane scrollPane = new JScrollPane(
                panel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        Dimension scP = new Dimension(200, 375);
        scrollPane.setPreferredSize(scP);
        handler.handleParserOutput(scrollPane);
    }

    @Override
    protected void done() {
        try {
            if (ds != null) {
                ds.close();
                if (!brain.isStreamOpen()) {
                    brain.resetContents();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
