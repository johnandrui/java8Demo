package com.iyungu.www.main.funinterface;

import com.iyungu.www.action.SubClass;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:测试接口中的默认方法与静态方法
 * @Date 2018/5/11  16:20
 * @Modified By:
 */
public class TestDefaultInterface {
    public static void main(String[] args){
        SubClass sub = new SubClass();
        sub.getName();
    }
}
