package com.john.www.main.lambda;

import java.util.function.Supplier;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  16:43
 * @Modified By:
 */
public class ConstructorReference {
    /**
     * 构造器引用：根据参数列表自动匹配构造器
     * 对于一个现有构造函数，可利用它的名称和关键字 new来创建它的一个引用：ClassName::new.
     * 功能与指向静态方法的引用类似。
     */
    Supplier<ConstructorReference> sup=ConstructorReference::new;
    ConstructorReference constructorReference=sup.get();
}
