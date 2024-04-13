package me.devteqhs.example.impl.ui.click.panels;

import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleCategory;
import me.devteqhs.example.impl.modules.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryPanel {
    private final ModuleCategory category;
    private final int x;
    private final int y;
    private boolean expanded = false;
    private final ModuleManager moduleManager;
    private final List<ModulePanel> modulePanels = new ArrayList<>();
    private int panelWidth = 90;

    public CategoryPanel(ModuleCategory category, int x, int y, ModuleManager moduleManager) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.moduleManager = moduleManager;
        initializeModulePanels();
    }

    private void initializeModulePanels() {
        int moduleY = y + 20;
        for (Module module : moduleManager.getModulesByCategory(category)) {
            ModulePanel panel = new ModulePanel(module, x, moduleY);
            modulePanels.add(panel);
            moduleY += 20;
            panelWidth = Math.max(panelWidth, !module.getProperties().isEmpty() ? 180 : panelWidth);
        }
    }

    public void draw(int mouseX, int mouseY) {
        Gui.drawRect(x, y, x + 90, y + 20, new Color(0, 200, 255).getRGB());
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(category.toString(), x + 5, y + 6, -1);

        if (expanded) {
            modulePanels.forEach(panel -> {
                panel.draw(mouseX, mouseY);
                panelWidth = Math.max(panelWidth, panel.getExpandedWidth());
            });
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseWithinBounds(mouseX, mouseY) && mouseButton == 1) {
            expanded = !expanded;
        }

        if (expanded) {
            modulePanels.forEach(panel -> panel.mouseClicked(mouseX, mouseY, mouseButton));
        }
    }

    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if (expanded) {
            for (ModulePanel panel : modulePanels) {
                panel.keyTyped(typedChar, keyCode);
            }
        }
    }

    private boolean mouseWithinBounds(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + 90 && mouseY >= y && mouseY <= y + 20;
    }

    public List<ModulePanel> getModulePanels() {
        return modulePanels;
    }
}
