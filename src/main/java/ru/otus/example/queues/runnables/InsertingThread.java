package ru.otus.example.queues.runnables;

import org.apache.commons.lang3.RandomStringUtils;

public class InsertingThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (Commons.lock) {
                if (Commons.list.getSize() >= 100) {
                    try {
                        Commons.lock.wait();
                    } catch (InterruptedException e) {
                        System.out.printf("Error %s - %s", e.getClass(), e.getMessage());
                    }
                } else {
                    Commons.list.add(RandomStringUtils.random(32, true, false));
                    System.out.printf("Thread %s - Index: %d - Item inserted. New list size: %d\n", Thread.currentThread().getName(), i, Commons.list.getSize());
                    Commons.lock.notify();
                }
            }
        }
    }

}
