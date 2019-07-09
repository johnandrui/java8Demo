package com.john.www.main.funinterface;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  18:09
 * @Modified By:
 */
public class BasicCalculator implements Calculator {
    @Override
    public int add(int first, int second) {
        return first+second;
    }

    @Override
    public int subtract(int first, int second) {
        return first-second;
    }

    @Override
    public int multiply(int first, int second) {
        return first*second;
    }

    @Override
    public int divide(int number, int divisor) {
        if(divisor ==0){
            throw new IllegalArgumentException("divisor can't be zero");
        }
        return number/divisor;
    }
}
