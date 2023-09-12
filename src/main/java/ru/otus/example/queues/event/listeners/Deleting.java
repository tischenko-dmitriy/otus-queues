package ru.otus.example.queues.event.listeners;

import ru.otus.example.queues.model.Event;

public class Deleting implements Listener {
    @Override
    public void onUpdate(Event data) {
        if ("DEL".equals(data.getType())) {
            System.out.printf("Deleting: [%s].\n", data.getItem().toString());
        }
    }

}
