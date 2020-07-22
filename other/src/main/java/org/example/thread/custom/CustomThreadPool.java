package org.example.thread.custom;

import java.util.concurrent.*;

public class CustomThreadPool {

    public static void main(String[] args) {
        /**
         * 在使用有界队列时，若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，则优先创建线程，
         * 若大于corePoolSize，则会将任务加入队列，
         * 若队列已满，则在总线程数不大于maximumPoolSize的前提下，创建新的线程，
         * 若线程数大于maximumPoolSize，则执行拒绝策略。或其他自定义方式。
         */
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1, 				    //coreSize
                2, 				//MaxSize
                60, 			    //60
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3)			//指定一种队列 （有界队列）
                //new LinkedBlockingQueue<Runnable>()
                ,new MyRejected()
                //, new DiscardOldestPolicy()
        );

        /**
         * 这里跟使用有界队列不同的是不会使用到max这个参数，而是一直会添加到无界队列中
         */
        ThreadPoolExecutor executor  = new ThreadPoolExecutor(
                5, 		    //  core
                10, 	//  max
                120L, 	    //  时间为120
                TimeUnit.SECONDS,       //  单位（秒）
                new LinkedBlockingQueue<Runnable>()); // 无界队列
    }

}
