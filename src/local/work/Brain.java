package local.work;

import local.work.panels.BrainClient;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

public class Brain {

    private static String rootDir;
    private static String currentLocation;
    private static Window window;
    private static JPanel[] panels;
    public static Stack<String> history;
    private static boolean caller;

    public void breakdownDirectory(String path) {
        Path filepath = Paths.get(path);
        if (Files.exists(filepath) && Files.isDirectory(filepath)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(filepath)) {
                for (Path entry : stream) {
                    System.out.println(entry.getFileName());
                }
            }
            catch (Exception e) {
                System.out.println("Listing error");
            }
        }
        else {
            System.out.println("Invalid path");
        }
    }

    public void publish(String string) {
        setBackButtonState();
        breakdownDirectory(string);
        for (JPanel panel : panels) {
            if (panel instanceof BrainClient) {
                ((BrainClient) panel).update(string);
                ((BrainClient) panel).setBrain(this);
            }
        }
    }

    private static void setBackButtonState() {
        if (history.empty() || history.peek() == null) {
            window.getToolbar().getBackBtn().setEnabled(false);
        }
        else {
            window.getToolbar().getBackBtn().setEnabled(true);
        }
    }

    public void getBack() {
        String backElement = history.pop();
        this.publish(backElement);
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
        rootDir = String.valueOf('/'); // Change this logic if extending this application to Windows or Mac.
        currentLocation = null;
        publish(rootDir);
    }
}
