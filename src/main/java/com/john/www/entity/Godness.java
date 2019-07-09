package com.john.www.entity;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:   女神
 * @Date 2018/5/11  15:40
 * @Modified By:
 */
public class Godness {
    private String name;
    private int age;

    public Godness() {
    }

    public Godness(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Godness{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
