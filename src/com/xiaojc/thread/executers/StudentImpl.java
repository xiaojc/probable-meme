package com.xiaojc.thread.executers;

/**
 * 用途：
 * Created by xiaojc on 2016/6/20 14:29.
 */
public class StudentImpl implements IStudent {

    public static void main(String[] args){
        IStudent student = new StudentImpl();
        String str = student.getStudentById("15");
        System.out.println(str);

        IStudent student1 = new IStudent() {
            @Override
            public String getStudentById(String id) {
                return "xiaojc_"+id;
            }
        };


        System.out.println(student1.getStudentById("haha"));

    }


}
