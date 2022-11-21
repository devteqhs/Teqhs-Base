package me.devteqhs.example.impl.utils.misc;

import net.minecraft.util.Util;

import java.util.Random;

public class MathUtils extends Util {

    public static int getRandomBetween(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

}
