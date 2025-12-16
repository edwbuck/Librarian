package local.work;

import local.work.panels.Toolbar;

import javax.swing.*;

public class Window extends JPanel{
//    private static JLabel label;
    private static Toolbar toolbar;
    private static Dummy label;

    public Window() {
        Window.toolbar = new Toolbar();
        Window.label = new Dummy();
//        Window.label = new JLabel("Hello World");
//        label.setAlignmentY(0.5f);
        label.setAlignmentY(0.5f);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(toolbar, 0);
        this.add(label, 1);
    }
}
