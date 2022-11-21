package me.devteqhs.example;

import me.devteqhs.example.api.event.Event;
import me.devteqhs.example.api.event.bus.Listener;
import me.devteqhs.example.api.event.bus.annotations.Target;
import me.devteqhs.example.api.event.bus.bus.impl.EventBus;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleManager;
import me.devteqhs.example.impl.events.player.KeyEvent;
import me.devteqhs.example.impl.ui.click.ClickGUI;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public final class Example {

    public static Example INSTANCE = new Example();
    private final String name = "Example", build = "0.1";
    private EventBus<Event> eventBus = new EventBus<>();
    private ModuleManager moduleManager;

    public void start() {
        Display.setTitle(name + " " + build);
        moduleManager = new ModuleManager();
        eventBus = new EventBus<>();
        eventBus.subscribe(this);
    }

    @Target
    private final Listener<KeyEvent> keyEventListener = event -> {
        ClickGUI clickGUI = null;
        if(event.getKey() == Keyboard.KEY_RSHIFT) {
            Minecraft.getMinecraft().displayGuiScreen(clickGUI == null ? new ClickGUI() : clickGUI);
        }
        getModuleManager().getModules().stream().filter(module -> module.getKey() == event.getKey()).forEach(Module::toggle);
  };

    public String getName() {
        return name;
    }

    public String getBuild() {
        return build;
    }

    public EventBus<Event> getEventBus() {
        return eventBus;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}
