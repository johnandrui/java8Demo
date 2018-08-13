package com.iyungu.www.annotation.annotations;

import com.iyungu.www.annotation.MyAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description: 注解容器
 * @Date 2018/5/14  10:18
 * @Modified By:
 */
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotations {

    MyAnnotation[] value();
}
