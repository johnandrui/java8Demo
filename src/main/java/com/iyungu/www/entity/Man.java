package com.iyungu.www.entity;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:   man
 * @Date 2018/5/11  15:39
 * @Modified By:
 */
public class Man {

    private Godness godness;

    public Man() {
    }

    public Man(Godness godness) {
        this.godness = godness;
    }

    public Godness getGodness() {
        return godness;
    }

    public void setGodness(Godness godness) {
        this.godness = godness;
    }

    @Override
    public String toString() {
        return "Man{" +
                "godness=" + godness +
                '}';
    }
}
