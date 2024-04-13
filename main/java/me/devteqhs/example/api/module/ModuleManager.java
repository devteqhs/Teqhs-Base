package me.devteqhs.example.api.module;

import me.devteqhs.example.impl.modules.combat.VelocityMod;
import me.devteqhs.example.impl.modules.misc.DevelopmentMod;
import me.devteqhs.example.impl.modules.movement.NoSlowdownMod;
import me.devteqhs.example.impl.modules.movement.SprintMod;
import me.devteqhs.example.impl.modules.render.HUDMod;

import java.util.*;

public class ModuleManager {

    private final HashMap<Class<? extends Module>, Module> modules = new LinkedHashMap<>();

    public ModuleManager() {
        registerModules();
    }

    public void registerModules() {
        /** COMBAT */
        add(new VelocityMod());
        /** PLAYER */
        /** MOVEMENT */
        add(new SprintMod(), new NoSlowdownMod());
        /** EXPLOIT */
        /** RENDER */
        add(new HUDMod());
        /** MISC */
        add(new DevelopmentMod());
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
