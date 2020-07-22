package org.example.lock;

public class MyThread1 implements Runnable{

    int count = 5;

    /**
     * synchronized
     */
    @Override
    public synchronized void run() {
        this.count--;
        System.out.println(Thread.currentThread().getName() + "--count:" + this.count);
    }

    public static void main(String[] args) {
        MyThread1 thread = new MyThread1();
        Thread t1 = new Thread(thread, "t1");
        Thread t2 = new Thread(thread, "t2");
        Thread t3 = new Thread(thread, "t3");
        Thread t4 = new Thread(thread, "t4");
        Thread t5 = new Thread(thread, "t5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }

}
