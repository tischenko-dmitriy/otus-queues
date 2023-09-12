package ru.otus.example.queues.runnables;

public class InsertingThread implements Runnable{
    @Override
    public void run() {
        int i = 0;
        do {
            synchronized (Commons.lock) {
                if (Commons.list.getSize() >= 1000) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.printf("Error %s - %s", e.getClass(), e.getMessage());
                    }
                }
                Commons.list.add(0);
                notify();
            }
            i++;
        } while (i < Commons.counter);
    }

}
