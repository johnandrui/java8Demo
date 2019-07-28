package com.john.www.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OssUrlUtil {

    private Logger logger = LoggerFactory.getLogger(OssUrlUtil.class);

    //阿里云资源服务器访问地址
    public String aliyuncs_server_name = "";

    /**
     * http 变量
     */
    public String DOMAIN_HTTP = "http";


    /**
     * 转换文件路径
     */
    public String getFileUrl(String url) {
        String result = "";
        if (StringUtils.isNotEmpty(url)) {
            if (url.trim().indexOf(DOMAIN_HTTP) == 0) {
                result = url;
            } else {
                if (aliyuncs_server_name != null && StringUtils.isNotEmpty(aliyuncs_server_name)) {
                    result = aliyuncs_server_name + url;
                }
            }
        }
        return result;
    }
    /**
     * 转换文件路径
     */
    public String setFileUrl(String url) {
        String result = "";
        if (StringUtils.isNotEmpty(url)) {
            if (aliyuncs_server_name != null && url.trim().indexOf(aliyuncs_server_name) == 0) {
                result = url.replaceAll(aliyuncs_server_name, "");
            } else {
                result = url;
            }
        }
        return result;
    }

}
