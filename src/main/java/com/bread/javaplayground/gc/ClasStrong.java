package com.bread.javaplayground.gc;

public class ClasStrong {
    public static class Referred {

        public static String test = "hi";

        protected void finalize() {
            System.out.println("Good bye cruel world");
        }
    }

    public static void collect() throws InterruptedException {
        System.out.println("Suggesting collection");
        System.gc();
        System.out.println("Sleeping");
        Thread.sleep(5000);
    }

    public static void main(String args[]) throws InterruptedException {
        System.out.println("Creating strong references");

        // This is now a strong reference.
        // The object will only be collected if all references to it disappear.
        Referred strong = new Referred();

        // Attempt to claim a suggested reference.
        ClasStrong.collect();

        System.out.println("Removing reference");
        // The object may now be collected.
        strong = null;

        Thread thread = new Thread(() -> {
            System.out.println("hi");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("hi");
        });
        Thread thread3 = new Thread(() -> {
            System.out.println("hi");
        });

        thread.start();
        thread2.start();
        thread3.start();
        Thread.sleep(30000);


        System.out.println("Done");
    }
}
