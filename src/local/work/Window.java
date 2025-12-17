package local.work;

import local.work.panels.DisplayArea;
import local.work.panels.FileTreeArea;
import local.work.panels.PropertiesArea;
import local.work.panels.Toolbar;

import javax.swing.*;
import java.awt.*;

public class Window extends JPanel{
//    private static JLabel label;
    private static Toolbar toolbar;
    private static DisplayArea label;
    private static FileTreeArea fileTreeArea;
    private static PropertiesArea propertiesArea;

    public Window() {
        Window.toolbar = new Toolbar();
        Window.label = new DisplayArea();
        Window.fileTreeArea = new FileTreeArea();
        Window.propertiesArea = new PropertiesArea();
        this.setLayout(new BorderLayout());
//        Window.label = new JLabel("Hello World");
//        label.setAlignmentY(0.5f);
        this.add(toolbar, BorderLayout.NORTH);
        this.add(label, BorderLayout.EAST);
        this.add(fileTreeArea, BorderLayout.WEST);
        this.add(propertiesArea, BorderLayout.SOUTH);
    }
}
