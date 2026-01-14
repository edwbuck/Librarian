package local.work;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    private static void instLibrarian() {
        Dimension d = new Dimension(800, 500);
        JFrame frame = new JFrame();
        Window window = new Window();
        Brain brain = new Brain(window);

        try {
            BufferedImage icon = ImageIO.read(new File("src/resources/book-logo.png"));
            Image rIcon = icon.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            frame.setIconImage(rIcon);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(window);
        frame.pack();
        frame.setMinimumSize(d);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                instLibrarian();
            }
        });
    }
}
