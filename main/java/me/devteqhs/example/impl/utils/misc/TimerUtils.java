package me.devteqhs.example.impl.utils.misc;

import me.devteqhs.example.impl.utils.Util;

/**
 * Class for measuring time elapsed
 *
 * @author teqhs
 * @since 15/11/2022
 */

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
