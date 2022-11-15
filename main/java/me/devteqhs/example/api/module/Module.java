package me.devteqhs.example.api.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.devteqhs.example.Example;
import net.minecraft.client.Minecraft;

/**
 * Class all modules will extend from
 * Also stores all module info
 *
 * @author teqhs
 * @since 15/11/2022
 */

public class Module {

    /* All Module Info */
    protected static Minecraft mc = Minecraft.getMinecraft();
    private String name, suffix;
    private boolean toggled;
    private int key;
    private Category category;

    /* Set module info to the correct info via constructor */
    public Module() {
        ModuleInfo info = getClass().getAnnotation(ModuleInfo.class);
        name = info.name();
        suffix = "";
        key = info.key();
        toggled = toggled;
        category = info.category();

    }

    /* On enable and disable methods */
    public void onEnable() {}
    public void onDisable() {}

    /* Method to toggle modules */
    public void toggle() {
        toggled = !toggled;
        if(toggled) {
            Example.INSTANCE.getEventBus().subscribe(this);
            onEnable();
        } else {
            Example.INSTANCE.getEventBus().unsubscribe(this);
            onDisable();
        }
    }

    /* Getters and Setters */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDisplayName() {
        boolean hasSuffix = !suffix.equalsIgnoreCase("");
        return getName() + (hasSuffix ? ChatFormatting.GRAY + " " + suffix : "");
    }

}
