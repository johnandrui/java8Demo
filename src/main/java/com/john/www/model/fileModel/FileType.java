package com.john.www.model.fileModel;

/**
 * @Author:
 * @Description: 文件类型的枚举
 * @Date: 15:32 2018/8/27
 * @Modified By:
 */
public enum FileType {
    /**
     * word文档
     */
    DOC("doc"),
    DOCX("docx");


    private String name;

    private String value;

    FileType(String name) {
        this.name = name;
    }

    FileType(String name, String value) {
        this(name);
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
