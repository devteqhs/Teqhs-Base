package me.devteqhs.example.api.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.devteqhs.example.Example;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.StringUtils;

public abstract class Module {

    protected static Minecraft mc = Minecraft.getMinecraft();
    private String name, suffix;
    private boolean toggled;
    private int key;
    private ModuleCategory category;
    private boolean listening = false;

    public Module() {
        ModuleInfo info = getClass().getAnnotation(ModuleInfo.class);
        name = info.name();
        suffix = "";
        key = info.key();
        category = info.category();
    }

    public void onEnable() {
        Example.getInstance().getEventBus().subscribe(this);
    }

    public void onDisable() {
        Example.getInstance().getEventBus().unsubscribe(this);
    }

    public void toggle() {
        toggled = !toggled;
        if(toggled) {
            onEnable();
        } else {
            onDisable();
        }
    }

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

    public boolean isListening() {
        return listening;
    }

    public void setListening(boolean listening) {
        this.listening = listening;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public ModuleCategory getCategory() {
        return category;
    }

    public void setCategory(ModuleCategory category) {
        this.category = category;
    }

    /**
     * Get module name with suffix added onto it for rendering on the arraylist
     */

    public String getDisplayName() {
        return name + (StringUtils.isNotEmpty(suffix) ? ChatFormatting.GRAY + " " + suffix : "");
    }

}
