package local.work;

import local.work.panels.DisplayArea;
import local.work.panels.FileTreeArea;
import local.work.panels.PropertiesArea;
import local.work.panels.Toolbar;

import javax.swing.*;
import java.awt.*;

public class Window extends JPanel{

    private static Toolbar toolbar;
    private static DisplayArea displayArea;
    private static FileTreeArea fileTreeArea;
    private static PropertiesArea propertiesArea;

    public Window() {
        Window.toolbar = new Toolbar();
        Window.displayArea = new DisplayArea();
        Window.fileTreeArea = new FileTreeArea();
        Window.propertiesArea = new PropertiesArea();

        this.setLayout(new BorderLayout());

        this.add(toolbar, BorderLayout.NORTH);
        this.add(displayArea, BorderLayout.CENTER);
        this.add(fileTreeArea, BorderLayout.WEST);
        this.add(propertiesArea, BorderLayout.SOUTH);
    }
}