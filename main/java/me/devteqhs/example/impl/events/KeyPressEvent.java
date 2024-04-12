package me.devteqhs.example.impl.events;

import me.devteqhs.example.api.event.Event;

public class KeyPressEvent extends Event {

    private int key;

    public KeyPressEvent(int key) {
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
