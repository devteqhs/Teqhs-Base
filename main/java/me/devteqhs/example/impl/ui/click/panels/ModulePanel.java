package me.devteqhs.example.impl.ui.click.panels;

import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.property.Property;
import me.devteqhs.example.impl.ui.click.components.PropertyComponent;
import me.devteqhs.example.impl.ui.click.components.PropertyComponentFactory;
import me.devteqhs.example.impl.ui.click.components.sub.DoublePropertyComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModulePanel {
    private final Module module;
    private final int x;
    private final int y;
    private final int width = 90;
    private final int height = 20;
    private boolean expanded = false;
    private final List<PropertyComponent<?>> propertyComponents = new ArrayList<>();

    public ModulePanel(Module module, int x, int y) {
        this.module = module;
        this.x = x;
        this.y = y;
        initializeProperties();
    }

    private void initializeProperties() {
        int propertyY = y + height;
        for (Property<?> prop : module.getProperties()) {
            PropertyComponent<?> widget = PropertyComponentFactory.createWidget(prop, x + width, propertyY - 20);
            propertyComponents.add(widget);
            propertyY += 20;
        }
    }

    public void draw(int mouseX, int mouseY) {
        Gui.drawRect(x, y, x + width, y + height, new Color(25, 25, 55).getRGB());
        String displayText = module.isListening() ? "Listening..." : module.getName();
        Minecraft.getMinecraft().fontRendererObj.drawString(displayText, x + 2, y + (height - 8) / 2, module.isToggled() ? new Color(79, 214, 255).getRGB() : -1);

        if (expanded) {
            propertyComponents.forEach(widget -> widget.draw(mouseX, mouseY));
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        boolean clickedProperty = false;

        if (expanded) {
            for (PropertyComponent<?> widget : propertyComponents) {
                if (widget.inBounds(mouseX, mouseY)) {
                    widget.mouseClicked(mouseX, mouseY, mouseButton);
                    clickedProperty = true;
                    break;
                }
            }

            if (!clickedProperty) {
                propertyComponents.stream()
                        .filter(widget -> widget instanceof DoublePropertyComponent)
                        .forEach(widget -> ((DoublePropertyComponent) widget).onMouseClickedElsewhere(mouseX, mouseY, mouseButton));
            }
        }

        if (inBounds(mouseX, mouseY) && !clickedProperty) {
            if (mouseButton == 0) {
                module.toggle();
            } else if (mouseButton == 1) {
                expanded = !expanded;
            } else if (mouseButton == 2) {
                module.setListening(!module.isListening());
            }
        }

    }

    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if (expanded) {
            propertyComponents.stream()
                    .filter(widget -> widget instanceof DoublePropertyComponent && ((DoublePropertyComponent) widget).inputMode)
                    .forEach(widget -> {
                        ((DoublePropertyComponent) widget).keyTyped(typedChar, keyCode);
                    });
        }
    }

    public int getExpandedWidth() {
        int expandedWidth = 180;
        return expanded ? expandedWidth : width;
    }

    private boolean inBounds(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public Module getModule() {
        return module;
    }
}
