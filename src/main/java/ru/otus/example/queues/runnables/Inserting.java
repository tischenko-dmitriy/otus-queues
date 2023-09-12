package ru.otus.example.queues.runnables;

public class Inserting implements Runnable{
    @Override
    public void run() {
        synchronized (Commons.lock) {
            Commons.list.add(0);
            notify();
        }
    }

}
