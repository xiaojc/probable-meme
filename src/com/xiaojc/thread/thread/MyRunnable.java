package com.xiaojc.thread.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用途：
 * Created by xiaojc on 2016/6/21 11:39.
 */
public class MyRunnable implements Runnable {


    private Object lock;

    public MyRunnable(){
        this.lock = this;

    }
    volatile int tiket = 5;

    @Override
    public void run() {
        synchronized (lock){
            for (int i = 0; i < 10; i++) {
                if (tiket > 0) {
                    System.out.println(Thread.currentThread().getName() + "====>火车票：" + tiket--);
                }
            }
        }
    }


    public static void main(String[] args) {

        MyRunnable my1 = new MyRunnable();
        new Thread(my1).start();
        new Thread(my1).start();
        new Thread(my1).start();

    }
}
