package com.john.www.model;

public class PatientInfo {

    private String name;
    private String bedNum;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBedNum() {
        return bedNum;
    }

    public void setBedNum(String bedNum) {
        this.bedNum = bedNum;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public PatientInfo() {
    }

    public PatientInfo(String name, String bedNum, String age) {
        this.name = name;
        this.bedNum = bedNum;
        this.age = age;
    }
}
