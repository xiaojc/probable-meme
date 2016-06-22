package com.xiaojc.thread.executers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final String TIME_FORMAT = "yyyyMMddhhmmss";
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        //当前时间
        Date date = new Date();
        String currentDate = sdf.format(date);
        System.out.println("====>currentDate:"+ Long.valueOf(currentDate));
        System.out.println(Str(10, Long.valueOf(currentDate)));


        executorService.shutdown();
    }

    public static String Str(int Length, long datatime)
    {

        char[] Pattern = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
                'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' , 'a', 'b', 'c',
                'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        String result = "";
        int n = Pattern.length;
        Lock lock = new ReentrantLock();
        try{
            lock.lock();
            Random random = new Random(System.currentTimeMillis());
            for (int i = 0; i < Length; i++)
            {
                int rnd = random.nextInt(n);
                result += Pattern[rnd];
            }
        }finally {
            lock.unlock();
        }
        return result;
    }


}
