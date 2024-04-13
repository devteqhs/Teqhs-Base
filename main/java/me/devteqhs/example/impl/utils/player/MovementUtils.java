package me.devteqhs.example.impl.utils.player;

import me.devteqhs.example.impl.utils.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;

public class MovementUtils extends Util {

    public static boolean moving() {
        return mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0;
    }

    public static boolean canSprint(boolean legit) {
        return legit ? mc.thePlayer.moveForward >= 0.8
                && !mc.thePlayer.isCollidedHorizontally
                && !mc.thePlayer.isPotionActive(Potion.blindness)
                && (mc.thePlayer.getFoodStats().getFoodLevel() > 6 || mc.thePlayer.capabilities.allowFlying)
                && !mc.thePlayer.isSneaking()
                && !mc.thePlayer.isUsingItem()
                : moving();
    }

    public static void setMotion(float speed) {
        EntityPlayer player = mc.thePlayer;
        float yaw = player.rotationYaw;
        float forward = player.moveForward;
        float strafe = player.moveStrafing;

        float norm = MathHelper.sqrt_float((forward * forward) + (strafe * strafe));
        forward = (norm > 0.0F) ? (forward / norm) : 0.0F;
        strafe = (norm > 0.0F) ? (strafe / norm) : 0.0F;

        float rad = yaw * 0.017453292F;
        float sin = MathHelper.sin(rad);
        float cos = MathHelper.cos(rad);

        float motionX = (strafe * cos - forward * sin) * speed;
        float motionZ = (forward * cos + strafe * sin) * speed;

        player.motionX += motionX;
        player.motionZ += motionZ;
    }

}
