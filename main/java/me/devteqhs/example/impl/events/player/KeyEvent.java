package me.devteqhs.example.impl.events.player;

import me.devteqhs.example.api.event.Event;

public class KeyEvent extends Event {

    private int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    /* Getters and Setters */

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

}
