package me.devteqhs.example.impl.ui.click;

import me.devteqhs.example.Example;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleCategory;
import me.devteqhs.example.impl.ui.click.panels.CategoryPanel;
import me.devteqhs.example.impl.ui.click.panels.buttons.ModuleButton;
import me.devteqhs.example.impl.utils.misc.Logger;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Very basic, dropdown ClickGUI.
 * Will only be used to toggle modules.
 * You can use this as a framework to add upon if you want to add your own settings system.
 */

public class DropdownClickGUI extends GuiScreen {

    private final List<CategoryPanel> categoryPanels = new ArrayList<>();

    public DropdownClickGUI() {
        int x = 10;
        for (ModuleCategory category : ModuleCategory.values()) {
            categoryPanels.add(new CategoryPanel(category, x, 10, Example.getInstance().getModuleManager()));
            x += 100;
        }
        super.initGui();
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
            for (ModuleButton button : panel.getModuleButtons()) {
                Module module = button.getModule();
                if (module.isListening()) {
                    if(keyCode == Keyboard.KEY_ESCAPE) {
                        module.setKey(0);
                        Logger.print(Logger.LogType.CHAT, String.format("Successfully unbound %s!", module.getName()));
                        module.setListening(false);
                    } else {
                        module.setKey(keyCode);
                        Logger.print(Logger.LogType.CHAT, String.format("Successfully bound %s to %s!", module.getName(), Keyboard.getKeyName(keyCode)));
                        module.setListening(false);
                    }
                    return;
                }
            }
        }
    }


}
