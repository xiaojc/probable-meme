package com.xiaojc.thread.executers;

/**
 * 用途：
 * Created by xiaojc on 2016/6/20 14:28.
 */
public interface IStudent {

    default String getStudentById(String id){

        return id +"_xiaojc";
    }
}
