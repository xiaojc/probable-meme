package com.xiaojc.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用途：
 * Created by xiaojc on 2016/6/20 17:45.
 */
public class ReentrantLockDemo01 {

    public static void main(String[] args){

        Lock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();

    }
}
