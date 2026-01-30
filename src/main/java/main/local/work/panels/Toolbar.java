package main.local.work.panels;

import main.local.work.Brain;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Toolbar extends JPanel implements BrainClient, ActionListener {
    private static JButton homeBtn;
    private static JButton refreshBtn;
    private static JButton rootBtn;
    private static JButton backBtn;
    private static ImageIcon homeIcon;
    private static ImageIcon refreshIcon;
    private static ImageIcon rootIcon;
    private static ImageIcon backIcon;
    private static JTextField addressBar;
    private static Brain brain;
    private static Dimension d;
    private static Dimension iconSize;

    @Override
    public void actionPerformed(@NotNull ActionEvent e) {
        if (e.getSource() == homeBtn) {
            String homeIndex = new String("");
            ArrayList<Path> annex = new ArrayList<Path>();
            var root = brain.breakdownDirectory(brain.getRootDir());
            for (Path p : root) {
                if (p.toString().equals("/home")) {
                    homeIndex = p.toString();
                }
            }
            var home = brain.breakdownDirectory(homeIndex);
            for (Path q : home) {
                annex.add(q);
            }
            brain.publish(annex.get(1).toString());
        }
        else if (e.getSource() == rootBtn) {
            brain.history.push(brain.getCurrentLocation());
            brain.publish(brain.getRootDir());
        }
        else if (e.getSource() == backBtn) {
            brain.getBack();
        }
        else if (e.getSource() == refreshBtn) {
            brain.refresh();
        }
        else if (e.getSource() == addressBar) {
            takeInput();
        }
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public static void setAddressBar(String t) {
        addressBar.setText(t);
    }

    @Override
    public void setBrain(Brain brain) {
        this.brain = brain;
    }

    private @NotNull ImageIcon setIconSize(@NotNull ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    @Override
   public void update() {}

    @Override
    public void update(String u) {
        setAddressBar(u);
        brain.setTarget(u, this);
    }

    public void takeInput() {
        String userInput = addressBar.getText();
        Path tryPath = Paths.get(userInput);
        if (Files.exists(tryPath)) {
            brain.publish(userInput);
        }
        else {
            String message = new String("Not a valid address!");
            brain.handleErrorMessage(message);
        }
    }


    public Toolbar() {
        super();

        Border border = BorderFactory.createLineBorder(new Color(189, 204, 206));

        Toolbar.iconSize = new Dimension(32, 32);

        Toolbar.homeIcon = new ImageIcon(getClass().getResource("/home-2504.png"));
        Toolbar.homeIcon = setIconSize(Toolbar.homeIcon);
        Toolbar.homeBtn = new JButton(Toolbar.homeIcon);
        Toolbar.homeBtn.setPreferredSize(Toolbar.iconSize);
        Toolbar.homeBtn.addActionListener(this);

        Toolbar.refreshIcon = new ImageIcon(getClass().getResource("/recycle.png"));
        Toolbar.refreshIcon = setIconSize(Toolbar.refreshIcon);
        Toolbar.refreshBtn = new JButton(Toolbar.refreshIcon);
        Toolbar.refreshBtn.setPreferredSize(Toolbar.iconSize);
        Toolbar.refreshBtn.addActionListener(this);

        Toolbar.rootIcon = new ImageIcon(getClass().getResource("/admin-9580.png"));
        Toolbar.rootIcon = setIconSize(Toolbar.rootIcon);
        Toolbar.rootBtn = new JButton(Toolbar.rootIcon);
        Toolbar.rootBtn.setPreferredSize(iconSize);
        Toolbar.rootBtn.addActionListener(this);

        Toolbar.backIcon = new ImageIcon(getClass().getResource("/back.png"));
        Toolbar.backIcon = setIconSize(Toolbar.backIcon);
        Toolbar.backBtn = new JButton(Toolbar.backIcon);
        Toolbar.backBtn.setPreferredSize(iconSize);
        Toolbar.backBtn.addActionListener(this);

        Toolbar.d = new Dimension(500, 32);
        Toolbar.addressBar = new JTextField();
        addressBar.addActionListener(this);
        addressBar.setPreferredSize(d);
        Font font = new Font("Caladea", Font.PLAIN, 18);
        addressBar.setFont(font);
        addressBar.setForeground(new Color(113, 106, 110));

        backBtn.setBorder(border);
        refreshBtn.setBorder(border);
        rootBtn.setBorder(border);
        homeBtn.setBorder(border);
        addressBar.setBorder(border);

        this.add(backBtn);
        this.add(refreshBtn);
        this.add(rootBtn);
        this.add(homeBtn);
        this.add(addressBar);
        this.setBackground(new Color(255, 250, 198));
    }
}
