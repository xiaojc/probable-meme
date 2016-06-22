package com.xiaojc.thread.thread;

/**
 * 用途： 使用interrupt（）中断线程
 * Created by xiaojc on 2016/6/21 14:45.
 */
public class SleepInterruptedDemo extends Thread{

    private String name;
    public SleepInterruptedDemo(String name){
        this.name = name;
    }
    public void run() {

        try {
            System.out.println("线程"+name+"开始执行线程睡眠-10s");
            Thread.sleep(10000);
            System.out.println("线程"+name+"结束线程睡眠");
        } catch (InterruptedException e) {
            System.out.println("线程中断异常");
//            return;
        }
        System.out.println("线程"+name+"======>执行结束");

    }

    public static void main(String[] args) throws InterruptedException {

        SleepInterruptedDemo sl = new SleepInterruptedDemo("A");
        sl.start();

        Thread.sleep(2000);
        sl.interrupt();


    }
}
