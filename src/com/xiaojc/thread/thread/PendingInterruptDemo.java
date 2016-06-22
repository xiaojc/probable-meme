package com.xiaojc.thread.thread;

/**
 * 用途：待决中断demo
 * Created by xiaojc on 2016/6/21 15:00.
 */
public class PendingInterruptDemo {

    public static void main(String[] args){

        if (args.length > 0){
            System.out.println("准备中断程序主线程："+args[0]);
            Thread.currentThread().interrupt();
            System.out.println("已经中断程序主线程");
        }

        long startTime = System.currentTimeMillis();
        try {
           if (!Thread.currentThread().isInterrupted()){
               System.out.println(Thread.currentThread().isInterrupted());
               System.out.println("主线程准备让程序主线程休眠2s");
               Thread.sleep(2000);
           }else{
               System.out.println("==========");
           }
        } catch (InterruptedException e) {
            System.out.println("主线程准备让程序主线程休眠2s，报异常");
//            return;
        }

        System.out.println("主线程执行完毕,执行时间差："+ (System.currentTimeMillis() - startTime));
    }
}
