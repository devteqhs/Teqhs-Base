package me.devteqhs.example.impl.modules.movement;

import me.devteqhs.example.api.module.Category;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleInfo;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "No Slowdown", key = Keyboard.KEY_K, category = Category.MOVEMENT)
public class NoSlowdownMod extends Module {
}
