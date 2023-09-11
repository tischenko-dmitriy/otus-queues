package ru.otus.example.queues.event;

import ru.otus.example.queues.model.EventData;

public class Deleting implements Listener {
    @Override
    public void onUpdate(EventData data) {
        if ("DEL".equals(data.getType())) {
            System.out.printf("Deleting: [%s].\n", data.getItem().toString());
        }
    }

}
