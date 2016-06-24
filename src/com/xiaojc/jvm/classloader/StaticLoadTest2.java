package com.xiaojc.jvm.classloader;

/**
 * 用途：如果没有，则会按照继承关系从上往下递归搜索该类所实现的各个接口和它们的父接口
 * Created by xiaojc on 2016/6/24 10:44.
 */
public class StaticLoadTest2 implements IChild{
    @Override
    public void haha() {
        System.out.println("===>StaticLoadTest2:"+i);
    }

    public static void main(String[] args){
        StaticLoadTest2 test2 = new StaticLoadTest2();
        test2.haha();

    }
}


interface ISupper{

    static int i = 11;
    default void haha(){
        System.out.println("======>ISupper:"+i);
    }
}

interface IFather extends ISupper{

    static int i = 22;
    default void haha(){
        System.out.println("======>IFather:"+i);
    }
}

interface IChild extends IFather{

//    static int i = 33;
    default void haha(){
        System.out.println("======>IChild:"+i);
    }
}

