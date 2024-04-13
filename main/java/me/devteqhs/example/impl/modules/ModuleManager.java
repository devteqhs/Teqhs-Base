package me.devteqhs.example.impl.modules;

import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleCategory;
import me.devteqhs.example.impl.modules.combat.VelocityModule;
import me.devteqhs.example.impl.modules.misc.DevelopmentModule;
import me.devteqhs.example.impl.modules.movement.NoSlowdownModule;
import me.devteqhs.example.impl.modules.movement.SprintModule;
import me.devteqhs.example.impl.modules.render.HUDMod;

import java.util.*;

public class ModuleManager {

    private final HashMap<Class<? extends Module>, Module> modules = new LinkedHashMap<>();

    public ModuleManager() {
        registerModules();
    }

    public void registerModules() {
        /** COMBAT */
        add(new VelocityModule());
        /** PLAYER */
        /** MOVEMENT */
        add(new SprintModule(), new NoSlowdownModule());
        /** EXPLOIT */
        /** RENDER */
        add(new HUDMod());
        /** MISC */
        add(new DevelopmentModule());
    }

    private void add(Module... modules) {
        Arrays.stream(modules).forEach(mod -> this.modules.put(mod.getClass(), mod));
    }

    public Collection<Module> getModules() {
        return modules.values();
    }

    public Module getModule(String name) {
        return getModules().stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Module[] getModulesByCategory(ModuleCategory category) {
        return getModules().stream().filter(module -> module.getCategory() == category).toArray(Module[]::new);
    }

}
