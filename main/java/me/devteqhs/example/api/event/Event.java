package me.devteqhs.example.api.event;

/**
 * Class all events extend from
 * Also stores all event info
 *
 * @author teqhs
 * @since 15/11/2022
 */

public class Event {

    private boolean cancelled;
    private EventState eventState;

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public EventState getEventState() {
        return eventState;
    }

    public void setEventState(EventState eventState) {
        this.eventState = eventState;
    }

    public void cancel() {
        cancelled = true;
    }


}
