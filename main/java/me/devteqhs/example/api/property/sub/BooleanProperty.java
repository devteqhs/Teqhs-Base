package me.devteqhs.example.api.property.sub;

import me.devteqhs.example.api.property.Property;

public class BooleanProperty extends Property<Boolean> {
    public BooleanProperty(String name, boolean defaultValue) {
        super(name, defaultValue);
    }

    public void toggle() {
        this.setValue(!this.getValue());
    }
}
