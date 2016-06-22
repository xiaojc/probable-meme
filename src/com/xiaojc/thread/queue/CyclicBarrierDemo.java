package com.xiaojc.thread.queue;

import java.util.concurrent.*;

/**
 * 用途：循环障碍器 demo
 * Created by xiaojc on 2016/6/21 10:55.
 */
public class CyclicBarrierDemo {

    public static void main(String[] args){

//        ExecutorService executorService = Executors.newFixedThreadPool(4);

//        ExecutorService executorService = new ThreadPoolExecutor(3, 10,  1L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        ExecutorService executorService = Executors.newCachedThreadPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new MainTask());

        for (int i = 0; i < 5; i++){
            executorService.execute(new MyTask("A"+i, cyclicBarrier));
        }
        /*executorService.execute(new MyTask("A", cyclicBarrier));
        executorService.execute(new MyTask("B", cyclicBarrier));
        executorService.execute(new MyTask("C", cyclicBarrier));
        executorService.execute(new MyTask("D", cyclicBarrier));
        executorService.execute(new MyTask("E", cyclicBarrier));*/

        executorService.shutdown();
    }

}


class MainTask implements Runnable{

    @Override
    public void run() {
        System.out.println("等了一万年，终于到大爷我了");
    }
}

class MyTask implements Runnable{

    private String threadName;
    private CyclicBarrier cyclicBarrier;

    public MyTask(String threadName, CyclicBarrier cyclicBarrier){
        this.threadName = threadName;
        this.cyclicBarrier = cyclicBarrier;
    }
    @Override
    public void run() {
        System.out.println("开始执行线程："+threadName);
        for (int i = 0; i < 9; i++);
        System.out.println("线程："+threadName + "运行完毕,通知障碍器。");

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
