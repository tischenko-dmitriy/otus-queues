package ru.otus.example.queues.event;

import ru.otus.example.queues.event.listeners.Listener;
import ru.otus.example.queues.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventMaker {

    private final List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public void event(Event data) {
        listeners.forEach(listener -> {
            try {
                listener.onUpdate(data);
            } catch (RuntimeException e) {
                System.out.printf("Error %s with message [%s].\n", e.getClass(), e.getMessage());
            }
        });
    }

}
