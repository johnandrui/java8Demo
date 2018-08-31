package com.iyungu.www.main.annotationTest;

import com.iyungu.www.annotation.MyAnnotation;
import org.testng.annotations.Test;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:    测试重复注解
 * @Date 2018/5/14  10:16
 * @Modified By:
 */
public class TestAnnotation {
    //checker framework  验证框架
    private /*@NotNull*/ Object obj = null;

    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public static void show(@MyAnnotation("ABC")String str){
        System.out.println("test reapet annotation");
    }
    /**
     * 注解与反射配合使用
     */
    @Test
    public void test1() throws NoSuchMethodException {

        Class<TestAnnotation> clazz =TestAnnotation.class;
        Method m1=clazz.getMethod("show");
        MyAnnotation[] mas = m1.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation myAnnotation: mas
             ) {
            System.out.println(myAnnotation.value());
        }
    }

}
