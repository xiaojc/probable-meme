package com.xiaojc.thread.thread;

/**
 * 用途： 卖火车票线程
 * Created by xiaojc on 2016/6/21 11:35.
 */
public class MyThread extends Thread {

    private int tiket = 5;
    @Override
    public void run() {

        for(int i = 0; i < 10; i++){
            if (tiket> 0){
                System.out.println(Thread.currentThread().getName()+"====>火车票："+tiket --);
            }
        }
    }

    public static void main(String[] args){

        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();

        myThread1.start();
        myThread2.start();
        myThread3.start();
    }
}
