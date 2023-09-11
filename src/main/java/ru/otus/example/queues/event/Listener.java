package ru.otus.example.queues.event;

import ru.otus.example.queues.model.EventData;

public interface Listener {
    void onUpdate(EventData data);

}
