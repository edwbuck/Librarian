package local.work;

import local.work.datahandlers.DisplayParser;
import local.work.datahandlers.PropertiesParser;
import local.work.panels.BrainClient;
import local.work.panels.DisplayArea;
import local.work.panels.FileTreeArea;
import local.work.panels.PropertiesArea;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

public class Brain {

    private static String rootDir;
    private static String currentLocation;
    private static String target;
    private static JFrame frame;
    private static Window window;
    private static JPanel[] panels;
    public static Stack<String> history;
    private DirectoryStream<Path> contents;

    public DirectoryStream<Path> breakdownDirectory(String path) {
        Path filepath = Paths.get(path);
        if (Files.exists(filepath) && Files.isDirectory(filepath)) {
            try {
                return Files.newDirectoryStream(filepath);
            }
            catch (Exception e) {
                System.out.println("Listing error");
                return null;
            }
        }
        else {
            System.out.println("Invalid path");
            return null;
        }
    }

    public void getBack() {
        if (history.size() > 1) {
            history.pop();
            String backElement = history.pop();
            this.publish(backElement);
        }
    }

    public DirectoryStream<Path> getContents() { return contents; }

    public String getCurrentLocation() { return currentLocation; }

    public String getRootDir() {
        return rootDir;
    }

    public String getTarget() { return target; }

    public void handleErrorMessage(String message) {
        JDialog messageBox = new JDialog(frame, "Unfortunate error");
        JLabel label = new JLabel(message);
        messageBox.setSize(300, 200);
        messageBox.getContentPane().setBackground(new Color(255, 250, 198));
        messageBox.add(label);
        messageBox.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        messageBox.setModal(true);
        messageBox.setVisible(true);
    }


    public void publish(String string) {
        setCurrentLocation(string);
        if (history.empty()) {
            history.push(currentLocation);
        }
        if (!history.peek().equals(currentLocation)) {
            history.push(currentLocation);
        }
        setBackButtonState();
        this.contents = breakdownDirectory(string);
        for (JPanel panel : panels) {
            if (panel instanceof BrainClient) {
                ((BrainClient) panel).update(this.getCurrentLocation());
            }
        }
    }

    public void refresh() {
        String peekValue = history.peek();
        publish(peekValue);
    }

    public void resetContents() {
        this.contents = breakdownDirectory(history.peek());
    }

    private static void setBackButtonState() {
        if (history.size() <= 1) {
            window.getToolbar().getBackBtn().setEnabled(false);
        }
        else {
            window.getToolbar().getBackBtn().setEnabled(true);
        }
    }

    public static void setCurrentLocation(String currentLocation) {
        Brain.currentLocation = currentLocation;
    }

    public void setTarget(String t, BrainClient client) {
        if (client instanceof DisplayArea) {
            target = t;
            for (JPanel panel : panels) {
                if (panel instanceof PropertiesArea) {
                    ((PropertiesArea) panel).update(target);
                }
            }
        }
        else {
            target = currentLocation;
        }
    }

    public Brain(@NotNull Window window, JFrame frame) {
        this.window = window;
        this.panels = window.getPanels();
        this.frame = frame;
        for (JPanel panel : panels) {
            if (panel instanceof BrainClient) {
                ((BrainClient) panel).setBrain(this);
            }
        }
        history = new Stack<String>();
        rootDir = String.valueOf('/'); // Change this logic if extending this application to Windows or Mac.
        currentLocation = null;
        target = null;
        publish(rootDir);
    }
}
