package com.xiaojc.thread.lock;

/**
 * 用途：synchronized  响应中断锁【无法实现】
 * Created by xiaojc on 2016/6/20 16:57.
 */
public class Buffer {

    private Object lock;
    public Buffer(){
        lock = this;
    }

    public void writer(){
        synchronized (lock){
            long startTime = System.currentTimeMillis();
            System.out.println("开始往这个buff写入数据。。。");
            for (;;){
                if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE){
                   break;
                }
            }
            System.out.println("终于写完了。。。");
        }
    }


    public void reader(){
        synchronized (lock){
            System.out.println("开始读取buff数据");
        }
    }

    public static void main(String[] args){

        Buffer buffer = new Buffer();

        Writer writer = new Writer(buffer);
        Reader reader = new Reader(buffer);

        writer.start();
        reader.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
               for (;;){
                   if (System.currentTimeMillis() - startTime >= 5000){
                       System.out.println("不等了。。。");
                       reader.interrupted();
                       System.out.println("中断。。。");
                       break;
                   }
               }
            }
        }).start();
    }



    static class Writer extends Thread{

        private Buffer buffer;

        public Writer(Buffer buffer){
            this.buffer = buffer;
        }

        @Override
        public void run() {
            buffer.writer();
        }
    }


    static class Reader extends Thread{
        private Buffer buffer;

        public Reader(Buffer buffer){
            this.buffer = buffer;
        }

        @Override
        public void run() {
            buffer.reader();
        }
    }
}
