package me.devteqhs.example.api.module;

public enum ModuleCategory {

    COMBAT("Combat"),
    PLAYER("Player"),
    MOVEMENT("Movement"),
    RENDER("Render"),
    EXPLOIT("Exploit"),
    MISC("Misc");

    private final String name;

    ModuleCategory(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

}
