package com.iyungu.www.main.lambda;

import com.iyungu.www.entity.Employee;
import com.iyungu.www.impl.lambda.FilterEmployeeByAge;
import com.iyungu.www.impl.lambda.FilterEmployeeBySalary;
import com.iyungu.www.interfaces.lambda.MyPredicate;
import org.testng.annotations.Test;

import java.util.*;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:   lambda表达式
 * @Date 2018/5/9  11:04
 * @Modified By:
 */
public class LambdaMain {

    //原来的匿名内部类
    public void compara(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(com);
    }
    //Lambda表达式
    public void comparaByLambda(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }
    //需求：获取当前公司中员工年龄大于35的员工信息
      List<Employee> employees = Arrays.asList(
              new Employee("张三",18,4000.12),
              new Employee("李思",38,5000.22),
              new Employee("肇东",58,6000.11),
              new Employee("虎门",18,7000.88),
              new Employee("刘工",48,9000.88),
              new Employee("童敏",8,4000.00)
    );
    public List<Employee> filteEmployees(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for(Employee emp:list){
             if(emp.getAge() >= 35){
                 emps.add(emp);
             }
        }
        return emps;
    }
    //需求：获取当前公司中员工工资大于5000的员工信息
    public List<Employee> filteEmployees2(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for(Employee emp:list){
            if(emp.getSalary() >= 5000){
                emps.add(emp);
            }
        }
        return emps;
    }

    //优化方式一：策略设计模式
    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp){
         List<Employee> emps = new ArrayList<>();
         for(Employee employee:list){
                if(mp.test(employee)){
                    emps.add(employee);
                }
         }
         return  emps;
    }

    public void modelEmployee(){
        List<Employee> list= filterEmployee(employees,new FilterEmployeeByAge());
        for(Employee employee:list){
                System.out.println(employee);
        }
        System.out.println("________________________");
        List<Employee> listOne = filterEmployee(employees,new FilterEmployeeBySalary());
        for (Employee emp :listOne){
            System.out.println(emp);
        }
    }

    //优化方式二：匿名内部类
    @Test
    public void test5(){
          List<Employee> list = filterEmployee(employees, new MyPredicate<Employee>() {
              @Override
              public boolean test(Employee employee) {
                  return employee.getSalary() <=5000;
              }
          });
          for(Employee employee : list){
              System.out.println(employee);
          }
    }
    //优化方式三：lambda表达式
    @Test
    public void test6(){
         List<Employee> list = filterEmployee(employees,(e) ->e.getSalary() <=5000);
         list.forEach(System.out::println);
    }
    //优化方式四：streamAPI
    @Test
    public void test7(){
        //工资5000的前两位
        employees.stream()
                .filter((e) -> e.getSalary() >=5000)
                .limit(2)
                .forEach(System.out::println);
        System.out.println("____________________________");
        //员工姓名
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    public static void main(String[] args){

    }
}