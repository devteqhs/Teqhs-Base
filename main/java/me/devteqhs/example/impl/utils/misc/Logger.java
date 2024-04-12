package me.devteqhs.example.impl.utils.misc;

import me.devteqhs.example.impl.utils.Util;
import net.minecraft.util.ChatComponentText;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger extends Util {

    /**
     * Print into either the console or in-game chat
     *
     * @param logType
     * @param contents
     */

    public static void print(LogType logType, Object contents) {
        switch (logType) {
            case CHAT:{
                mc.thePlayer.addChatMessage(new ChatComponentText("§bExample §7»§f " + contents));
                break;
            }
            case CONSOLE:{
                LocalTime now = LocalTime.now();
                String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                System.out.println("[" + time +"] [Example]: " + contents);
                break;
            }
        }
    }

    public enum LogType {
        CONSOLE,
        CHAT
    }

}
