package org.example.lock;

public class ThreadLocalTest {
    /**
     * 为每一个线程生成副本
     */
    public static ThreadLocal<String> th = new ThreadLocal<String>();

    public void setTh(String value){
        th.set(value);
    }
    public void getTh(){
        System.out.println(Thread.currentThread().getName() + ":" + this.th.get());
    }

    public static void main(String[] args) throws InterruptedException {

        final ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalTest.setTh("张三");
                threadLocalTest.getTh();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    threadLocalTest.getTh();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}
