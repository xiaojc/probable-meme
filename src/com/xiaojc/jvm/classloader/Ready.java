package com.xiaojc.jvm.classloader;

/**
 * 用途： 测试类加载 准备阶段 static的使用
 * Created by xiaojc on 2016/6/24 9:50.
 */
public class Ready {


    private static long i;
    public int k;
    public char c;
    public float f;
    private static final String str = null;

    public long getNum(){
        return i+k;
    }

    public char getChar(){
        return c;
    }

    public float getFloat(){
        return f;
    }
    public int getPartialVariable(){
        int j = 0;
        return j*1;
    }

    public static void main(String[] args){
        Ready ready = new Ready();
        System.out.println(ready.getNum());
        System.out.println(ready.getPartialVariable());
        System.out.println(ready.getChar());
        System.out.println(ready.getFloat());
    }
}
