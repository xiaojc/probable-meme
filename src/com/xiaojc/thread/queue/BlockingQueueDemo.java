package com.xiaojc.thread.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用途：
 * Created by xiaojc on 2016/6/20 18:32.
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue blockingQueue = new ArrayBlockingQueue(20, true);
        for(int i = 0 ; i < 30; i++){
            blockingQueue.put(i);
//            blockingQueue.offer(i);
            System.out.println("往阻塞队列中加入了下标为："+ i);
            if (i > 18){
                System.out.println("往阻塞队列中移除下标为："+ blockingQueue.poll());
//                Thread.sleep(10000);
            }
        }


        System.out.println("阻塞队列执行完毕"+Integer.MIN_VALUE);
    }
}
