package com.iyungu.www.main.funinterface;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:计算器
 * @Date 2018/9/1  18:08
 * @Modified By:
 */
public interface Calculator {
    int add(int first, int second);

    int subtract(int first, int second);

    int multiply(int first, int second);

    int divide(int number, int divisor);
     //默认方法
    default int mod(int first, int second){
        return first % second;
    }
     //静态示例
    static Calculator getInstance(){
        return new BasicCalculator(); //实现的子类
    }
}
