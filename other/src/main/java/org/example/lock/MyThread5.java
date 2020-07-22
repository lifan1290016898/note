package org.example.lock;

/**
 * 锁重入
 */
public class MyThread5 {
    public synchronized void method1(){
        System.out.println("method1..");
        method2();
    }
    public synchronized void method2(){
        System.out.println("method2..");
        method3();
    }
    public synchronized void method3(){
        System.out.println("method3..");
    }

    public static void main(String[] args) {
        final MyThread5 thread = new MyThread5();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread.method1();
            }
        });
        t1.start();
    }

}
