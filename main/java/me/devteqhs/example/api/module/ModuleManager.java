package me.devteqhs.example.api.module;

import me.devteqhs.example.impl.modules.combat.VelocityMod;
import me.devteqhs.example.impl.modules.movement.NoSlowdownMod;
import me.devteqhs.example.impl.modules.movement.SprintMod;
import me.devteqhs.example.impl.modules.render.HUDMod;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModuleManager {

    private final Map<Class<? extends Module>, Module> modules = new LinkedHashMap<>();

    public ModuleManager() {
        /* Combat */
        add(new VelocityMod());

        /* Player */

        /* Movement */
        add(new SprintMod());
        add(new NoSlowdownMod());

        /* Render */
        add(new HUDMod());

        /* Exploit */
    }

    private void add(Module module) {
        modules.put(module.getClass(), module);
    }

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
