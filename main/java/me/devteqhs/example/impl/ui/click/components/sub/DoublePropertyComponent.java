package me.devteqhs.example.impl.ui.click.components.sub;

import me.devteqhs.example.api.property.sub.DoubleProperty;
import me.devteqhs.example.impl.ui.click.components.PropertyComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;

import java.awt.*;

/**
 * You can replace this code with sliders instead.
 * I prefer this as you can get specific values easier.
 */

public class DoublePropertyComponent extends PropertyComponent<DoubleProperty> {
    public boolean inputMode = false;
    private GuiTextField inputField;

    public DoublePropertyComponent(DoubleProperty property, int x, int y) {
        super(property, x, y, 90, 20);
        inputField = new GuiTextField(0, Minecraft.getMinecraft().fontRendererObj, x + 2, y + 2, width - 4, height - 4);
        inputField.setMaxStringLength(10);
        inputField.setCanLoseFocus(false);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Gui.drawRect(x, y, x + width, y + height, new Color(20, 20, 50).getRGB());
        if (!inputMode) {
            String displayText = property.getName() + ": " + String.format("%.2f", property.getValue());
            Minecraft.getMinecraft().fontRendererObj.drawString(displayText, x + 2, y + (height - 8) / 2, -1);
        } else {
            inputField.drawTextBox();
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            inputMode = true;
            inputField.setFocused(true);
        }
        if (inputMode && mouseButton == 0 && !inBounds(mouseX, mouseY)) {
            inputMode = false;
            inputField.setFocused(false);
        }
    }

    public void keyTyped(char typedChar, int keyCode) {
        if (inputMode) {
            inputField.textboxKeyTyped(typedChar, keyCode);
            if (keyCode == Keyboard.KEY_RETURN) {
                double value;
                try {
                    value = Double.parseDouble(inputField.getText());
                } catch (NumberFormatException e) {
                    value = property.getValue();
                }
                property.setValue(Math.max(property.getMin(), Math.min(property.getMax(), value)));
                inputMode = false;
                inputField.setFocused(false);
            }
        }
    }

    public void onMouseClickedElsewhere(int mouseX, int mouseY, int mouseButton) {
        if (inputMode && mouseButton == 0 && !inBounds(mouseX, mouseY)) {
            inputMode = false;
            inputField.setFocused(false);
        }
    }
}

