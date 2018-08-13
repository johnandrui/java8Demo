package com.iyungu.www.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: biss-app
 * @Author： zhangrui@iyungu.com
 * @Description:     身份证的处理类
 * @Date 2018/7/24  16:22
 * @Modified By:
 */
@Component
public class IdCarUtil {
    /**
     * 日志类
     */
    private static Logger logger = LoggerFactory.getLogger(IdCarUtil.class);
    /**
     * 获取 生日
     * @param CardCode
     * @return
     */
    public static String getBirthdayByIdCard(String CardCode){

        if(JavaUtil.isStringEmpty(CardCode)){
            logger.info("【IdCarUtil】类中：getBirthdayByIdCard(String CardCode)的CardCode为空！");
           return "";
        }
        Integer len = CardCode.length();
        String bir;
        if(len==18){
            bir = getBirthdayByIdCard18(CardCode);
        }else{
            bir = getBirthdayByIdCard15(CardCode);
        }
        return bir;
    }

    /**
     * 获取 年龄
     * @param CardCode
     * @return
     */
    public static String getAgeByIdCard(String CardCode){

        if(JavaUtil.isStringEmpty(CardCode)){
            logger.info("【IdCarUtil】类中：getAgeByIdCard(String CardCode)的CardCode为空！");
            return "";
        }
        Integer len = CardCode.length();
        String age;
        if(len==18){
            age = getAgeByIdCard18(CardCode);
        }else{
            age = getAgeByIdCard15(CardCode);
        }
        return age;
    }

    /**
     * 获取 性别
     * @param CardCode
     * @return
     */
    public static String getSexByIdCard(String CardCode){
      //  Integer len = CardCode.length();
        if(JavaUtil.isStringEmpty(CardCode)){
            logger.info("【IdCarUtil】类中：getSexByIdCard(String CardCode)的CardCode为空！");
            return "";
        }
        Integer len = CardCode.length();
        String sex;
        if(len==18){
            sex = getSexByIdCard18(CardCode);
        }else{
            sex = getSexByIdCard15(CardCode);
        }
        return sex;
    }

    /**
     * 18位身份证
     * 获取 生日
     * @param CardCode
     * @return
     */
    private static String getBirthdayByIdCard18(String CardCode) {

        if( JavaUtil.isStringEmpty(CardCode)){
            logger.info("【IdCarUtil】类中：getBirthdayByIdCard18(String CardCode)的CardCode为空！");
            return "";
        }
        Integer len = CardCode.length();
        //身份证上的年月日
        String idyear = CardCode.substring(6).substring(0, 4);// 得到年份
        String idyue = CardCode.substring(10).substring(0, 2);// 得到月份
        String idday = CardCode.substring(12).substring(0, 2);//得到日
        String bir = idyear+"-"+idyue +"-"+ idday;
        return bir;
    }

    /**
     * 18位身份证
     * 获取 性别
     * @param CardCode
     * @return
     */
    private static String getSexByIdCard18(String CardCode) {
        if(JavaUtil.isStringEmpty(CardCode)){
            logger.info("【IdCarUtil】类中：getSexByIdCard18(String CardCode)的CardCode为空！");
            return "";
        }
        String sex = "0"; //未知
        if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
            sex = "2"; //女
        } else {
            sex = "1"; //男
        }
        return sex;
    }

    /**
     * 18位身份证
     * 获取 年龄
     * @param CardCode
     * @return
     */
    private static String getAgeByIdCard18(String CardCode){

        if(JavaUtil.isStringEmpty(CardCode)){
            logger.info("【IdCarUtil】类中：getAgeByIdCard18(String CardCode)的CardCode为空！");
            return "";
        }
        //身份证上的年月日
        String idyear = CardCode.substring(6).substring(0, 4);// 得到年份
        String idyue = CardCode.substring(10).substring(0, 2);// 得到月份
        String idday = CardCode.substring(12).substring(0, 2);//得到日
        String idyr = idyue + idday + "";

        //当前年月日
        String year = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(0, 4);// 当前年份
        String yue = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(5, 7);// 月份
        String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(8, 10);
        String yr = yue + day + "";

        int age = 0;
        if (Integer.parseInt(idyr) <= Integer.parseInt(yr)) { // 表示生日已过
            age = Integer.parseInt(year) - Integer.parseInt(idyear) + 1;
        } else {// 生日未过
            age = Integer.parseInt(year) - Integer.parseInt(idyear);
        }
        return age + "";
    }
    /**
     * 15位身份证
     * 获取 生日
     * @param CardCode
     * @return
     */
    private static String getBirthdayByIdCard15(String CardCode) {

        if(JavaUtil.isStringEmpty(CardCode)){
            logger.info("【IdCarUtil】类中：getBirthdayByIdCard15(String CardCode)的CardCode为空！");
            return "";
        }
        Integer len = CardCode.length();
        //身份证上的年月日
        String idyear = "19" + CardCode.substring(6, 8);
        String idyue = CardCode.substring(8, 10);
        String idday = CardCode.substring(10, 12);
        String bir = idyear+"-"+idyue +"-"+ idday;
        return bir;
    }
    /**
     * 15位身份证
     * 获取 性别
     * @param CardCode
     * @return
     */
    private static String getSexByIdCard15(String CardCode) {
        String sex = "2"; //未知
        if (Integer.parseInt(CardCode.substring(14, 15)) % 2 == 0) {// 判断性别
            sex = "0"; //女
        } else {
            sex = "1"; //男
        }
        return sex;
    }
    /**
     * 15位身份证
     * 获取 年龄
     * @param CardCode
     * @return
     */
    private static String getAgeByIdCard15(String CardCode){
        //身份证上的年月日
        String idyear = "19" + CardCode.substring(6, 8);
        String idyue = CardCode.substring(8, 10);
        String idday = CardCode.substring(10, 12);
        String idyr = idyue + idday + "";
        //当前年月日
        String year = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(0, 4);// 当前年份
        String yue = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(5, 7);// 月份
        String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(8, 10);
        String yr = yue + day + "";
        int age = 0;
        if (Integer.parseInt(idyr) <= Integer.parseInt(yr)) { // 表示生日已过
            age = Integer.parseInt(year) - Integer.parseInt(idyear) + 1;
        } else {// 生日未过
            age = Integer.parseInt(year) - Integer.parseInt(idyear);
        }
        return age + "";
    }
}
