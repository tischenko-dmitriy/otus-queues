package ru.otus.example.queues.runnables;

public class DeletingThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (Commons.lock) {
                if (Commons.list.getSize() == 0) {
                    try {
                        Commons.lock.wait();
                    } catch (InterruptedException e) {
                        System.out.printf("Error %s - %s", e.getClass(), e.getMessage());
                    }
                } else {
                    Commons.list.remove(0);
                    System.out.printf("Thread %s - Index: %d - Item deleted. New list size: %d\n", Thread.currentThread().getName(), i, Commons.list.getSize());
                    Commons.lock.notify();
                }
            }
            i++;
        }
    }

}
