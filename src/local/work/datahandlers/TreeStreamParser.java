package local.work.datahandlers;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.List;

//This class to be exclusively implemented by the FileTreeArea

public class TreeStreamParser extends SwingWorker<Void, Path> {

    private DirectoryStream<Path> ds;
    private WorkerOutputHandler handler;

    public TreeStreamParser(DirectoryStream<Path> ds, WorkerOutputHandler handler) {

        this.ds = ds;
        this.handler = handler;

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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (Path p : chunks) {
            JLabel hyperable = new JLabel(p.getFileName().toString());
            hyperable.setSize(200, 25);
            panel.add(hyperable);
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
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}