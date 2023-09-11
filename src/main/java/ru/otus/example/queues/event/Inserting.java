package ru.otus.example.queues.event;

import ru.otus.example.queues.model.EventData;

public class Inserting implements Listener {

    @Override
    public void onUpdate(EventData data) {
        if ("INS".equals(data.getType())) {
            System.out.printf("Inserting: [%s].\n", data.getItem().toString());
        }
    }

}
