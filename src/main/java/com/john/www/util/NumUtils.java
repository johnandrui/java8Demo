package com.john.www.util;

import com.john.www.model.MedicalOrderInfo;
import com.john.www.model.PatientInfo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumUtils {





    /**
     * 截取字符串中的数字
     * @param content
     * @return
     */
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    /**
     * 根据属性，获取get方法
     * @param ob 对象
     * @param name 属性名
     * @return
     * @throws Exception
     */
    public static Object getGetMethod(Object ob , String name)throws Exception{
        Method[] m = ob.getClass().getMethods();
        for(int i = 0;i < m.length;i++){
            if(("get"+name).toLowerCase().equals(m[i].getName().toLowerCase())){
                return m[i].invoke(ob);
            }
        }
        return null;
    }



    public static void main(String[] args) throws Exception {
//        List<PatientInfo> patientInfoList = new ArrayList<>();
        List<PatientInfo> patientInfoList = Arrays.asList(
                new PatientInfo("ss","ssan床","11"),
                new PatientInfo("ss2","01床","11"),
                new PatientInfo("ss3","02","11")
        );


                List<MedicalOrderInfo> medicalOrderInfoList = new ArrayList<>();
        medicalOrderInfoList.add(new MedicalOrderInfo("AA","wozaiceshi","ssan床"));
        medicalOrderInfoList.add(new MedicalOrderInfo("aa2","test0","01床"));
        medicalOrderInfoList.add( new MedicalOrderInfo("aa3","test00008","02"));
//                List<MedicalOrderInfo> medicalOrderInfoList = Arrays.asList(
//                        new MedicalOrderInfo("AA","wozaiceshi","ssan床"),
//                        new MedicalOrderInfo("aa2","test0","01床"),
//                        new MedicalOrderInfo("aa3","test00008","02")
//                );
       Long startMillis = System.currentTimeMillis();
        dolist(medicalOrderInfoList,MedicalOrderInfo.class,"bedNum");
        Long endMillis = System.currentTimeMillis();
        System.out.println("start ====== > " + medicalOrderInfoList.toString());
        System.out.println(" cost time ============= > " + (endMillis - startMillis));
    }


   public static List<?> dolist(List<?> list,Class clazz,String name) throws Exception {
       List<Object> list2 = new ArrayList<>();
       for (Object temp : list
       ) {
           Object bedNum = getGetMethod(temp,"bedNum");
           if(bedNum.equals("ssan床")){
               list2.add(temp);
           }
           System.out.println(getGetMethod(temp,"bedNum"));
       }
       list.removeAll(list2);
     return list;
    }







}
