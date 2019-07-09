package com.john.www.impl.lambda;

import com.john.www.entity.Employee;
import com.john.www.interfaces.lambda.MyPredicate;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:
 * @Date 2018/5/9  15:07
 * @Modified By:
 */
public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() >5000;
    }
}
