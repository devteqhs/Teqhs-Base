package me.devteqhs.example.impl.modules.misc;

import me.devteqhs.example.api.event.bus.Listener;
import me.devteqhs.example.api.event.bus.annotations.Target;
import me.devteqhs.example.api.module.ModuleCategory;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleInfo;
import me.devteqhs.example.impl.events.player.MotionEvent;
import me.devteqhs.example.impl.utils.misc.Logger;
import org.lwjgl.input.Keyboard;

/**
 * Module for testing things during development
 */

@ModuleInfo(name = "Development", category = ModuleCategory.MISC, key = Keyboard.KEY_V)
public class DevelopmentMod extends Module {

}
