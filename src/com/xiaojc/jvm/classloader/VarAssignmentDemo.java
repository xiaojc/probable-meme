package com.xiaojc.jvm.classloader;

/**
 * 用途： 类初始化 变量赋值demo
 * Created by xiaojc on 2016/6/24 11:45.
 */
public class VarAssignmentDemo {

    public static void main(String[] args) {
        System.out.println("====>value:"+ FatherVar.b);
    }
}

class SuperVar {

    static {
        a = 2;
//        int b = a;
    }
    public static int a = 1;
}

class FatherVar extends SuperVar {

    public static int b = a;

}
