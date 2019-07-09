package com.john.www.main.optional;

import com.john.www.entity.Employee;
import com.john.www.entity.Godness;
import com.john.www.entity.Man;
import com.john.www.entity.NewMan;
import org.testng.annotations.Test;


import java.util.Optional;

/**
 * @ProjectName: java8Demo
 * @Author： zhnagrui@iyungu.com
 * @Description:  optional类的测试
 * @Date 2018/5/11  14:57
 * @Modified By:
 */
public class TestOptional {
    /**
     *  Optional 容器类的常用方法：
     *  Optional.of(T t):创建一个Optional 实例
     *  Optional.empty():创建一个空的Optional实例
     *  Optional.ofNullable(T t):若t不为null，创建Optional实例，否则创建空实例
     *  isPresent():判断是否包含值
     *  orElse(T t):如果调用对象包含值，返回该值，否则返回t
     *  orElseGet(Supplier s):如果调用对象包含值，返回该值，否则返回s获取的值
     *  map(Function f):如果有值对其处理，并返回处理后的Optional,否则返回Optional.empty()
     *  flatMap(Function mapper):与map类似，要求返回值必须是Optional
     */
    @Test
    public void test1(){
        //Optional.of(T t):创建一个Optional 实例
        /* Optional<Employee> op =Optional.of(new Employee());
        Employee emp = op.get();
        System.out.println(String.valueOf(emp.getAge()));*/
        System.out.println(String.valueOf(Optional.of(new Employee()).get().getAge()));
    }
    @Test
    public void test2(){
        //Optional.empty():创建一个空的Optional实例
        Optional<Employee> op =Optional.empty();
        Employee emp = op.get();
        System.out.println(String.valueOf(emp.getAge()));
    }
    @Test
    public void test3(){
        //Optional.ofNullable(T t):若t不为null，创建Optional实例，否则创建空实例
        Optional<Employee> op =Optional.ofNullable(new Employee("john",41,453,Employee.Status.FREE));

        //isPresent():判断是否包含值
        if(op.isPresent()){
            Employee emp = op.get();
            System.out.println("测试是否包含值："+emp);
        }
        Employee em = op.orElse(new Employee("zhang",10,100, Employee.Status.BUSY));
        System.out.println(em);

        //orElseGet(Supplier s):如果调用对象包含值，返回该值，否则返回s获取的值
       Employee emp =  op.orElseGet(() -> new Employee());  //也可返回其他的对象
    }
    @Test
    public void test4(){
        //map(Function f):如果有值对其处理，并返回处理后的Optional,否则返回Optional.empty()
        Optional<Employee> op =Optional.ofNullable(new Employee("zhang",10,100, Employee.Status.BUSY));
        Optional<String> str = op.map(e -> e.getName());
        System.out.println(str);
        //flatMap(Function mapper):与map类似，要求返回值必须是Optional
        Optional<String> str1 =op.flatMap(e -> Optional.of(e.getName()));
    }
      //例题
      @Test
    public void test5(){
         Man man= new Man();
         String n = getGodnessName(man);
         System.out.println(n);

         Optional<Godness> gn = Optional.ofNullable(new Godness("BO",12));
         Optional<NewMan> op = Optional.ofNullable(null);
         String n2 = getGodnessName2(op);
    }
    /**
     * 需求：获取一个男人心中女神的名字
     */
    public String getGodnessName(Man man){
        if(man !=null){
            Godness gn = man.getGodness();
            if (gn != null){
                return gn.getName();
            }
        }
        return "苍龙";


    }
    public String getGodnessName2(Optional<NewMan> man){
        return man.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("苍龙",10))
                .getName();
    }

/*    DepartmentInfo  bean= departmentDistributionInformationMapper.getById("1");
         logger.info(""+bean);
         Optional.ofNullable(bean);
         if( Optional.ofNullable(bean).isPresent()){
        System.out.println("存在值！");
        System.out.println(String.valueOf(Optional.of(bean).get().getId()));
    }
         if(! Optional.ofNullable(bean).isPresent()){
        System.out.println("不存在值！");
        Optional.ofNullable(bean).orElse(new DepartmentInfo());
    }*/


}
