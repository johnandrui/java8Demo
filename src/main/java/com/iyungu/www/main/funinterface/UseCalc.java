package com.iyungu.www.main.funinterface;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  18:20
 * @Modified By:
 */
public class UseCalc {
    public static void main(String[] args) {
        Calculator calculator=new BasicCalculator();
        int sum=calculator.add(1,2);
        System.out.println(sum);

//        BasicCalculator cal= new BasicCalculator();
        Calculator cal= Calculator.getInstance();
        int difference=cal.subtract(3,2);
        System.out.println(difference);

        System.out.println(cal.mod(3,2));
    }

}
