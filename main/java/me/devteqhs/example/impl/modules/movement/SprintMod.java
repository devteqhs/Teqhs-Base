package me.devteqhs.example.impl.modules.movement;

import me.devteqhs.example.api.event.bus.Listener;
import me.devteqhs.example.api.event.bus.annotations.Target;
import me.devteqhs.example.api.module.ModuleCategory;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleInfo;
import me.devteqhs.example.impl.events.player.MotionEvent;
import me.devteqhs.example.impl.utils.player.MovementUtils;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Sprint", key = Keyboard.KEY_N, category = ModuleCategory.MOVEMENT)
public class SprintMod extends Module {

    @Target
    private final Listener<MotionEvent> motionEventListener = event -> {
        /** Change this to false if you want omni-sprint */
        boolean legit = true;

        mc.gameSettings.keyBindSprint.setPressed(MovementUtils.canSprint(legit));
    };

}
