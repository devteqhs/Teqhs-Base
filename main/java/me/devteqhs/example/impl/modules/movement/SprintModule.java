package me.devteqhs.example.impl.modules.movement;

import me.devteqhs.example.api.event.bus.Listener;
import me.devteqhs.example.api.event.bus.annotations.EventLink;
import me.devteqhs.example.api.module.ModuleCategory;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleInfo;
import me.devteqhs.example.api.property.sub.BooleanProperty;
import me.devteqhs.example.impl.events.player.MotionEvent;
import me.devteqhs.example.impl.utils.player.MovementUtils;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Sprint", key = Keyboard.KEY_N, category = ModuleCategory.MOVEMENT)
public class SprintModule extends Module {

    public BooleanProperty legit = new BooleanProperty("Legit", true);

    public SprintModule() {
        addProperties(legit);
    }

    @EventLink
    private final Listener<MotionEvent> motionEventListener = event -> {
        setSuffix(legit.getValue() ? "Legit" : "Omni");
        /** Change this to false if you want omni-sprint */

        mc.gameSettings.keyBindSprint.setPressed(MovementUtils.canSprint(legit.getValue()));
    };

}
