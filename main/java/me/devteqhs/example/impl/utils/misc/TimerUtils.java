package me.devteqhs.example.impl.utils.misc;

import me.devteqhs.example.impl.utils.Util;

public class TimerUtils extends Util {

    private long lastMS = System.currentTimeMillis();

    public void reset() {
        this.lastMS = System.currentTimeMillis();
    }

    public boolean hasReached(double delay) {
        return System.currentTimeMillis() - this.lastMS >= delay;
    }

    public boolean hasReached(long delay) {
        return System.currentTimeMillis() - this.lastMS >= delay;
    }

    public long getTime() {
        return System.currentTimeMillis() - this.lastMS;
    }


}
