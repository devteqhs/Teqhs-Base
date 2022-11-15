package me.devteqhs.example.impl.events.player;

import me.devteqhs.example.api.event.Event;

/**
 * Event for handling key presses
 *
 * @author teqhs
 * @since 15/11/2022
 */

public class KeyEvent extends Event {

    /* Key */
    private int key;

    /* Constructor */
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
