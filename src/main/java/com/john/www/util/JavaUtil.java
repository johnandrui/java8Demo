package com.john.www.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class JavaUtil {

	/**
	 * 判断string是否为空
	 * @param	string
	 * @return	boolean
	 */
	public static boolean isStringEmpty(String string) {
		boolean bool = true;
		if(!"".equalsIgnoreCase(string) && string != null){
			bool = false;
		}
		return bool;
	}

	/**
	 * 判断list是否为空
	 * @param:	map
	 * @return	boolean
	 */
	public static boolean isListEmpty(List<?> list) {
		boolean bool = true;
		if(list != null && !list.isEmpty()){
			bool = false;
		}
		return bool;
	}

	/**
	 * 判断map是否为空
	 * @param	map
	 * @return	boolean
	 */
	public static boolean isMapEmpty(Map<?, ?> map) {
		boolean bool = true;
		if(map != null && !map.isEmpty()){
			bool = false;
		}
		return bool;
	}

	/**
	 * 判断两个map是否相同
	 * @param:	map1操作是的值
	 * @param:	map2数据库现在的值
	 * @return	boolean
	 */
	public static boolean isEqual(Map<String, Object> map1, Map<String, Object> map2) {
		boolean bool = true;
		Set<String> map2Key = map2.keySet();
		for (String key : map2Key) {
			if(!key.toLowerCase().contains("time") && !key.toLowerCase().contains("date")){
				if(map1.get(key) != null && map2.get(key) != null){
					if (!map1.get(key).toString().equalsIgnoreCase(map2.get(key).toString())) {
						bool = false;
						break;
					}
				}
			}
		}
		return bool;
	}

	/**
	 * 把vo封装为map
	 * @param: <T>
	 * @param: 	<T>
	 * @param:	class
	 * @return	map
	 */
	public static Map<String, Object> beanToMap(Object clazz) throws Exception{
		Class<?> classes = clazz.getClass();
		List<String> namelist = new ArrayList<String>();
		for (Field field : classes.getDeclaredFields()) {
			namelist.add(field.getName());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (String name : namelist) {
			String methodName = "get" + firstUpCase(name);
			Method method = classes.getMethod(methodName);
			map.put(name, method.invoke(clazz));
		}
		return map;
	}

	/**
	 * Xml string转换成Map
	 * @param xmlStr
	 * @return
	 */
	public static Map<String,Object> xmlString2Map(String xmlStr){
		Map<String,Object> map = new HashMap<String,Object>();
		Document doc;
		try {
			doc = DocumentHelper.parseText(xmlStr);
			Element el = doc.getRootElement();
			map = recGetXmlElementValue(el,map);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 循环解析xml
	 * @param ele
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	private static Map<String, Object> recGetXmlElementValue(Element ele, Map<String, Object> map){
		List<Element> eleList = ele.elements();
		if (eleList.size() == 0) {
			map.put(ele.getName(), ele.getTextTrim());
			return map;
		} else {
			for (Iterator<Element> iter = eleList.iterator(); iter.hasNext();) {
				Element innerEle = iter.next();
				recGetXmlElementValue(innerEle, map);
			}
			return map;
		}
	}

	/**
	 * 把vo封装为map,map的key值为注释的值
	 * @param	obj
	 * @return	map
	 */
/*	public static Map<String, Object> transBean2MapByAnn(Object obj) throws Exception{
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Class<?> classes = obj.getClass();
		for (Field field : classes.getDeclaredFields()) {
			String fieldName = field.getName();
			if(field.isAnnotationPresent(Column.class)){
				Column column = field.getAnnotation(Column.class);
				String key = column.name();
				String methodName = "get" + firstUpCase(fieldName);
				Method method = classes.getMethod(methodName);
				map.put(key, method.invoke(obj));
			}
		}
		return map;
	}*/

	/**
	 * 首字母大写
	 * @param	str
	 * @return	String
	 */
	public static String firstUpCase(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 把vo封装为map
	 * @param	obj
	 * @return	map
	 */
	public static Map<String, Object> transBean2Map(Object obj) throws Exception{
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			// 过滤class属性
			if (!key.equals("class")) {
				// 得到property对应的getter方法
				Method getter = property.getReadMethod();
				Object value = getter.invoke(obj);
				map.put(key, value);
			}
		}
		return map;
	}

	/**
	 * 把map转为bean
	 * @param	map
	 * @return
	 * @throws Exception
	 */
	public static <T> T transMap2Bean(Map<?, ?> map, Class<T> clazz) throws Exception{
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz); // 获取类属性
		T entity = clazz.newInstance(); // 创建 JavaBean 对象
        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (map.containsKey(propertyName)) {
                Object value = map.get(propertyName);
                descriptor.getWriteMethod().invoke(entity, value);
            }
        }
		return entity;
	}

	/**
	 * 判断bean中是否有空值
	 * @param:	clazz
	 * @return	boolean
	 * @throws	Exception
	 */
	public static boolean judgeBeanHaveNullValue(Object obj) throws Exception{
		boolean bool = true;
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			// 过滤class属性
			if (!key.equals("class")) {
				// 得到property对应的getter方法
				Method getter = property.getReadMethod();
				Object value = getter.invoke(obj);
				if(value == null){
					bool = false;
					break;
				}
			}
		}
		return bool;
	}

	/**
	 * 数字转中文
	 * @param	number
	 * @return	String
	 */
	public static String number2chinese(int number) throws Exception{
		String[] str = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		String ss[] = new String[] { "层", "十", "百", "千", "万", "十", "百", "千", "亿" };
		String s = String.valueOf(number);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
		    String index = String.valueOf(s.charAt(i));
		    sb = sb.append(str[Integer.parseInt(index)]);
		}
		String sss = String.valueOf(sb);
		int i = 0;
		for (int j = sss.length(); j > 0; j--) {
			sb = sb.insert(j, ss[i++]);
		}
		if(sb.length() > 1){
			sb = new StringBuffer(sb.toString().replace("零", ""));
		}
		if(sb.indexOf("一") == 0 && sb.length() > 2){
			sb = new StringBuffer(sb.replace(0, 1, ""));
		}
		return sb.toString();
	}

	/**
	 * 把json转为list
	 * @param	str
	 * @return	list
	 */
	public static List<Map<String, Object>> json2list(String str) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Object object : JSONObject.parseArray(str)) {
			list.add(json2map(JSONObject.toJSONString(object)));
		}
		return list;
	}

	/**
	 * json转map的list处理
	 * @param	jsonArray
	 * @return	list
	 */
	static List<Map<String, Object>> handleJSONArray(JSONArray jsonArray){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Object object : jsonArray) {
			JSONObject jsonObject = (JSONObject) object;
			Map<String, Object> map = new HashMap<String, Object>();
			Set<String> keySet = jsonObject.keySet();
			for (String key : keySet) {
				//如果得到的值是JSONArray
				if(jsonObject.get(key) instanceof JSONArray){
					map.put(key, handleJSONArray((JSONArray)jsonObject.get(key)));
				}else{
					map.put(key, jsonObject.get(key));
				}
			}
			list.add(map);
		}
		return list;
	}


	/**
	 * 把json转为map
	 * @param	str
	 * @return	map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2map(String str) {
		Map<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> jsonObject = JSONObject.parseObject(str, HashMap.class);
		Set<String> keySet = jsonObject.keySet();
		for (String key : keySet) {
			//如果得到的值是JSONArray，则把该值放入list，并把list存入map，map的键值为json中的键
			if(jsonObject.get(key) instanceof JSONArray){
				List<Map<String, Object>> list = handleJSONArray((JSONArray)jsonObject.get(key));
				map.put(key, list);
			}
			//如果得到的值是JSONArray，则把该值放入map，map的键值为json中的键
			else{
				map.put(key, jsonObject.get(key));
			}
		}
		return map;
	}

	/**
	 * @Author: zhangchu@john.com
	 * @Description:将List<Map>对象转化为List<JavaBean>此方法已通过测试
	 * @Date: 15:38 2018/2/13
	 * @Modified By:
	 */
/*	public static <T> List<T> convertListMap2ListBean(List<Map<String,Object>> listMap, Class<T> T){
		List<T> beanList=new ArrayList<T>();
		try {
			for(int i=0, n=listMap.size(); i<n; i++){
				Map<String,Object> map = listMap.get(i);
				T bean = transMap2Bean(map,T);
				beanList.add(bean);
			}
		} catch (Exception e) {
			throw new BizException(ExceptionCode.REQUEST_FAIL.getCode(),ExceptionCode.REQUEST_FAIL.getMsg()+"异常信息：服务异常！"+e.getMessage());
		}
		return beanList;
	}*/
	/**
	 * @Author:  zhangrui@john.com
	 * @Description: getRandomString    生成随机字符串
	 * @Params: [length]
	 * @param:
	 * @Return: java.lang.String
	 * @Throws:
	 * @Date: 2018/6/8 18:24
	 */
	public static String getRandomString(int length) {

		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";//含有字符和数字的字符串
		Random random = new Random();//随机类初始化
		StringBuffer sb = new StringBuffer();//StringBuffer类生成，为了拼接字符串

		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(62);// [0,62)

			sb.append(str.charAt(number));
		}
		return sb.toString();
	}
	/**
	 * @Author: zhangrui@john.com
	 * @Description:判断对象是否为空
	 * @Params:
	 *   @param: obj
	 * @Return: boolean
	 * @Throws:
	 * @Date: Created in 11:20 2018/7/23
	 */
	public static boolean isNullOrEmpty(Object obj) {

		if (obj == null)
			return true;

		if (obj instanceof CharSequence)
			return ((CharSequence) obj).length() == 0;

		if (obj instanceof Collection)
			return ((Collection) obj).isEmpty();

		if (obj instanceof Map)
			return ((Map) obj).isEmpty();

		if (obj instanceof Object[]) {
			Object[] object = (Object[]) obj;
			if (object.length == 0) {
				return true;
			}
			boolean empty = true;
			for (int i = 0; i < object.length; i++) {
				if (!isNullOrEmpty(object[i])) {
					empty = false;
					break;
				}
			}
			return empty;
		}
		return false;
	}

	public static String getMapString(Map<String,Object> map)
	{
		String result="";
		Set<String> keySet = map.keySet();
		for (String string : keySet) {
			result+=string + ":" + map.get(string)+",";
			System.out.println(string + ":" + map.get(string));
		}
		return result;
	}


}
