package com.iyungu.www.entity;

import java.util.Optional;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:   optional
 * @Date 2018/5/11  15:52
 * @Modified By:
 */
public class NewMan {
    private Optional<Godness> godness= Optional.empty();

    public NewMan() {
    }

    public NewMan(Optional<Godness> godness) {
        this.godness = godness;
    }

    public Optional<Godness> getGodness() {
        return godness;
    }

    public void setGodness(Optional<Godness> godness) {
        this.godness = godness;
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "godness=" + godness +
                '}';
    }
}
