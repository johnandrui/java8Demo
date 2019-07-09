package com.john.www.main.funinterface;

import com.john.www.action.SubClass;

import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:测试接口中的默认方法与静态方法
 * @Date 2018/5/11  16:20
 * @Modified By:
 */
public class TestDefaultInterface {
    public static void main(String[] args){
        SubClass sub = new SubClass();
        sub.getName();
        test();
    }

    public static void test(){
        List list= Arrays.asList("a","b","c","d");
        list.forEach(System.out::println);
    }

}
