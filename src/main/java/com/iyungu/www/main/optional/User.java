package com.iyungu.www.main.optional;

import lombok.Builder;
import lombok.Data;
import org.apache.cxf.annotations.DataBinding;

import java.util.Optional;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/9  16:46
 * @Modified By:
 */
@Data
public class User {
    private String username;
    private String password;
    private Integer age;
    private  Address address;
    private Optional<Address> optAddress;

}
