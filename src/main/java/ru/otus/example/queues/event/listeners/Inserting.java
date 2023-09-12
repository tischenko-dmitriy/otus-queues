package ru.otus.example.queues.event.listeners;

import ru.otus.example.queues.model.Event;

public class Inserting implements Listener {

    @Override
    public void onUpdate(Event data) {
        if ("INS".equals(data.getType())) {
            System.out.printf("Inserting: [%s].\n", data.getItem().toString());
        }
    }

}
