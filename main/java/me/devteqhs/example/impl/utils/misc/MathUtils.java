package me.devteqhs.example.impl.utils.misc;

import net.minecraft.util.Util;

import java.util.Random;

/**
 * Class for doing many math related things via utils
 *
 * @author teqhs
 * @since 15/11/2022
 */

public class MathUtils extends Util {

    /**
     * Get a random integer between a min and a max value
     */

    public static int getRandomBetween(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

}
