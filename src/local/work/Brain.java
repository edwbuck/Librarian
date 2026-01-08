package local.work;

import local.work.panels.BrainClient;
import local.work.panels.Toolbar;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Stack;

public class Brain {

    private static FileSystem fileSystem;
    private static String rootDir;
    private static String currentLocation;
    private static Window window;
    private static JPanel[] panels;
    public static Stack<String> history;
    private static boolean caller;

    public void publish(String string) {
        currentLocation = string;
        for (JPanel panel : panels) {
            if (panel instanceof BrainClient) {
                ((BrainClient) panel).update(string);
                ((BrainClient) panel).setBrain(this);
            }
        }
    }

    public void getBack() {
        JButton bb = window.getToolbar().getBackBtn();
        if (history.empty()) {
            bb.setEnabled(false);
        // In future, replace terminal println with window in message
        }
        else if (history.peek() != null && !history.empty()) {
            String backElement = history.pop();
            this.publish(backElement);
            if (!bb.isEnabled()) {
                bb.setEnabled(true);
            }
        }
        else if (history.peek() == null && !history.empty()) {
            this.publish(rootDir);
            if (bb.isEnabled()) {
                bb.setEnabled(false);
            }
        }
    }

    public String getRootDir() {
        return rootDir;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public Brain(@NotNull Window window) {
        this.window = window;
        this.panels = window.getPanels();
        history = new Stack<String>();
        fileSystem = FileSystems.getDefault();
        rootDir = String.valueOf('/'); // Change this logic if extending this application to Windows or Mac.
        currentLocation = null;
        publish(rootDir);
    }
}
