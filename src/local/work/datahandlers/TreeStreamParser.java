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

    public TreeStreamParser(DirectoryStream<Path> ds) {

        this.ds = ds;
    }

    @Override
    protected Void doInBackground() throws Exception {
        for (Path entry : ds) {
            publish(entry);
        }
        return null;
    }

    @Override
    protected void process(@NotNull List<Path> chunks) {
        Path p = chunks.get(chunks.size() - 1);
        JLabel hyperable = new JLabel();
        hyperable.setText(p.getFileName().toString());
    }
}
