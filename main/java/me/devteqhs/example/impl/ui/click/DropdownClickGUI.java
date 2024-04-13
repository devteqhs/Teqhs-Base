package me.devteqhs.example.impl.ui.click;

import me.devteqhs.example.Example;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleCategory;
import me.devteqhs.example.impl.modules.ModuleManager;
import me.devteqhs.example.impl.ui.click.panels.CategoryPanel;
import me.devteqhs.example.impl.ui.click.panels.ModulePanel;
import me.devteqhs.example.impl.utils.misc.Logger;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Incredibly basic ClickGUI with a settings and binding system.
 * I spent a very short amount of time on this, so it is NOT good.
 * I would recommend you to improve upon this.
 */

public class DropdownClickGUI extends GuiScreen {
    private final List<CategoryPanel> categoryPanels = new ArrayList<>();

    public DropdownClickGUI() {
        initializePanels();
        super.initGui();
    }

    private void initializePanels() {
        int x = 10;
        ModuleManager moduleManager = Example.getInstance().getModuleManager();
        for (ModuleCategory category : ModuleCategory.values()) {
            categoryPanels.add(new CategoryPanel(category, x, 10, moduleManager));
            x += 100;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for (CategoryPanel panel : categoryPanels) {
            panel.draw(mouseX, mouseY);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (CategoryPanel panel : categoryPanels) {
            panel.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        for (CategoryPanel panel : categoryPanels) {
            if (handleKeyBindings(typedChar, keyCode, panel)) return;
        }
    }

    private boolean handleKeyBindings(char typedChar, int keyCode, CategoryPanel panel) throws IOException {
        for (ModulePanel button : panel.getModulePanels()) {
            Module module = button.getModule();
            if (module.isListening()) {
                processKeyBinding(keyCode, module);
                return true;
            }
            button.keyTyped(typedChar, keyCode);
        }
        return false;
    }

    private void processKeyBinding(int keyCode, Module module) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            module.setKey(0);
            Logger.print(Logger.LogType.CHAT, String.format("Successfully unbound %s!", module.getName()));
        } else {
            module.setKey(keyCode);
            Logger.print(Logger.LogType.CHAT, String.format("Successfully bound %s to %s!", module.getName(), Keyboard.getKeyName(keyCode)));
        }
        module.setListening(false);
    }
}

