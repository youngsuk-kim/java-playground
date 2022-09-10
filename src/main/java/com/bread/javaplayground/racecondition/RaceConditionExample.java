package com.bread.javaplayground.racecondition;

public class RaceConditionExample {
    private static Runnable getRunnable(Counter counter, String message) {
        return () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.incAndGet();
            }
            System.out.println(message + counter.get());
        };
    }

    private static Runnable getReadingRunnable(Counter counter) {
        return () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2 count: " + counter.get());
            }
        };
    }

    public static void main(String[] args) {
//        CounterSynchronized counterSynchronized = new CounterSynchronized();
        Counter counter = new Counter();

        Thread thread1 = new Thread(getRunnable(counter, "Thread1 final count: "));
//        Thread thread2 = new Thread(getRunnable(counterSynchronized, "Thread2 final count: "));
        Thread thread2 = new Thread(getReadingRunnable(counter));

        thread1.start();
        thread2.start();
    }
}
