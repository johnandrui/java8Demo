package com.john.www.main.base64;

import org.apache.xmlbeans.soap.SOAPArrayType;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  11:25
 * @Modified By:
 */
public class Baser64Test {
    public static void main(String[] args) {
          final String text="Lets Learn Java 8!";
          final String encoded= Base64
                  .getEncoder()
                  .encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);
        final String decoded=new String(Base64.getDecoder()
        .decode(encoded),StandardCharsets.UTF_8);
        System.out.println(decoded);
    }
}
