package com.john.www.main.lambda;

import java.util.function.Function;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  16:49
 * @Modified By:
 */
public class OtherReference {
    public static void main(String[] args) {
        Function<Integer,String[]> fun=x-> new String[x];
        String[]  strs=fun.apply(2);
        System.out.println(strs.length);

        Function<Integer,String[]> fun1=String[]::new;
        strs=fun1.apply(23);
        System.out.println(strs.length);
    }
}
