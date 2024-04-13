package me.devteqhs.example.impl.ui.click.components;

import me.devteqhs.example.api.property.Property;
import me.devteqhs.example.api.property.sub.BooleanProperty;
import me.devteqhs.example.api.property.sub.DoubleProperty;
import me.devteqhs.example.api.property.sub.EnumProperty;
import me.devteqhs.example.api.property.sub.MultiSelectEnumProperty;
import me.devteqhs.example.impl.ui.click.components.sub.*;

public class PropertyComponentFactory {
    public static PropertyComponent<?> createWidget(Property<?> property, int x, int y) {
        if (property instanceof BooleanProperty) {
            return new BooleanPropertyComponent((BooleanProperty) property, x, y);
        } else if (property instanceof DoubleProperty) {
            return new DoublePropertyComponent((DoubleProperty) property, x, y);
        } else if (property instanceof EnumProperty) {
            return new EnumPropertyComponent((EnumProperty<?>) property, x, y);
        } else if (property instanceof MultiSelectEnumProperty) {
            return new MultiSelectEnumPropertyComponent<>((MultiSelectEnumProperty<?>) property, x, y );
        } else {
            throw new IllegalArgumentException("Unsupported property type: " + property.getClass());
        }
    }
}
