package com.iyungu.www.action;

import com.iyungu.www.interfaces.functioninterface.MyFun;
import com.iyungu.www.interfaces.functioninterface.MyInterface;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:接口中的默认方法与静态方法
 * @Date 2018/5/11  16:18
 * @Modified By:
 */
public class SubClass /*extends MyClass*/ implements MyFun,MyInterface{

    @Override
    public String getName() {
        return MyInterface.super.getName();
    }
}
