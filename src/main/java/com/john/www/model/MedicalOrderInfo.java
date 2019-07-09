package com.john.www.model;

public class MedicalOrderInfo {

    private String code;
    private String name;
    private String bedNum;

    public MedicalOrderInfo() {
    }

    public MedicalOrderInfo(String code, String name, String bedNum) {
        this.code = code;
        this.name = name;
        this.bedNum = bedNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    @Override
    public String toString() {
        return "MedicalOrderInfo{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", bedNum='" + bedNum + '\'' +
                '}';
    }
}
