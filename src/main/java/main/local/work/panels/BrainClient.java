package main.local.work.panels;

import main.local.work.Brain;

public interface BrainClient {
    void update();
    void update(String u);
    void setBrain(Brain brain);
}
