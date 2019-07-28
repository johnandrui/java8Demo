package com.john.www.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

/**
 * @Author: zhang@self.com
 * @Description: upLoadFiles
 * @Date: 9:12 2019/7/28
 * @Modified By:
 */
@Component
public class UploadOssUtils {

    //阿里云服务器的终端名称
    private final static String aliyun_oss_endpoint = "";
    //服务器id相当于登录的用户名
    private final static String aliyun_oss_accessKeyId = "";
    //密钥
    private final static String aliyun_oss_accessKeySecret = "";
    //阿里云服务器的bucket值  都可以在注册号的阿里云服务器上得到
    private final static String aliyun_oss_sourceBucketName = "";

    Logger logger = LoggerFactory.getLogger(UploadOssUtils.class);

    private OSSClient client = null;

    @Autowired
    OssUrlUtil ossUrlUtil;

    public String uploadToOss(File file, String folder) throws Exception {
        String folderName = getFolderNameByTime();
        String path = folder + folderName + "/" + file.getName();
        InputStream is = new FileInputStream(file);
        uploadfiletoali(is, path);
        is.close();
        String url = ossUrlUtil.getFileUrl(path);
        file.delete();
        return url;
    }

    /**
     * 功能描述: 将文件上传到oss服务器
     *
     * @param file     上传的文件
     * @param folder   存放路径
     * @param fileName 上传文件的命名
     * @author:""
     * @return:
     * @date: Created in 15:29 2019/5/22
     */
    public String uploadToOss(File file, String folder, String fileName) throws Exception {
        String folderName = getFolderNameByTime();
        String path = folder + folderName + "/" + fileName;
        InputStream is = new FileInputStream(file);
        uploadfiletoali(is, path);
        is.close();
        String url = ossUrlUtil.getFileUrl(path);
        file.delete();
        return url;
    }

    /**
     * 功能描述: 将文件夹上传到oss服务器
     *
     * @param file   上传的文件
     * @param folder 存放路径
     * @author:""
     * @return:
     * @date: Created in 15:29 2019/5/22
     */
    public String uploadDirToOss(File file, String folder)throws Exception {
        String folderName = getFolderNameByTime();
        String path = folder + folderName + "/" + file.getName() + "/";
        client = new OSSClient(aliyun_oss_endpoint, aliyun_oss_accessKeyId, aliyun_oss_accessKeySecret);
        upLoadDir(file, path);
        String url = ossUrlUtil.getFileUrl(path);
        return url;
    }


    /**
     * @Author: wangyarui@iyungu.com
     * @Description:上传文件-上传到阿里云服务器上
     * @Date: Created in 11:26 2018/6/8
     * @Param:
     * @Return:
     */
    public String imageUpload(HttpServletRequest request) throws Exception {
        //获取图片上传名称
        String path = "app/imgs/";
        String url = "";
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                //文件名
                String myFileName = "";
                //文件扩展名
                String myFileType = "";
                if (null != file) {
                    //取得当前上传文件的文件名称
                    myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        myFileType = myFileName.substring(myFileName.lastIndexOf(".") + 1, myFileName.length());
                        //重命名上传后的文件名
//                        String fileName = RandomUtil.simpleUUID();
                        String fileName = new Random().nextInt()+"";
                        String fileFullName = fileName + "." + myFileType;
                        InputStream is = file.getInputStream(); //photo为客户端上传的File类型文件
                        path = path + fileFullName;
                        uploadfiletoali(is, path); //path为图片名称
                        is.close();
                        /**2016-12-21修改图片路径为相对路径**/
                        url = ossUrlUtil.getFileUrl(path);
                        /**2016-12-21修改图片路径为相对路径**/
                    }
                }
            }
        }
        return url;
    }


    /**
     * 上传文件到阿里云服务器
     *
     * @param is     上传文件的文件流
     * @param objKey 文件的存储路径
     * @throws IOException
     */
    public void uploadfiletoali(InputStream is, String objKey) throws IOException {
        /*
         * Constructs a client instance with your account for accessing OSS
         */
        //String code = Constants.SYS_CONFIG_IMG_SERVER_ADDRESS;
        //endpoint = PropertiesUtil.getProperty("config/systemConfig.properties",code);
//        client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        client = new OSSClient(aliyun_oss_endpoint, aliyun_oss_accessKeyId, aliyun_oss_accessKeySecret);

        try {

            //System.out.println("Uploading a new object to OSS from a file\n");
            //文件上传
            //PutObjectResult   ss= client.putObject(sourceBucketName, objKey, is);
            client.putObject(aliyun_oss_sourceBucketName, objKey, is);
            // PutObjectResult   ss= client.putObject(new PutObjectRequest(sourceBucketName, objKey, createSampleFile()));

            //System.out.println(ss.getCallbackResponseBody());


        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, " + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered " + "a serious internal problem while trying to communicate with OSS, " + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            client.shutdown();
        }
    }

    /**
     * 删除阿里云服务器指定路径下的文件
     *
     * @param objKey 根目录下的文件路径
     */
    public void deltefiletoali(String objKey) {
//        client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        client = new OSSClient(aliyun_oss_endpoint, aliyun_oss_accessKeyId, aliyun_oss_accessKeySecret);
        try {
            //删除文件操作
            client.deleteObject(aliyun_oss_sourceBucketName, objKey);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, " + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered " + "a serious internal problem while trying to communicate with OSS, " + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            client.shutdown();
        }
    }

    public static String getFolderNameByTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(new Date());
    }


    /**
     * 功能描述: 上传文件夹
     *
     * @param dirFile 上传的文件夹
     * @param path    上传的路径
     * @author:""
     * @return:
     * @date: Created in 12:09 2018/8/23
     */
    public void upLoadDir(File dirFile, String path)throws Exception {
        File[] files = dirFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                //如果是文件夹，改变上传路径
                upLoadDir(file, path + file.getName() + "/");
            } else {
                //如果是文件，直接上传
                InputStream is = new FileInputStream(file);
                uploadfiletoali(is, path+file.getName());
                is.close();
                file.delete();
            }
        }
        dirFile.delete();
    }



}
