package me.devteqhs.example.impl.utils.misc;

import me.devteqhs.example.impl.utils.Util;
import net.minecraft.util.ChatComponentText;

public class Logger extends Util {

    public static void log(String message) {
        System.out.print(message);
    }

    public static void logChat(String message) {
        mc.thePlayer.addChatMessage(new ChatComponentText("§8[§fE§8]§f " + message));
    }

}
