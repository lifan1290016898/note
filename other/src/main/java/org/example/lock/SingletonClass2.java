package org.example.lock;

/**
 * 在类加载阶段，如果是静态的JVM会加锁，这个不是由我们加的
 */
public class SingletonClass2 {

    private static class InnerSingletion {
        private static SingletonClass2 single = new SingletonClass2();
    }

    public static SingletonClass2 getInstance(){
        return InnerSingletion.single;
    }
}
