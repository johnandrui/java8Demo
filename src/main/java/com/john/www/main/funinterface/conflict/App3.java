package com.john.www.main.funinterface.conflict;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  18:59
 * @Modified By:
 */
public class App3 implements A,B {
    @Override
    public void sayHello() {
        System.out.println("Hello this is APP3");
    }

    public static void main(String[] args) {
        new App3().sayHello();
    }
}
