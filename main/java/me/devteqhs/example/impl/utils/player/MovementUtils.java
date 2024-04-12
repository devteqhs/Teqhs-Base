package me.devteqhs.example.impl.utils.player;

import me.devteqhs.example.impl.utils.Util;
import net.minecraft.potion.Potion;

public class MovementUtils extends Util {

    private static boolean movingEnoughToSprint() {
        return mc.thePlayer.moveForward > 0.8F ||
                mc.thePlayer.moveForward < -0.8F ||
                mc.thePlayer.moveStrafing > 0.8F ||
                mc.thePlayer.moveStrafing < -0.8F;
    }

    public static boolean canSprint(boolean legit) {
        return legit ? mc.thePlayer.moveForward >= 0.8
                && !mc.thePlayer.isCollidedHorizontally
                && !mc.thePlayer.isPotionActive(Potion.blindness)
                && (mc.thePlayer.getFoodStats().getFoodLevel() > 6 || mc.thePlayer.capabilities.allowFlying)
                && !mc.thePlayer.isSneaking()
                && !mc.thePlayer.isUsingItem()
                : movingEnoughToSprint();
    }

}
