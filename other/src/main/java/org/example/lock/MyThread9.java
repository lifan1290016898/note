package org.example.lock;

public class MyThread9 extends Thread{
    // volatile
    private volatile boolean isRunning = true;
    private void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    public void run(){
        System.out.println("进入run方法..");
        int i = 0;
        while(isRunning == true){
            //..
        }
        System.out.println("线程停止");
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread9 thread = new MyThread9();
        thread.start();
        Thread.sleep(1000);
        thread.setRunning(false);
        System.out.println("isRunning的值已经被设置了false");
    }

}
