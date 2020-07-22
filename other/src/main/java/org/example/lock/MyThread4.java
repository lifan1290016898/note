package org.example.lock;

/**
 * 脏读
 */
public class MyThread4 {

    private String username = "zhangsan";
    private String password = "123";

    public synchronized void setValue(String username, String password){
        this.username = username;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.password = password;

        System.out.println("setValue最终结果：username = " + username + " , password = " + password);
    }

    /**
     * synchronized
     */
    public synchronized void getValue(){
        System.out.println("getValue方法得到：username = " + this.username + " , password = " + this.password);
    }


    public static void main(String[] args) throws Exception{

        final MyThread4 thread = new MyThread4();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread.setValue("z3", "456");
            }
        });
        // 子线程休眠2秒
        t1.start();
        // 主线程休眠1秒
        Thread.sleep(1000);
        // 不加synchronized就会出现脏读
        thread.getValue();
    }

}
