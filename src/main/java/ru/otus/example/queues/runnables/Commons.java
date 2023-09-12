package ru.otus.example.queues.runnables;

import ru.otus.example.queues.model.EventedList;

public class Commons {

    public static final Object lock = new Object();

    public static final EventedList list = new EventedList();

}
