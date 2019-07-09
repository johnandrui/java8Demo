package com.john.www.main.optional;

import lombok.Builder;
import lombok.Data;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/9  16:48
 * @Modified By:
 */
@Data
public class Address {
    private  String name;
    private  String street;
    private  String door;

    public Address() {
    }

    public Address(String street, String door) {
        this.street = street;
        this.door = door;
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", door='" + door + '\'' +
                '}';
    }
}
