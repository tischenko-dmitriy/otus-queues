package ru.otus.example.queues;

import ru.otus.example.queues.event.listeners.Deleting;
import ru.otus.example.queues.event.EventMaker;
import ru.otus.example.queues.event.listeners.Inserting;
import ru.otus.example.queues.runnables.DeletingThread;
import ru.otus.example.queues.runnables.InsertingThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) {
        EventMaker eventMaker = new EventMaker();
        Inserting inserting = new Inserting();
        Deleting deleting = new Deleting();

        eventMaker.addListener(inserting);
        eventMaker.addListener(deleting);

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.submit(new InsertingThread());
        threadPool.submit(new DeletingThread());

        threadPool.shutdown();

        eventMaker.removeListener(inserting);
        eventMaker.removeListener(deleting);
    }

}
