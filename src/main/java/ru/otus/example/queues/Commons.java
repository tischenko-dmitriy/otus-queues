package ru.otus.example.queues;

import ru.otus.example.queues.model.EventedList;

public class Commons {

    public static final Object lock = new Object();

    public static final EventedList list = new EventedList();

    public static final int counter = 10000;

    public static final String INSERT_FILE_NAME = "inserting.log";

    public static final String DELETE_FILE_NAME = "deleting.log";

    public static void sleep(int millis) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
