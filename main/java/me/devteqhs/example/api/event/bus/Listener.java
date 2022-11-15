package me.devteqhs.example.api.event.bus;

@FunctionalInterface
public interface Listener<Event> {
    void call(Event event);
}