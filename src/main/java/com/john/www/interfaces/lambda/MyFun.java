package com.john.www.interfaces.lambda;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:     自定义函数式接口
 * @Date 2018/5/9  16:58
 * @Modified By:
 */
@FunctionalInterface
public interface MyFun<T> {
     Integer getValue(Integer num);
}
