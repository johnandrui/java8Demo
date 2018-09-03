package com.iyungu.www.main.funinterface.conflict;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  18:54
 * @Modified By:
 */
public class App implements A {
    @Override
    public void sayHello() {
        System.out.println("Hello this is APP");
    }

    public static void main(String[] args) {
        new App().sayHello();
    }
}
