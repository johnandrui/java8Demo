package com.iyungu.www.main.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.*;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  15:16
 * @Modified By:
 */
public class TestLambda7 {
    /**
     * 断言
     */
    public static void predicate(){
        Predicate<String> namesStartingWiths=  name-> name.startsWith("s");
        boolean hello = namesStartingWiths.test("Hello");
    }
    /**
     * 消费数据
     */
    public static void consumer(){
        Consumer<String> messageConsumer=message -> System.out.println(message);
        messageConsumer.accept("Learn Java8");
    }
    /**
     * 转换
     */
    public static void convertFun(){
        Function<String,String> toUpperCase = name -> name.toUpperCase();
         String ss=toUpperCase.apply("Java");
        System.out.println(ss);
    }
    /**
     * 提供数据
     */
    public static void supplier(){
        Supplier<String> uuidGenerator=() -> UUID.randomUUID().toString();
        System.out.println(uuidGenerator.get());
    }

    public static void main(String[] args) {
//        predicate(); consumer();convertFun();supplier();
        List<Integer> list=new ArrayList<>();
        for (int i = 300; i < 400; i++) {
            list.add(i);
        }
        IntPredicate evenNumbers=i ->i%2 ==0;
        list.stream()
                .filter(i ->i%2 ==0)
                .forEach(System.out::println);
        System.out.println(evenNumbers.test(1000));
        System.out.println("++++++++++10+++++++++");

        IntPredicate oddNumbers=i -> i%2==1;
        oddNumbers.test(19);
        System.out.println(oddNumbers.test(10));
        System.out.println("++++++++++11+++++++++");

        Function<Integer,Integer> add1=x -> x+1;
        Function<String,String> add2=x -> x+1;

        Integer two =add1.apply(1);
        String answer=add2.apply("1");
        System.out.println("first:"+two+";\nsecond:"+answer);

        BinaryOperator<Integer> sum =(a,b) -> a+b;
        System.out.println("sum:"+sum.apply(12,1));

        BinaryOperator<Function<Integer,Integer>> compose=(a,b)->x->b.apply(a.apply(x));
//        BinaryOperator<Integer> compose1=compose.apply(1,1);

        UnaryOperator<Integer>     add3=n->n+1;
        System.out.println("+++==="+add3.apply(2));

        UnaryOperator<String>      concat=s->s+1;
        Function<Integer,UnaryOperator<Integer>>  sum2=x->y->x+y;
//        System.out.println("sum2=="+sum2.apply(2));
        UnaryOperator<Integer>  sum3=sum2.apply(10);
        System.out.println("sum3:"+sum3.apply(5));



    }
}
