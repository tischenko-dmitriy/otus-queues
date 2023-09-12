package ru.otus.example.queues.runnables;

public class Deleting implements Runnable {

    @Override
    public void run() {
        synchronized (Commons.lock) {
            if (Commons.list.getSize() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.printf("Error %s - %s", e.getClass(), e.getMessage());
                }
            } else {
                Commons.list.remove(0);
                notify();
            }
        }
    }

}
