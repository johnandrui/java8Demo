package com.john.www.main.funinterface;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  18:32
 * @Modified By:
 */
public class CalculatorFactory {
     public static Calculator getInstanc(){
         return new BasicCalculator();
     }
}
