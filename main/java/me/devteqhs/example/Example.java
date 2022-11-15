package me.devteqhs.example;

import me.devteqhs.example.api.event.Event;
import me.devteqhs.example.api.event.bus.Listener;
import me.devteqhs.example.api.event.bus.annotations.Target;
import me.devteqhs.example.api.event.bus.bus.impl.EventBus;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleManager;
import me.devteqhs.example.impl.events.player.KeyEvent;
import me.devteqhs.example.impl.utils.misc.Logger;
import org.lwjgl.opengl.Display;

/**
 * Main client class
 * All necessary client info and methods are stored here
 *
 * @author teqhs
 * @since 15/11/2022
 */

public class Example {

    /* Client Instance */
    public static Example INSTANCE = new Example();
    /* Client info */
    private final String name = "Example", build = "0.1";
    /* Client event bus */
    private EventBus<Event> eventBus = new EventBus<>();
    /* Client managers */
    private ModuleManager moduleManager;

    /**
     * Client start method
     * All variables are initialized here
     */

    public void start() {
        /* Set display title */
        Display.setTitle(name + " " + build);
        /* initialize moduleManager */
        moduleManager = new ModuleManager();
        /* Register client to event bus */
        eventBus = new EventBus<>();
        eventBus.subscribe(this);
    }

    /* Key press handler */
    @Target
    private final Listener<KeyEvent> keyEventListener = event -> {
        getModuleManager().getModules().stream().filter(module -> module.getKey() == event.getKey()).forEach(Module::toggle);
    };

    /**
     * Getters and Setters
     */

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
