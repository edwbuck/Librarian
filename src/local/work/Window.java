package local.work;

import local.work.panels.DisplayArea;
import local.work.panels.Toolbar;

import javax.swing.*;
import java.awt.*;

public class Window extends JPanel{
//    private static JLabel label;
    private static Toolbar toolbar;
    private static DisplayArea label;

    public Window() {
        Window.toolbar = new Toolbar();
        Window.label = new DisplayArea();
        this.setLayout(new BorderLayout());
//        Window.label = new JLabel("Hello World");
//        label.setAlignmentY(0.5f);
        label.setAlignmentY(0.5f);
        this.add(toolbar, BorderLayout.NORTH);
        this.add(label, BorderLayout.CENTER);
    }
}
