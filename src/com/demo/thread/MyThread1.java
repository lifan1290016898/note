package com.demo.thread;

public class MyThread1 extends Thread {

    private int count = 0;


    /**
     * 此处加上synchronized
     */
    @Override
    public synchronized void run() {
        count++;
        System.out.println(this.currentThread().getName() + "修改了count, count为:" + count);
    }

    public static void main(String[] args) {

        /**
         * 现在有5个线程需要执行run方法中的业务逻辑，这5个线程会以排队的形式依次访问run方法，这里的排队是看CPU的分配
         * 想要执行run方法就需要拿到synchronized这把锁，如果拿到就执行方法，如果拿不到，会一直尝试拿锁
         */

        MyThread1 myThread = new MyThread1();
        Thread t1 = new Thread(myThread,"t1");
        Thread t2 = new Thread(myThread,"t2");
        Thread t3 = new Thread(myThread,"t3");
        Thread t4 = new Thread(myThread,"t4");
        Thread t5 = new Thread(myThread,"t5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }


}
