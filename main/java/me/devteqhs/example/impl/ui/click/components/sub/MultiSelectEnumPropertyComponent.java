package me.devteqhs.example.impl.ui.click.components.sub;

import me.devteqhs.example.api.property.sub.*;
import me.devteqhs.example.impl.ui.click.components.PropertyComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class MultiSelectEnumPropertyComponent<E extends Enum<E>> extends PropertyComponent<MultiSelectEnumProperty<E>> {

    public MultiSelectEnumPropertyComponent(MultiSelectEnumProperty<E> property, int x, int y) {
        super(property, x, y, 90, 20 * (property.getValues().length + 1));
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Gui.drawRect(x, y, x + width, y + height, new Color(20, 20, 50).getRGB());
        int currentY = y + 5;
        Minecraft.getMinecraft().fontRendererObj.drawString(property.getName() + ":", x + 2, currentY, -1);
        currentY += 20;

        for (E value : property.getValues()) {
            boolean isSelected = property.getValue().contains(value);
            String displayText = value.toString();
            int color = isSelected ? new Color(79, 214, 255).getRGB() : -1;
            Minecraft.getMinecraft().fontRendererObj.drawString(displayText, x + 2, currentY, color);
            currentY += 20;
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            int index = (mouseY - y - 20) / 20;
            if (index >= 0 && index < property.getValues().length) {
                E value = property.getValues()[index];
                property.toggleValue(value);
            }
        }
    }
}

