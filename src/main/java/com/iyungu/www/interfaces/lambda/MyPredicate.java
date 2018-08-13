package com.iyungu.www.interfaces.lambda;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description: 策略设计模式-- 优化
 * @Date 2018/5/9  14:46
 * @Modified By:
 */
public interface MyPredicate<T> {
    boolean test(T t);
}
