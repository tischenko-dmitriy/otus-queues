package ru.otus.example.queues.model;

import ru.otus.example.queues.event.EventPublisher;
import ru.otus.example.queues.event.EventType;

import java.util.ArrayList;
import java.util.List;

public class EventedList {

    private List<Object> list = new ArrayList<>();

    private EventPublisher publisher;

    public int getSize() {
        return list.size();
    }

    public void add(Object object) {
        list.add(object);
        publishEvent(EventType.INSERT, object);
    }

    public void remove(int i) {
        Object removed = list.remove(i);
        publishEvent(EventType.DELETE, removed);
    }

    public void setPublisher(EventPublisher publisher) {
        this.publisher = publisher;
    }

    private void publishEvent(EventType type, Object item) {
        if (publisher == null) {
            return;
        }

        publisher.publish(new Event(type, item));
    }

}
