package me.devteqhs.example.api.module;

import me.devteqhs.example.impl.modules.combat.VelocityMod;
import me.devteqhs.example.impl.modules.misc.DevelopmentMod;
import me.devteqhs.example.impl.modules.movement.NoSlowdownMod;
import me.devteqhs.example.impl.modules.movement.SprintMod;
import me.devteqhs.example.impl.modules.render.HUDMod;

import java.util.*;
import java.util.stream.Collectors;

public class ModuleManager {

    private final Map<ModuleCategory, List<Module>> modules = new EnumMap<>(ModuleCategory.class);

    public ModuleManager() {
        registerModules();
    }

    public void registerModules() {
        add(ModuleCategory.COMBAT, new VelocityMod());
        add(ModuleCategory.MOVEMENT, new SprintMod(), new NoSlowdownMod());
        add(ModuleCategory.RENDER, new HUDMod());
        add(ModuleCategory.MISC, new DevelopmentMod());
    }

    private void add(ModuleCategory category, Module... modules) {
        for (Module module : modules) {
            this.modules.computeIfAbsent(category, k -> new ArrayList<>()).add(module);
        }
    }

    public List<Module> getModules() {
        return modules.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public Module getModule(String name) {
        return getModules().stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Module[] getModulesByCategory(ModuleCategory category) {
        return getModules().stream().filter(module -> module.getCategory() == category).toArray(Module[]::new);
    }

}
