package me.devteqhs.example;

import me.devteqhs.example.api.event.Event;
import me.devteqhs.example.api.event.bus.Listener;
import me.devteqhs.example.api.event.bus.annotations.Target;
import me.devteqhs.example.api.event.bus.bus.impl.EventBus;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleManager;
import me.devteqhs.example.impl.events.KeyPressEvent;
import me.devteqhs.example.impl.ui.click.DropdownClickGUI;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 * The main class of the client
 *
 * @author teqhs
 */

public final class Example {

    private static final Example INSTANCE = new Example();
    private static final String NAME = "Example";
    private static final String BUILD = "0.1";

    private EventBus<Event> eventBus;
    private ModuleManager moduleManager;
    private DropdownClickGUI clickGUI;

    private Example() {
        eventBus = new EventBus<>();
        moduleManager = new ModuleManager();
        eventBus.subscribe(this);
    }

    public static Example getInstance() {
        return INSTANCE;
    }

    public void start() {
        Display.setTitle(NAME + " " + BUILD);
    }

    @Target
    private final Listener<KeyPressEvent> keyPressEventListener = event -> {
        if (event.getKey() == Keyboard.KEY_RSHIFT) {
            if (clickGUI == null) {
                clickGUI = new DropdownClickGUI();
            }
            Minecraft.getMinecraft().displayGuiScreen(clickGUI);
        }
        moduleManager.getModules().stream()
                .filter(module -> module.getKey() == event.getKey())
                .forEach(Module::toggle);
    };

    public String getName() {
        return NAME;
    }

    public String getBuild() {
        return BUILD;
    }

    public EventBus<Event> getEventBus() {
        return eventBus;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}
