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
        int[] rh = {125, 125, 125, 125};
        int[] cw = {200, 200, 200, 200};
        GridBagLayout layout = new GridBagLayout();
        layout.rowHeights = rh;
        layout.columnWidths = cw;
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.CENTER;

        Window.toolbar = new Toolbar();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        this.add(toolbar, gbc);

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        Window.fileTreeArea = new FileTreeArea();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        this.add(fileTreeArea, gbc);

        Window.displayArea = new DisplayArea();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        this.add(displayArea, gbc);

        Window.propertiesArea = new PropertiesArea();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        this.add(propertiesArea, gbc);
    }
}