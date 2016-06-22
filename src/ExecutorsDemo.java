import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 用途：
 * Created by xiaojc on 2016/6/20 14:42.
 */
public class ExecutorsDemo {


    public static void main(String[] args){


//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        ExecutorService executorService2 = Executors.newCachedThreadPool();

        List<Future<String>> futureList = new ArrayList<Future<String>>();


       /* for (int i=0; i< 3; i++) {
            Future future = executorService1.submit(new CallableTest());
            try {
                Object str = future.get();
                System.out.println(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService1.shutdown();*/


        for (int i = 0; i < 10; i++){
            Future<String> future = executorService2.submit(new CallableTest(i));
            futureList.add(future);
        }

        System.out.println("futureList 的size大小是："+futureList.size());
        for (Future<String> future : futureList){

            try {
                while (!future.isDone());

                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                executorService2.shutdown();
            }
        }

    }
}

class RunnableTest implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"============>开始执行线程。。。");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"============>结束执行线程。。。");
    }
}


class CallableTest implements Callable<String>{
    private int id;

    public CallableTest(int id){
        this.id = id;
    }
    @Override
    public String call() throws Exception {

//        System.out.println(Thread.currentThread().getName()+"============>开始执行线程。。。");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(Thread.currentThread().getName()+"============>结束执行线程。。。");

        return  "call()方法被自动调用，任务返回的结果是：" + id + "    " + Thread.currentThread().getName();
    }
}
