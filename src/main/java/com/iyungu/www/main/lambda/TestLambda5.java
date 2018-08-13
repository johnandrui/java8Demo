package com.iyungu.www.main.lambda;

import com.iyungu.www.entity.Employee;
import org.testng.annotations.Test;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:    方法引用
 * @Date 2018/5/10  10:39
 * @Modified By:
 */
public class TestLambda5 {
    /**
     * 方法引用 ：若Lambda体中的内容有方法已经实现了，可使用"方法引用"
     *           (可以理解为方法引用是Lambda表达式的另外一种表现形式)
     *    主要语法格式：
     *      对象::实例方法名
     *      类:: 静态方法名
     *      类::实例方法名
     *      注意：
     *      1.lambda体中调用方法的参数列表与返回值类型，要与函数式接口中的抽象方法的函数列表和返回值类型保持一致！
     *      2.若Lambda参数列表中的第一个参数是实例方法的调用者，第二个参数是实例方法的参数时，可以使用ClassName ::方法名
     */
    /**
     *  对象::实例方法名
     *  使用条件：lambda体中的接口方法已经实现，参数列表类型与方法的返回值类型一致
     */
      @Test
    public void test1(){

          Consumer<String> con = x ->System.out.println(x);

          PrintStream ps = System.out;
          Consumer<String> con1 = ps::println;

          Consumer<String> con2 = System.out::println;
          con2.accept("abcd");
      }
      @Test
    public void test2(){
          Employee emp = new Employee();
          Supplier<String> sup = ()->emp.getName();
          sup.get();

          Supplier<Integer> sup2= emp::getAge;
          Integer num = sup2.get();
           System.out.println(num);
      }
       /**
        *  类::静态方法名
        */
       @Test
      public void test3(){
           Comparator<Integer> com = (x,y)->Integer.compare(x,y);
           Comparator<Integer> com1 = Integer::compare;
       }
    /**
     *  类::实例方法名
     */
      @Test
    public void test4(){
          BiPredicate<String,String> bp = (x,y) -> x.equals(y);
           BiPredicate<String,String> bp2 = String::equals;
      }
    /**
     *    二、构造器引用 ：
     *    主要语法格式：
     *      类名（ClassName）:: new
     *      注意：
     *      1.需要调用的构造器的参数列表与函数式接口中的参数列表保持一致！
     */
    @Test
    public void test5(){
        Supplier<Employee> sup = ()->new Employee();
        //构造器引用
        Supplier<Employee> sup1 = Employee::new;
        Employee employee = sup1.get();
    }
    @Test
    public void test6(){
        Function<Integer,Employee> fun=x ->new Employee(x);
        Function<Integer,Employee> fun2 =Employee::new;
        Employee emp = fun2.apply(101);

        BiFunction<Integer,Integer,Employee> bf = Employee::new;
    }
    /**
     *    三、数组引用 ：
     *    主要语法格式：
     *      类型（Type）:: new
     */
    @Test
    public void test7(){
        Function<Integer,String[]> fun=x ->new String[x];
        String[] strArr = fun.apply(10);
        System.out.println(strArr.length);

        Function<Integer,String[]> fun2 = String[]::new;
        String[] strArr1 = fun2.apply(20);
        System.out.println(strArr1.length);
    }


}