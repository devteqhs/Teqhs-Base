package me.devteqhs.example.api.module;

import me.devteqhs.example.impl.modules.movement.SprintMod;
import me.devteqhs.example.impl.modules.render.HUDMod;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for handling all modules
 *
 * @author teqhs
 * @since 15/11/2022
 */

public class ModuleManager {

    /* Map to store all modules */
    private final Map<Class<? extends Module>, Module> modules = new HashMap<>();

    /* Constructor to add modules */
    public ModuleManager() {
        /* Combat */

        /* Player */

        /* Movement */
        add(new SprintMod());

        /* Render */
        add(new HUDMod());

        /* Exploit */
    }

    /**
     * Method to add modules
     */
    private void add(Module module) {
        modules.put(module.getClass(), module);
    }

    /* Getters */
    public Collection<Module> getModules() {
        return modules.values();
    }

    public Module getModule(String name) {
        return getModules().stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Module[] getModulesByCategory(Category category) {
        return getModules().stream().filter(module -> module.getCategory() == category).toArray(Module[]::new);
    }

}
