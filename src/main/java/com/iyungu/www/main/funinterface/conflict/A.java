package com.iyungu.www.main.funinterface.conflict;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  18:49
 * @Modified By:
 */
public interface A {
    default void sayHello(){
        System.out.println("hell this is java8");
    }
}
