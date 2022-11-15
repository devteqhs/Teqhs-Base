package me.devteqhs.example.impl.utils.misc;

import me.devteqhs.example.impl.utils.Util;
import net.minecraft.util.ChatComponentText;

/**
 * Class to log information to in game chat and system console
 *
 * @author teqhs
 * @since 15/11/20222
 */

public class Logger extends Util {

    /**
     * Log to system console
     */

    public static void log(String message) {
        System.out.print(message);
    }

    /**
     * Log to in game chat
     */

    public static void logChat(String message) {
        mc.thePlayer.addChatMessage(new ChatComponentText("§8[§fE§8]§f " + message));
    }

}
