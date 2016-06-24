package com.xiaojc.jvm.classloader;

/**
 * 用途： static 在类加载时的执行顺序[变量的加载顺序]
 * 对字段进行解析时，会先在本类中查找是否包含有简单名称和字段描述符都与目标相匹配的字段，如果有，则查找结束；
 * Created by xiaojc on 2016/6/24 10:35.
 */
public class StaticLoadTest {

    public static void main(String[] args){
        System.out.println(Child.i);
    }
}


class Super{
    public static int i = 12;
    static {
        System.out.println("我是最大的祖类");
    }
}

class Father extends Super{
//    public static int i = 33;
    static {
        System.out.println("我是Father继承了supper的子类");
    }
}

class Child extends Father{
//    public static int i = 13;
    static {
        System.out.println("我是Child继承了Fatherr的子类");
    }
}
