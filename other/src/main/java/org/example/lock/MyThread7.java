package org.example.lock;

public class MyThread7 {

    private int i = 0;
    public synchronized void operation(){
        while(true){
            try {
                i++;
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " , i = " + i);
                if(i == 20){
                    //Integer.parseInt("a");
                    throw new RuntimeException();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        final MyThread7 thread = new MyThread7();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread.operation();
            }
        },"t1");
        t1.start();
    }

}
