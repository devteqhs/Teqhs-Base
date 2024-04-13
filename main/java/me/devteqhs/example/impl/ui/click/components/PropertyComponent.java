package me.devteqhs.example.impl.ui.click.components;

import me.devteqhs.example.api.property.Property;

public abstract class PropertyComponent<T extends Property<?>> {
    public final T property;
    protected final int x, y, width, height;

    public PropertyComponent(T property, int x, int y, int width, int height) {
        this.property = property;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(int mouseX, int mouseY);

    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);

    public boolean inBounds(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public int getHeight() {
        return height;
    }

    public T getProperty() {
        return property;
    }
}

