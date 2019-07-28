package com.john.www.model.apk;

import net.dongliu.apk.parser.bean.ApkMeta;

/**
 * @ProjectName:
 * @Author：
 * @Description:
 * @Date 2019/4/12  11:51
 * @Modified By:
 */
public class ApkInfoVo extends ApkMeta {
    /**
     * 文件地址
     */
    private String fileUrl;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
