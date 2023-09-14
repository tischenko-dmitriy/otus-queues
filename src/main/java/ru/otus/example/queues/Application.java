package ru.otus.example.queues;

import ru.otus.example.queues.event.EventPublisher;
import ru.otus.example.queues.event.EventType;
import ru.otus.example.queues.event.listeners.Deleting;
import ru.otus.example.queues.event.listeners.Inserting;
import ru.otus.example.queues.runnables.Commons;
import ru.otus.example.queues.runnables.DeletingThread;
import ru.otus.example.queues.runnables.InsertingThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        EventPublisher publisher = new EventPublisher();
        publisher.start();
        Commons.list.setPublisher(publisher);

        publisher.subscribe(EventType.INSERT, new Inserting());
        publisher.subscribe(EventType.DELETE, new Deleting());

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.submit(new InsertingThread());
        threadPool.submit(new DeletingThread());

        for (int i = 0; i < 2; i++) {
            threadPool.shutdown();
        }

        Thread.sleep(1000);

        publisher.stop();

        Thread.sleep(1000);

        System.out.println("Done");

    }


}
