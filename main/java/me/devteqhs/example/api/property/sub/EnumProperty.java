package me.devteqhs.example.api.property.sub;

import me.devteqhs.example.api.property.Property;

public class EnumProperty<E extends Enum<E>> extends Property<E> {
    private E[] values;

    public EnumProperty(String name, E defaultValue) {
        super(name, defaultValue);
        values = defaultValue.getDeclaringClass().getEnumConstants();
    }

    public E[] getValues() {
        return values;
    }

    public void increment() {
        int currentIndex = this.getValue().ordinal();
        int nextIndex = (currentIndex + 1) % values.length;
        this.setValue(values[nextIndex]);
    }
}
