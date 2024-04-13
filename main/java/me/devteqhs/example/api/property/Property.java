package me.devteqhs.example.api.property;

public abstract class Property<T> {
    private String name;
    private T value;

    public Property(String name, T defaultValue) {
        this.name = name;
        this.value = defaultValue;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
