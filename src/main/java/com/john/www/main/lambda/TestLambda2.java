package com.john.www.main.lambda;

import com.john.www.interfaces.lambda.MyFun;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description: lambda表达式的基础语法
 * @Date 2018/5/9  15:46
 * @Modified By:
 */
public class TestLambda2 {
    /**
     * 语法格式一：无参数，无返回值
     * 格式： () -> System.out.println("Hello Lambda!");
     */
    @Test
    public void test1(){
         int num = 0;//jdk1.7以前的，必须是final 局部内类，同级别的局部变量 现在是默认加上，不写
        //
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hellow World!"+num);
            }
        };
        r.run();
        System.out.println("____________________");
        Runnable r1 =()-> System.out.println("Hello Lambda !"+num);
        r1.run();
    }
    /**
     * 语法格式二：一个参数，无返回值
     * 格式： (x) ->System.out.println(x);
     */
    @Test
    public void test2(){
        Consumer<String> con = (x) ->System.out.println(x);
        con.accept("强大的Lambda表达式！");
    }
    /**
     * 语法格式三：一个参数，小括号可以不写，无返回值
     * 格式： x ->System.out.println(x);
     */
    @Test
    public void test3(){
        Consumer<String> con = x ->System.out.println(x);
        con.accept("强大的Lambda表达式！");
    }
    /**
     * 语法格式四：有两个以上参数，并且Lambda体中多条语句，有返回值
     * 格式： (x,y) ->{
     *               System.out.println("函数式接口");
     *                return Integer.compare(x,y);
     *               };
     */
    @Test
    public void test4(){
        Comparator<Integer> com = (x,y) ->{
              System.out.println("函数式接口");
              return Integer.compare(x,y);
        };
    }
    /**
     * 语法格式五：有两个以上参数，并且Lambda体中一条语句，有返回值  ，return 和 大括号都可以不写
     * 格式： (x,y) ->  Integer.compare(x,y);
     */
    @Test
    public void test5(){
        Comparator<Integer> com = (x,y) ->  Integer.compare(x,y);
    }
    /**
     * 语法格式六：Lambda表达式的参数列表的参数类型可以不写，因为JVM的编译器可通过上下文推断出，数据类型，即"类型推断"
     *    如果写，所有参数的类型都必须写
     * 格式： (x,y) ->  Integer.compare(x,y);
     */
    @Test
    public void test6(){
        Comparator<Integer> com = (x,y) ->  Integer.compare(x,y);
    }
    /**
     *  总结：
     *  上联：左右遇一括号省
     *  下联：左侧推断类型省
     *  横批：能省则省
     */

    /**
     * 二、Lambda表达式需要“函数式接口”的支持
     * 函数式接口：接口中只有一个抽象方法的接口 ，可以用注解@FunctionalInterface修饰
     *            可以检查是否是函数式接口
     */
    /**
     * 三、使用
     * 需求：对一个数进行运算
     */
    @Test
    public void test7(){
       Integer num = operation(100,(x)->x*x);
       System.out.println(num);
    }
    public Integer operation(Integer num, MyFun mf){
        return mf.getValue(num);
    }


}
