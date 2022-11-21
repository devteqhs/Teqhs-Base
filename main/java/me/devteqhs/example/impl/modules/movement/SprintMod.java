package me.devteqhs.example.impl.modules.movement;

import me.devteqhs.example.api.event.bus.Listener;
import me.devteqhs.example.api.event.bus.annotations.Target;
import me.devteqhs.example.api.module.Category;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleInfo;
import me.devteqhs.example.impl.events.player.MotionEvent;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Sprint", key = Keyboard.KEY_N, category = Category.MOVEMENT)
public class SprintMod extends Module {

    @Target
    private final Listener<MotionEvent> motionEventListener = event -> {
      if(mc.thePlayer.moveForward > 0 || mc.thePlayer.moveStrafing > 0) {
          mc.thePlayer.setSprinting(true);
      }
    };

}
