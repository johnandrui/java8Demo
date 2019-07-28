package com.john.www.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

 /**
  * @Author: zhang@self.com
  * @Description: 对url路径的处理
  * @Date: 9:48 2019/7/28
  * @Modified By:
  */
@Component
public class UrlUtil {

    private static Logger logger = LoggerFactory.getLogger(UrlUtil.class);

    private static String HTTP = "http://";

    private static String HTTPS = "https://";


    public static Integer deleteTag;

    @Value("${delete.tag}")
    public void setDeleteTag(Integer deleteTag) {
        UrlUtil.deleteTag = deleteTag;
    }


    /**
     * 文件路径
     */
    private static String filePath;

    @Value("${file.path}")
    public void setFilePath(String filePath) {
        UrlUtil.filePath = filePath;
    }

 /**
  * @Author: zhang@self.com
  * @Description:
  * @Params:
  *   @param: null
  * @Return:
  * @Throws:
  * @Date: Created in 9:49 2019/7/28
  */
    public static String remove(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        if (source.startsWith(HTTP) || source.startsWith(HTTPS)) {
            source = source.substring(StringUtils.ordinalIndexOf(source, "/", deleteTag) + 1);
        }
        return source;
    }

    /**
      * @Author: zhang@self.com
      * @Description: 拼接文件路径
      * @Params:
      *   @param: null
      * @Return:
      * @Throws:
      * @Date: Created in 9:49 2019/7/28
      */
    public static String add(String source) {
        if (StringUtils.isEmpty(source)) {
            return source;
        }
//        return URLUtil.complateUrl(filePath, source);
        return "";
    }

    /**
     * 功能描述: 拼接文件路径
     *
     * @param
     * @author: zhangrui@iyungu.com
     * @return:
     * @date: Created in 13:42 2018/9/7
     */
    public static String adds(String source) {
        if (StringUtils.isEmpty(source)) {
            return source;
        }
//        return URLUtil.complateUrl(filePath, source);
        return "";
    }

    /**
     * 功能描述: 删除文件路径
     *
     * @param
     * @author: zhangrui@iyungu.com
     * @return:
     * @date: Created in 13:42 2018/9/7
     */
    public static String removes(String source) {
        if (StringUtils.isEmpty(source)) {
            return source;
        }
        if (source.startsWith(HTTP) || source.startsWith(HTTPS)) {
            source = source.substring(filePath.length());
        }
        return source;
    }


}
