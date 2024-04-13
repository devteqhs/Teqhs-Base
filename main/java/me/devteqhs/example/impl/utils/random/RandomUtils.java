package me.devteqhs.example.impl.utils.random;

import java.util.Random;

public class RandomUtils {

    private static final Random random = new Random();

    public static int getRandomBetween(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return random.nextInt(max - min + 1) + min;
    }

    public static double getRandomDouble() {
        return random.nextDouble();
    }

    public static boolean getRandomBoolean() {
        return random.nextBoolean();
    }
}

