package org.example.lock;

public class SingletonClass1 {
    /**
     * volatile 禁止CPU缓存，禁止使用指令重排序
     * 创建对象分为以下几步
     * 1、new关键字触发对象的类加载（如果已经做过了就不会去做这一步）
     * 2、根据Class开辟内存空间
     * 3、初始化对象，完成成员变量的初始化操作
     * 4、将开辟出来的内存空间地址，赋值给栈空间的变量
     * 第4步不会依赖第3步
     *
     * 禁止CPU缓存指在CPU多核的情况下，每次是从内存中获取一个变量然后操作，这里表示禁止这个内存中获取
     */
    private volatile static SingletonClass1 ds;

    public  static SingletonClass1 getDs(){
        if(ds == null){
            synchronized (SingletonClass1.class) {
                if(ds == null){
                    ds = new SingletonClass1();
                }
            }
        }
        return ds;
    }


}
