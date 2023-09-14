package ru.otus.example.queues.event;

import ru.otus.example.queues.event.listeners.Listener;
import ru.otus.example.queues.model.Event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class EventPublisher {

    private final Map<EventType, Listener> subscribers = new ConcurrentHashMap<>();

    private final LinkedBlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();

    private boolean isActive = true;

    private final Thread logThread = new Thread(new Runnable() {
        @Override
        public void run() {
            int k = 0;
            do {
                try {
                    notifySubscribers(eventQueue.take());
                } catch (InterruptedException e) {
                    System.out.println("Log thread has been interrupted.");;
                }
                k++;
            } while (isActive);
        }
    });

    public void publish(Event event) {
        eventQueue.add(event);
    }

    public void notifySubscribers(Event event) {
        if (subscribers.containsKey(event.getType())) {
            subscribers.get(event.getType()).onUpdate(event);
        }
    }

    public void start() {
        logThread.start();

    }

    public void stop() {
        isActive = false;
        try {
            logThread.join();
        } catch (InterruptedException e) {
            System.out.println("Stopping thread... Log thread has been interrupted.");;
        }
    }

    public void subscribe(EventType type, Listener listener) {
        subscribers.computeIfAbsent(type, key -> listener);
    }

}
