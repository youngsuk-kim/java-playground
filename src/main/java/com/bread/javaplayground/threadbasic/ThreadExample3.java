package com.bread.javaplayground.threadbasic;

public class ThreadExample3 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("Runnable running"));
        thread.start();
    }
}
