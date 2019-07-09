package com.john.www.main.lambda;

import com.john.www.entity.Employee;
import com.john.www.interfaces.lambda.MyFunction;
import com.john.www.interfaces.lambda.MyFunction2;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:  Lambda表达式练习
 * @Date 2018/5/9  17:10
 * @Modified By:
 */
public class TestLambda3 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,4000.12),
            new Employee("李思",38,5000.22),
            new Employee("肇东",58,6000.11),
            new Employee("虎门",18,7000.88),
            new Employee("刘工",48,9000.88),
            new Employee("童敏",8,4000.00)
    );

    /**
     *  1.调用Collection.sort()方法，通过定制排序比较两个Employee(先按年龄比，年龄相同按姓名比)，
     *  使用Lambda作为参数传递。
     */
    @Test
    public void test1(){
        Collections.sort(employees,(e1,e2)->{
            if(e1.getAge() == e2.getAge()){
               return e1.getName().compareTo(e2.getName());
            }else {
                return -Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        for(Employee emp : employees){
           System.out.println(emp);
        }
    }
    /**
     *  2.
     *  （1）声明函数式接口，接口中声明抽象方法， String getValue(String str);
     *   (2)声明类TestLambda,类中编写方法使用接口作为参数，将一个字符串转换成大写，作为方法返回值；
     *   (3)再将一个字符串的第2个和第4个索引位置进行截取子串。
     */
        @Test
        public void test2(){
            System.out.println("\t\t\t强大的去空格！");
            String trimStr = strHandler("\t\t\t强大的去空格！",(str) -> str.trim());
            System.out.println(trimStr);

            String upper = strHandler("abcd",str ->str.toUpperCase());
            System.out.println(upper);

            String subStr = strHandler("零一二三四五",str ->str.substring(2,5));
            System.out.println(subStr);
        }
     public String strHandler(String str, MyFunction mf){
        return mf.getValue(str);
     }
    /**
     *  3.
     *  (1) 声明一个带两个泛型的函数式接口，泛型类型为<T,R>T为参数，R为返回值
     *  (2) 接口中声明对应的抽象方法
     *  (3) 在TestLambda类中声明方法，使用接口作为参数，计算两个long型参数和
     *  (4) 再计算两个long型参数的乘积。
     */
    @Test
    public void test3(){
        op(100L,100L,(x,y) -> x + y);
        op(100L,100L,(x,y)->x*y);
    }
     public void op(Long l1, Long l2, MyFunction2<Long,Long> mf){
           System.out.println(mf.getValue(l1,l2));
     }
}
