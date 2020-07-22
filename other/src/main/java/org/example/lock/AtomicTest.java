package org.example.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    private static AtomicInteger count = new AtomicInteger(0);

    /**
     * 加上synchronized保证原子性
     * @return
     */
    public synchronized int multiAdd(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.addAndGet(1);
        count.addAndGet(2);
        count.addAndGet(3);
        count.addAndGet(4); //+10
        return count.get();
    }


    public static void main(String[] args) {

        final AtomicTest at = new AtomicTest();

        List<Thread> ts = new ArrayList<Thread>();
        for (int i = 0; i < 100; i++) {
            ts.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(at.multiAdd());
                }
            }));
        }

        for(Thread t : ts){
            t.start();
        }

    }
}
