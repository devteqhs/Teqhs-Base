package me.devteqhs.example.impl.ui.click.panels.buttons;

import me.devteqhs.example.api.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.io.IOException;

public class ModuleButton {
    private final Module module;
    private final int x, y, width = 90, height = 20;
    private boolean expanded = false;

    public ModuleButton(Module module, int x, int y) {
        this.module = module;
        this.x = x;
        this.y = y;
    }

    public void draw(int mouseX, int mouseY) {
        Gui.drawRect(x, y, x + width, y + height, new Color(25, 25, 55).getRGB());
        String displayText = module.isListening() ? "Listening..." : module.getName();
        Minecraft.getMinecraft().fontRendererObj.drawString(displayText, x + 2, y + (height - 8) / 2, module.isToggled() ? new Color(79, 214, 255).getRGB() : -1);
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (inBounds(mouseX, mouseY, x, y, width, height)) {
            if (mouseButton == 0) {
                module.toggle();
            } else if (mouseButton == 1) {
                expanded = !expanded;
            } else if (mouseButton == 2) {
                module.setListening(true);
            }
        }
    }

    private boolean inBounds(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public Module getModule() {
        return module;
    }
}
