package ru.otus.example.queues.event.listeners;

import ru.otus.example.queues.model.Event;

public interface Listener {
    void onUpdate(Event data);

}
