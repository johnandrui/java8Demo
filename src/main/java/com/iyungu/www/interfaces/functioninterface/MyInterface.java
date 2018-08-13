package com.iyungu.www.interfaces.functioninterface;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:  接口中的静态方法与默认方法
 * @Date 2018/5/11  16:24
 * @Modified By:
 */
public interface MyInterface {

    default String getName(){return "呵呵";};
    static void show(){
        System.out.println("接口中的静态方法！");
    }


}
