package com.iyungu.www.main.funinterface.conflict;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  18:52
 * @Modified By:
 */
public interface C extends A {
    default void sayHello(){
        System.out.println("hell this is Interface C");
    }
}
