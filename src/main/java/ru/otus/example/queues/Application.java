package ru.otus.example.queues;

import ru.otus.example.queues.event.Deleting;
import ru.otus.example.queues.event.EventMaker;
import ru.otus.example.queues.event.Inserting;
import ru.otus.example.queues.model.EventData;

public class Application {

    public static void main(String[] args) {
        EventMaker eventMaker = new EventMaker();
        Inserting inserting = new Inserting();
        Deleting deleting = new Deleting();

        eventMaker.addListener(inserting);
        eventMaker.addListener(deleting);

        eventMaker.event(new EventData("INS", "Some event!"));
        eventMaker.event(new EventData("DEL", "Second event"));

        eventMaker.removeListener(inserting);
        eventMaker.removeListener(deleting);
    }

}
