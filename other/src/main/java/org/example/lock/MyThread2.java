package org.example.lock;


public class MyThread2 {

    static int count = 0;

    public synchronized static void printNum(String tag) {
        if ("a".equals(tag)) {
            count = 100;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            count = 200;
        }
        System.out.println(Thread.currentThread().getName() + "---设置完毕，count = " + count);
    }

    public static void main(String[] args) {
        final MyThread2 thread1 = new MyThread2();
        final MyThread2 thread2 = new MyThread2();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread1.printNum("a");
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread2.printNum("b");
            }
        }, "t2");
        t1.start();
        t2.start();
    }

}
