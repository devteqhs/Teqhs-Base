package me.devteqhs.example.impl.ui.click.components.sub;

import me.devteqhs.example.api.property.sub.EnumProperty;
import me.devteqhs.example.impl.ui.click.components.PropertyComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class EnumPropertyComponent<E extends Enum<E>> extends PropertyComponent<EnumProperty<E>> {

    public EnumPropertyComponent(EnumProperty<E> property, int x, int y) {
        super(property, x, y, 90, 20);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Gui.drawRect(x, y, x + width, y + height, new Color(20, 20, 50).getRGB());
        String displayText = property.getName() + ": " + property.getValue().name();
        Minecraft.getMinecraft().fontRendererObj.drawString(displayText, x + 2, y + (height - 8) / 2, -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            property.increment();
        }
    }
}

