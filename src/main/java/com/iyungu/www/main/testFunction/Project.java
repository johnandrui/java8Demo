package com.iyungu.www.main.testFunction;

import lombok.Builder;
import lombok.Data;


/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  12:19
 * @Modified By:
 */
@Data
@Builder
public class Project {
    /**
     * 项目名称
     */
    private String  name;

    /**
     * 编程语言
     */
    private String  language;

    /**
     * star 数
     */
    private Integer stars;

    /**
     * 描述
     */
    private String  description;

    /**
     * 作者
     */
    private String author;
}
