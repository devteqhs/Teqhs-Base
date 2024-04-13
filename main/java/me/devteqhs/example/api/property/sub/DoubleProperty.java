package me.devteqhs.example.api.property.sub;

import me.devteqhs.example.api.property.Property;

public class DoubleProperty extends Property<Double> {
    private double min, max, increment;

    public DoubleProperty(String name, double defaultValue, double min, double max, double increment) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public void incrementValue() {
        this.setValue(Math.min(this.getValue() + increment, max));
    }

    public void decrementValue() {
        this.setValue(Math.max(this.getValue() - increment, min));
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }
}
