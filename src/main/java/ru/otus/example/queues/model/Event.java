package ru.otus.example.queues.model;

import ru.otus.example.queues.event.EventType;

public class Event {

    private final EventType type;

    private final Object item;

    public Event(EventType type, Object item) {
        this.type = type;
        this.item = item;
    }

    public EventType getType() {
        return type;
    }

    public Object getItem() {
        return item;
    }

}
