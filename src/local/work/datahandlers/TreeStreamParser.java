package local.work.datahandlers;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.List;

//This class to be exclusively implemented by the FileTreeArea

public class TreeStreamParser extends SwingWorker<Void, Path> {

    private DirectoryStream<Path> ds;
    private LabelHander handler;

    public TreeStreamParser(DirectoryStream<Path> ds, LabelHander handler) {

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
        for (Path p : chunks) {
            JLabel hyperable = new JLabel(p.getFileName().toString());
            handler.handleLabel(hyperable);
        }
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