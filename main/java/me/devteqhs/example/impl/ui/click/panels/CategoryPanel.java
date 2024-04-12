package me.devteqhs.example.impl.ui.click.panels;

import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleCategory;
import me.devteqhs.example.api.module.ModuleManager;
import me.devteqhs.example.impl.ui.click.panels.buttons.ModuleButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryPanel {
    private final ModuleCategory category;
    private final int x, y;
    private boolean expanded = false;
    private final ModuleManager moduleManager;
    private final List<ModuleButton> moduleButtons = new ArrayList<>();

    public CategoryPanel(ModuleCategory category, int x, int y, ModuleManager moduleManager) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.moduleManager = moduleManager;
        initModuleButtons();
    }

    private void initModuleButtons() {
        int moduleY = y + 20;
        for (Module module : moduleManager.getModulesByCategory(category)) {
            moduleButtons.add(new ModuleButton(module, x, moduleY));
            moduleY += 20;
        }
    }

    public void draw(int mouseX, int mouseY) {
        Gui.drawRect(x, y, x + 90, y + 20, new Color(0, 200, 255).getRGB());
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(category.toString(), x + 5, y + 6, -1);

        if (expanded) {
            for (ModuleButton button : moduleButtons) {
                button.draw(mouseX, mouseY);
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseWithinBounds(mouseX, mouseY, x, y, 90, 20) && mouseButton == 1) {
            expanded = !expanded;
        }

        if (expanded) {
            for (ModuleButton button : moduleButtons) {
                button.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }

    private boolean mouseWithinBounds(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
