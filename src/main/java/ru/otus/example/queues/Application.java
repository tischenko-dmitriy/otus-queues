package ru.otus.example.queues;

import ru.otus.example.queues.event.EventPublisher;
import ru.otus.example.queues.event.EventType;
import ru.otus.example.queues.event.listeners.Deleting;
import ru.otus.example.queues.event.listeners.Inserting;
import ru.otus.example.queues.runnables.DeletingThread;
import ru.otus.example.queues.runnables.InsertingThread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        EventPublisher publisher = new EventPublisher();
        publisher.start();
        Commons.list.setPublisher(publisher);

        publisher.subscribe(EventType.INSERT, new Inserting());
        publisher.subscribe(EventType.DELETE, new Deleting());

        try {
            Files.createFile(Paths.get(Commons.INSERT_FILE_NAME));
            Files.createFile(Paths.get(Commons.DELETE_FILE_NAME));
        } catch (IOException e) {
            System.out.println("Can't create log files: " + e.getMessage());
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.submit(new InsertingThread());
        threadPool.submit(new DeletingThread());

        for (int i = 0; i < 2; i++) {
            threadPool.shutdown();
        }

        Thread.sleep(1000);

        System.out.println("Done");

    }


}
