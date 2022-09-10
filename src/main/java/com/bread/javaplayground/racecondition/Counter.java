package com.bread.javaplayground.racecondition;

public class Counter {

    private long count = 0;

    public long incAndGet() {
        this.count++; // 1. 읽기 2. 쓰기, 두가지 작업이 포함되어 있다
        return this.count;
    }

    public long get() {
        return count;
    }
}
