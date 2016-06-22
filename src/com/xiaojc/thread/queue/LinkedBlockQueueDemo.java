package com.xiaojc.thread.queue;

import com.sun.deploy.util.StringUtils;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用途： blockingQueue 生产者消费者例子
 * Created by xiaojc on 2016/6/21 9:58.
 */
public class LinkedBlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        //创建一个queue
        BlockingQueue<String> queue = new LinkedBlockingDeque<String>(10);
        ExecutorService executors = Executors.newFixedThreadPool(10);

        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        executors.execute(producer1);
        executors.execute(producer2);
        executors.execute(producer3);
        executors.execute(consumer);


        Thread.sleep(10000);
        producer1.stopProduct();
        producer2.stopProduct();
        producer3.stopProduct();

        Thread.sleep(20000);
        executors.shutdown();


    }
}

/**
 * 生产者
 */
class Producer extends Thread {

    private BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    AtomicInteger count = new AtomicInteger();
    private boolean isRunning = true;


    @Override
    public void run() {
        Random random = new Random();
        System.out.println(Thread.currentThread().getName() + "【启动生产者线程】");

        try {
            while (isRunning) {
                System.out.println(Thread.currentThread().getName() + "生产者正在生产数据");
                Thread.sleep(random.nextInt(1000));
                String data = "data:" + count.decrementAndGet();
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println(Thread.currentThread().getName() + "生产数据失败" + data);
                } else {

                    System.out.println(Thread.currentThread().getName() + "生产数据成功" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + "【生产者退出线程】");
        }
    }

    public void stopProduct() {
        isRunning = false;
    }
}

/**
 * 消费者
 */
class Consumer extends Thread {
    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    boolean isRunning = true;

    @Override
    public void run() {
        System.out.println("-->" + Thread.currentThread().getName() + "【启动消费者】");
        Random random = new Random();

        try {
            while (isRunning) {
                String data = queue.poll(2, TimeUnit.SECONDS);
                if (!"".equals(data) && null != data) {
                    System.out.println("-->" + Thread.currentThread().getName() + "正在消费数据" + data);
                    Thread.sleep(random.nextInt(1000));
                } else {
                    //如果2秒还没拿到数据就算生产者退出了
                    System.out.println("-->" + Thread.currentThread().getName() + "正在消费数据，没有数据可以取");
                    isRunning = false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("-->" + Thread.currentThread().getName() + "退出【消费者】线程");
        }
    }
}

