package com.xiaojc.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用途：rentrantLock实现乐观锁 ==》响应中断锁
 * Created by xiaojc on 2016/6/20 17:19.
 */
public class RenntrantBuffer {

    Lock lock = new ReentrantLock();

    public void Writer(){
        lock.lock();
        long startTime = System.currentTimeMillis();
        System.out.println("开始往这个buff写入数据。。。");
        try {
            for (;;){
                if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE){
                    break;
                }
            }
        }finally {
            lock.unlock();
        }
        System.out.println("终于写完了。。。");
    }

    public void Reader() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            System.out.println("开始读取buff数据");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        RenntrantBuffer buffer = new RenntrantBuffer();

        Writer2 writer2 = new Writer2(buffer);
        Reader2 reader2 = new Reader2(buffer);

        writer2.start();

        Thread.sleep(1000);
        reader2.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                long starTime = System.currentTimeMillis();
                for (;;){
                    if (System.currentTimeMillis() - starTime >= 5000){
                        System.out.println("不等了，尝试中断");
                        reader2.interrupt();  //此处中断读操作
                        break;
                    }
                }
            }
        }).start();
    }
}

 class Writer2 extends Thread{

     private RenntrantBuffer buffer;

     public Writer2(RenntrantBuffer buffer){
         this.buffer = buffer;
     }

     @Override
     public void run() {
         buffer.Writer();
     }
 }


class Reader2 extends Thread{
    private RenntrantBuffer buffer;

    public Reader2(RenntrantBuffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            buffer.Reader();
        } catch (InterruptedException e) {
            System.out.println("我擦，要等这么长时间，我不等了");
        }
        System.out.println("读结束");
    }
}
