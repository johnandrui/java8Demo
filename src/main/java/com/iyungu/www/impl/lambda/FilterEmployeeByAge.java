package com.iyungu.www.impl.lambda;

import com.iyungu.www.entity.Employee;
import com.iyungu.www.interfaces.lambda.MyPredicate;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:   优化 -- 设计模式
 * @Date 2018/5/9  14:49
 * @Modified By:
 */
public class FilterEmployeeByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >=35;
    }
}
