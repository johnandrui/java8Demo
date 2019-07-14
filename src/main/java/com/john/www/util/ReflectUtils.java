package com.john.www.util;

import com.john.www.model.MedicalOrderInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * @Author: zhang@self.com
 * @Description:  java-reflect-utils
 * @Date: 9:47 2019/7/14
 * @Modified By:
 */
public class ReflectUtils {


    public static void main(String[] args) throws Exception {
//        List<PatientInfo> patientInfoList = new ArrayList<>();
//        List<PatientInfo> patientInfoList = Arrays.asList(
//                new PatientInfo("ss","ssan床","11"),
//                new PatientInfo("ss2","01床","11"),
//                new PatientInfo("ss3","02","11")
//        );
//
//
//        List<MedicalOrderInfo> medicalOrderInfoList = new ArrayList<>();
//        medicalOrderInfoList.add(new MedicalOrderInfo("AA","wozaiceshi","ssan床"));
//        medicalOrderInfoList.add(new MedicalOrderInfo("aa2","test0","01床"));
//        medicalOrderInfoList.add( new MedicalOrderInfo("aa3","test00008","02"));
////                List<MedicalOrderInfo> medicalOrderInfoList = Arrays.asList(
////                        new MedicalOrderInfo("AA","wozaiceshi","ssan床"),
////                        new MedicalOrderInfo("aa2","test0","01床"),
////                        new MedicalOrderInfo("aa3","test00008","02")
////                );
//        Long startMillis = System.currentTimeMillis();
////        dolist(medicalOrderInfoList,MedicalOrderInfo.class,"bedNum");
//        Long endMillis = System.currentTimeMillis();
//        System.out.println("start ====== > " + medicalOrderInfoList.toString());
//        System.out.println(" cost time ============= > " + (endMillis - startMillis));

//        System.out.println("数据类型测试int：" + getClassTypeValue(int.class, "100"));
        MedicalOrderInfo info = new MedicalOrderInfo();
        setValue(info, MedicalOrderInfo.class, "bedNum", String.class, "26Chuang");
        System.out.println("set方法测试：" + info.toString());
    }

    /**
     * 八种数据类型
     * 通过class类型获取获取对应类型的值
     * 如果类型不匹配，强制转换成指定的数据类型的值
     * @param typeClass class类型
     * @param value 值
     * @return Object
     */
    private static Object getClassTypeValue(Class<?> typeClass, Object value) {
        if (typeClass == int.class || value instanceof Integer) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == short.class) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == byte.class) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == double.class) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == long.class) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == String.class) {
            if (null == value) {
                return "";
            }
            return value;
        } else if (typeClass == boolean.class) {
            if (null == value) {
                return true;
            }
            return value;
        } else if (typeClass == BigDecimal.class) {
            if (null == value) {
                return new BigDecimal(0);
            }
            return new BigDecimal(value + "");
        } else {
            return typeClass.cast(value);
        }
    }



    /**
     * 根据属性，获取get方法并返回对象属性值
     * @param ob 对象
     * @param name 属性名
     * @return
     * @throws Exception
     */
    public static Object getGetMethodVal(Object ob , String name)throws Exception{
        Method[] m = ob.getClass().getMethods();
        for(int i = 0;i < m.length;i++){
            if(("get"+name).toLowerCase().equals(m[i].getName().toLowerCase())){
                return m[i].invoke(ob);
            }
        }
        return null;
    }
    /**
     * 根据属性，拿到set方法，并把值set到对象中
     * @param obj        对象
     * @param clazz      对象的class
     * @param filedName  需要设置值得属性
     * @param typeClass  字段数据类型
     * @param value      字段值
     */
    public static void setValue(Object obj,Class<?> clazz,String filedName,Class<?> typeClass,Object value){
        filedName = removeLine(filedName);
        String methodName = "set" + filedName.substring(0,1).toUpperCase()+filedName.substring(1);
        try{
            Method method =  clazz.getDeclaredMethod(methodName, new Class[]{typeClass});
            method.invoke(obj, new Object[]{getClassTypeValue(typeClass, value)});
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    /**
     * remove "_" toUpper
     * 处理字符串  如：  abc_dex ---> abcDex
     * @param str
     * @return
     */
    public static  String removeLine(String str){
        if(null != str && str.contains("_")){
            int i = str.indexOf("_");
            char ch = str.charAt(i+1);
            char newCh = (ch+"").substring(0, 1).toUpperCase().toCharArray()[0];
            String newStr = str.replace(str.charAt(i+1), newCh);
            String newStr2 = newStr.replace("_", "");
            return newStr2;
        }
        return str;
    }
    /**
     * 两者属性名一致时，拷贝source里的属性到dest里
     *
     * @return void
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("unchecked")
    public static void copyPorperties(Object dest, Object source) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class srcCla = source.getClass();
        Field[] fsF = srcCla.getDeclaredFields();

        for (Field s : fsF)
        {
            String name = s.getName();
            Object srcObj = invokeGetterMethod(source, name);
            try
            {
                BeanUtils.setProperty(dest, name, srcObj);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * 调用Getter方法.
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object invokeGetterMethod(Object target, String propertyName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        return invokeMethod(target, getterMethodName, new Class[] {},
                new Object[] {});
    }

    /**
     * 直接调用对象方法, 无视private/protected修饰符.
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object invokeMethod(final Object object,
                                      final String methodName, final Class<?>[] parameterTypes,
                                      final Object[] parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        if (method == null)
        {
            throw new IllegalArgumentException("Could not find method ["
                    + methodName + "] parameterType " + parameterTypes
                    + " on target [" + object + "]");
        }

        method.setAccessible(true);
        return method.invoke(object, parameters);
    }

    /**
     * 循环向上转型, 获取对象的DeclaredMethod.
     *
     * 如向上转型到Object仍无法找到, 返回null.
     */
    protected static Method getDeclaredMethod(Object object, String methodName,
                                              Class<?>[] parameterTypes)
    {
        Assert.notNull(object, "object不能为空");

        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass())
        {
            try{
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            }
            catch (NoSuchMethodException e)
            {// NOSONAR
                // Method不在当前类定义,继续向上转型
            }
        }
        return null;
    }



}
