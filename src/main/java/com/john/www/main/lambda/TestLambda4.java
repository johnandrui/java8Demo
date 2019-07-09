package com.john.www.main.lambda;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description: java8 内置的四大核心函数式接口
 * @Date 2018/5/9  18:04
 * @Modified By:
 */
public class TestLambda4 {
    /**
     *   Consumer<T>:消费型接口
     *   void accept(T t);
     *
     */
    @Test
    public void test1(){
        happy(1000,m -> System.out.println("桑拿，消费"+m+"元！"));
    }
    public void happy(double money,Consumer<Double> con){
          con.accept(money);
    }
    /**
     *   Supplier<T>:供给型接口
     *   T get();
     *
     */
    @Test
    public void test2(){
        //100以内的随机数
       List<Integer> numList=getNumList(10,()->(int)(Math.random()*100));
       for(Integer num : numList){
           System.out.println(num);
       }
    }
    //需求： 产生一些整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup){
       List<Integer> list = new ArrayList<>();
       for (int i= 0;i<num;i++){
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }
    /**
     *   Function<T,R>:函数型接口
     *   R apply(T t);
     *
     */
    @Test
    public void test3(){
       String st = strHandler("\t\t\t强大的Lambda表达式",(str)->str.trim());
       System.out.println(st);
       String subStr = strHandler("强大的Lambda表达式",str ->str.substring(0,3));
       System.out.println(subStr);
    }
    //需求：处理字符串
    public String strHandler(String str,Function<String,String> fun){
      return fun.apply(str);
    }
    /**
     *   Predicate<T>:断言型接口 判断操作
     *   boolean test(T t);
     *
     */
    @Test
    public void test4(){
        List<String> list = Arrays.asList("Hello","ha","Lambda","ok");
        List<String> newStr = filterStr(list,s ->s.length() >3);
        System.out.println(newStr);

    }
     //需求：将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> strings, Predicate<String> pre){
       List<String> strList = new ArrayList<>();
       for (String str : strings){
             if(pre.test(str)){
                 strList.add(str);
             }
       }
       return strList;
    }
}
