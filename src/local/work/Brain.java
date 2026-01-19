package local.work;

import local.work.panels.BrainClient;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

public class Brain {

    private static String rootDir;
    private static String currentLocation;
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

    public boolean isStreamOpen() {
        try {
            boolean hasNext = contents.iterator().hasNext();
            return hasNext;
        }
        catch (Exception e) {
            return false;
        }
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

    public void getBack() {
        if (history.size() > 1) {
            history.pop();
            String backElement = history.pop();
            this.publish(backElement);
        }
    }

    public String getRootDir() {
        return rootDir;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public DirectoryStream<Path> getContents() {
        return contents;
    }

    // Add logic to set the current location so that the parts of the window are updated according
    // to the most current information.
    public static void setCurrentLocation(String currentLocation) {
        Brain.currentLocation = currentLocation;
    }

    public Brain(@NotNull Window window) {
        this.window = window;
        this.panels = window.getPanels();
        for (JPanel panel : panels) {
            if (panel instanceof BrainClient) {
                ((BrainClient) panel).setBrain(this);
            }
        }
        history = new Stack<String>();
        rootDir = String.valueOf('/'); // Change this logic if extending this application to Windows or Mac.
        currentLocation = null;
        publish(rootDir);
    }
}
