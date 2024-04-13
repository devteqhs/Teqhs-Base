package me.devteqhs.example.api.property.sub;

import me.devteqhs.example.api.property.Property;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MultiSelectEnumProperty<E extends Enum<E>> extends Property<Set<E>> {
    private E[] values;

    public MultiSelectEnumProperty(String name, E[] defaultValues) {
        super(name, new HashSet<>(Arrays.asList(defaultValues)));
        values = defaultValues[0].getDeclaringClass().getEnumConstants();
    }

    public void toggleValue(E value) {
        if (this.getValue().contains(value)) {
            this.getValue().remove(value);
        } else {
            this.getValue().add(value);
        }
    }

    public E[] getValues() {
        return values;
    }

    public boolean isSelected(E value) {
        return this.getValue().contains(value);
    }

}
