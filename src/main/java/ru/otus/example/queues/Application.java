package ru.otus.example.queues;

import ru.otus.example.queues.event.EventType;
import ru.otus.example.queues.event.listeners.Deleting;
import ru.otus.example.queues.event.EventMaker;
import ru.otus.example.queues.event.listeners.Inserting;
import ru.otus.example.queues.model.Event;

public class Application {

    public static void main(String[] args) {
        EventMaker eventMaker = new EventMaker();
        Inserting inserting = new Inserting();
        Deleting deleting = new Deleting();

        eventMaker.addListener(inserting);
        eventMaker.addListener(deleting);

        eventMaker.event(new Event(EventType.INSERT, "Some event!"));
        eventMaker.event(new Event(EventType.DELETE, "Second event"));

        eventMaker.removeListener(inserting);
        eventMaker.removeListener(deleting);
    }

}
