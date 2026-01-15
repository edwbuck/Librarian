package local.work.panels;

import local.work.Brain;
import local.work.datahandlers.WorkerOutputHandler;
import local.work.datahandlers.TreeStreamParser;

import javax.swing.*;
import java.awt.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public class FileTreeArea extends JPanel implements BrainClient, WorkerOutputHandler {
    private static JLabel label;
    private static Brain brain;
    private static GroupLayout layout;
    private static GroupLayout.SequentialGroup group;

    public static JLabel getLabel() {
        return label;
    }

    public static void setLabel(String t) {
        label.setText(t);
    }

    @Override
    public void setBrain(Brain brain) {
        this.brain = brain;
    }

   public void start() {
        DirectoryStream<Path> ds = brain.getContents();
        if (ds != null) {
            TreeStreamParser parser = new TreeStreamParser(ds, this);
            parser.execute();
        }
        else {
            System.out.println("No Directory Stream returned!");
        }
   }

   @Override
   public void handleParserOutput(JComponent c) {
        SwingUtilities.invokeLater(() -> {
            group.addComponent(c);
            this.revalidate();
            this.repaint();
        });
   }

    @Override
    public void update() {}

    @Override
    public void update(String u) {
        this.removeAll();
        this.layout = new GroupLayout(this);
        this.group = layout.createSequentialGroup();
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        this.label = new JLabel();
        ImageIcon icon = new ImageIcon("src/resources/open-folder.png");
        label.setIcon(icon);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        setLabel(u);

        group.addComponent(label);
        start();
    }

    public FileTreeArea() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.YELLOW);
    }
}
