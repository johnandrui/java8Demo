package com.john.www.util;


import com.john.www.model.exception.BusinessException;
import com.john.www.model.result.ResultCode;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: ""
 * @Description: 上传文件的公共方法
 * @Date: 14:07 2018/8/3
 * @Modified By:
 */
@Component
public class UploadFilesUtil {

    private static String uploadPath;

    /**
     * 功能描述:
     *
     * @param file 将文件上传到本地临时文件夹
     * @author:""
     * @return: 返回本地文件
     * @date: Created in 16:55 2018/8/10
     */
    public static File uploadFileToLocalNurse(MultipartFile file,String picName) throws Exception {
        //判断文件不为空
        if (file.isEmpty()) {
            throw new BusinessException(ResultCode.SYS_FILE_EMPTY);
        }

        //文件名
        String fileName=file.getOriginalFilename();
//        //文件后缀,如 .jpg
//        String suffix = fileName.substring(fileName.lastIndexOf("."));
//        //文件名称
//        fileName = StringUtils.isEmpty(picName)==true?file.getOriginalFilename():picName+suffix;
        //创建新的文件
        File localFile = createFileNurse(fileName);
        //将数据写入文件
        file.transferTo(localFile);
        return localFile;
    }
    /**
     * 功能描述: 根据文件名在本地创建一个空白文件
     *
     * @param fileName 文件名称-名称+后缀名
     * @author:""
     * @return:
     * @date: Created in 17:31 2018/9/5
     */
    public static File createFileNurse(String fileName) {
        //将文件上传到本地的路径
        if (org.apache.commons.lang3.StringUtils.isEmpty(fileName)) {
            throw new BusinessException(ResultCode.SYS_FILE_NAME_EMPTY);
        }
        logger.info("********************************" + uploadPath + "/nurse");
        //创建新的文件
        File tempDirFile = new File(uploadPath + "/nurse");
        if (!tempDirFile.exists()) {
            tempDirFile.mkdirs();
        }

        //文件名,如 李佳
        String name = fileName.substring(0, fileName.indexOf("."));
        //文件后缀,如 .jpg
        String suffix = fileName.substring(fileName.lastIndexOf("."));

        //目标文件
        File localFile = new File(uploadPath + "/nurse/" + fileName);
        int i = 1;
        //若文件存在重命名
        while (localFile.exists()) {
            localFile.delete();
            localFile = new File(uploadPath + "/nurse/" + fileName);
        }
        return localFile;
    }

    /**
     * 功能描述: 将本地文件上传到ftp服务器.固定文件夹，不生成日期文件夹，不改变文件名
     *
     * @param file   文件
     * @param folder 文件夹名称
     * @author: zhangchu@iyungu.com
     * @return: 返回上传后的地址
     * @date: Created in 14:09 2018/8/3
     */
    public static String upLoadFileToNurse(File file, String folder, String picName) throws Exception {
        StringBuffer imgUrl = new StringBuffer();
        //文件名称.文件名+后缀名
        String fileName = file.getName();
        //文件后缀,如 .jpg
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //文件名称。文件名+后缀名
        fileName = StringUtils.isEmpty(picName) == true ? fileName : picName + suffix;
        //中文转换后的目录名或文件名。
        String fileNameGbk = new String(fileName.getBytes("GBK"), "iso-8859-1");

        //登录ftp并返回ftp连接
        FTPClient ftpClient = FtpUtil.ftpLogin();
        //设置文件上传类型
        ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.setControlEncoding("gbk");
        //设置缓冲区
        ftpClient.setBufferSize(102400);
        FtpUtil.createFolder(ftpClient, folder);
        //将文件写入ftp
        FileInputStream fis = new FileInputStream(file);
        ftpClient.storeFile(fileNameGbk, fis);
        fis.close();
        FtpUtil.ftpExit(ftpClient);
        //删除本地文件
        file.delete();
        imgUrl.append(FtpUtil.getFtpPath());
        imgUrl.append(folder);
        imgUrl.append("/");
        imgUrl.append(fileName);
        return imgUrl.toString();
    }

    @Value("${upload.path}")
    public  void setUploadPath(String uploadPath) {
        UploadFilesUtil.uploadPath = uploadPath;
    }

    private static Logger logger = LoggerFactory.getLogger(UploadFilesUtil.class);

    /**
     * 功能描述: 将本地文件上传到ftp服务器
     *
     * @param file 文件
     * @author:""
     * @return: 返回上传后的地址
     * @date: Created in 14:09 2018/8/3
     */
    public static String upLoadFileToFtp(File file) throws Exception {
        StringBuffer imgUrl = new StringBuffer();
        //文件名称
        String fileName = getFileNameByTime(file);
        //文件夹名称
        String folderName = getFolderNameByTime();
        //登录ftp并返回ftp连接
        FTPClient ftpClient = FtpUtil.ftpLogin();
        //设置文件上传类型
        ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //设置缓冲区
        ftpClient.setBufferSize(102400);
        FtpUtil.createFolder(ftpClient, folderName);
        //将文件写入ftp
        FileInputStream fis = new FileInputStream(file);
        ftpClient.storeFile(fileName, fis);
        fis.close();
        FtpUtil.ftpExit(ftpClient);
        //删除本地文件
        file.delete();
        imgUrl.append(FtpUtil.getFtpPath());
        imgUrl.append(folderName);
        imgUrl.append("/");
        imgUrl.append(fileName);
        return imgUrl.toString();
    }


    /**
     * 功能描述: 将本地文件上传到ftp服务器
     *
     * @param file   文件
     * @param folder 文件夹名称
     * @author:""
     * @return: 返回上传后的地址
     * @date: Created in 14:09 2018/8/3
     */
    public static String upLoadFileToFtp(File file, String folder) throws Exception {
        StringBuffer imgUrl = new StringBuffer();
        //文件名称
        String fileName = getFileNameByTime(file);
        //文件夹名称
        String folderName = getFolderNameByTime();
        //登录ftp并返回ftp连接
        FTPClient ftpClient = FtpUtil.ftpLogin();
        //设置文件上传类型
        ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //设置缓冲区
        ftpClient.setBufferSize(102400);
        FtpUtil.createFolder(ftpClient, folder);
        FtpUtil.createFolder(ftpClient, folderName);
        //将文件写入ftp
        FileInputStream fis = new FileInputStream(file);
        ftpClient.storeFile(fileName, fis);
        fis.close();
        FtpUtil.ftpExit(ftpClient);
        //删除本地文件
        file.delete();
        imgUrl.append(FtpUtil.getFtpPath());
        imgUrl.append(folder);
        imgUrl.append("/");
        imgUrl.append(folderName);
        imgUrl.append("/");
        imgUrl.append(fileName);
        return imgUrl.toString();
    }

    /**
     * 功能描述: 将本地文件上传到ftp服务器
     *
     * @param file   文件
     * @param folder 文件夹名称
     * @param fileName 文件名称
     * @author:""
     * @return: 返回上传后的地址
     * @date: Created in 14:09 2018/8/3
     */
    public static String upLoadFileToFtp(File file, String folder,String fileName) throws Exception {
        StringBuffer imgUrl = new StringBuffer();
        //文件夹名称
        String folderName = getFolderNameByTime();
        //登录ftp并返回ftp连接
        FTPClient ftpClient = FtpUtil.ftpLogin();
        //设置文件上传类型
        ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.setControlEncoding("gbk");
        //设置缓冲区
        ftpClient.setBufferSize(102400);
        FtpUtil.createFolder(ftpClient, folder);
        FtpUtil.createFolder(ftpClient, folderName);
        //将文件写入ftp
        FileInputStream fis = new FileInputStream(file);
        ftpClient.storeFile(fileName, fis);
        fis.close();
        FtpUtil.ftpExit(ftpClient);
        //删除本地文件
        file.delete();
        imgUrl.append(FtpUtil.getFtpPath());
        imgUrl.append(folder);
        imgUrl.append("/");
        imgUrl.append(folderName);
        imgUrl.append("/");
        imgUrl.append(fileName);
        return imgUrl.toString();
    }


    /**
     * 功能描述: 根据当前时间获取文件夹名称
     *
     * @param
     * @author:""
     * @return: java.lang.String
     * @date: Created in 14:22 2018/8/3
     */

    public static String getFolderNameByTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 功能描述:根据时间获取文件名
     *
     * @param file 上传的文件
     * @author:""
     * @return:
     * @date: Created in 14:30 2018/8/3
     */
    public static String getFileNameByTime(File file) {
        String fileName = file.getName();
        return System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 获取文件类型
     *
     * @param file
     * @return
     */
    public static String getFileType(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 功能描述:根据时间获取文件名
     *
     * @param file 上传的文件
     * @author:""
     * @return:
     * @date: Created in 14:30 2018/8/3
     */
    public static String getFileNameByTime(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
    }


    /**
     * 功能描述:
     *
     * @param file 将文件上传到本地临时文件夹
     * @author:""
     * @return: 返回本地文件
     * @date: Created in 16:55 2018/8/10
     */
    public static File uploadFileToLocal(MultipartFile file) throws Exception {
        //判断文件不为空
        if (file.isEmpty()) {
            throw new BusinessException(ResultCode.SYS_FILE_EMPTY);
        }
        //文件名称
        String fileName = getFileNameByTime(file);
        //创建新的文件
        File localFile = createFile(fileName);
        //将数据写入文件
        file.transferTo(localFile);
        return localFile;
    }

    /**
     * 功能描述: 根据文件名在本地创建一个空白文件
     *
     * @param
     * @author:""
     * @return:
     * @date: Created in 17:31 2018/9/5
     */
    public static File createFile(String fileName)  {
        //将文件上床到本地的路径
        if (org.apache.commons.lang3.StringUtils.isEmpty(fileName)) {
            throw new BusinessException(ResultCode.SYS_FILE_NAME_EMPTY);
        }
        logger.info("********************************"+uploadPath);
        //创建新的文件
        File tempDirFile = new File(uploadPath);
        if (!tempDirFile.exists()) {
            tempDirFile.mkdirs();
        }
        File localFile = new File(uploadPath + "/" + fileName);
        return localFile;
    }

    /**
     * 功能描述: 将文件夹及其下面的所有文件上传到ftp
     *
     * @param
     * @author:""
     * @return:
     * @date: Created in 11:29 2018/8/23
     */
    public static String uploadDirToFtp(File file,String folder) throws Exception {
        //上传文件夹的路径
        StringBuffer fileUrl = new StringBuffer();
        //文件夹名称
        String folderName = getFolderNameByTime();
        //登录ftp并返回ftp连接
        FTPClient ftpClient = FtpUtil.ftpLogin();
        //设置文件上传类型
        ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //设置缓冲区
        ftpClient.setBufferSize(102400);
        FtpUtil.createFolder(ftpClient,folder);
        FtpUtil.createFolder(ftpClient,folderName);
        upLoadDir(ftpClient, file);
        if (ftpClient.isConnected()) {
            //退出ftp
            ftpClient.logout();
            //断开连接
            ftpClient.disconnect();
        }
        fileUrl.append(FtpUtil.getFtpPath());
        fileUrl.append(folder);
        fileUrl.append("/");
        fileUrl.append(folderName);
        fileUrl.append("/");
        fileUrl.append(file.getName());
        fileUrl.append("/");
        return fileUrl.toString();
    }



    /**
     * 功能描述: 将文件夹及其下面的所有文件上传到ftp
     *
     * @param
     * @author:""
     * @return:
     * @date: Created in 11:29 2018/8/23
     */
    public static String uploadDirToFtp(File file) throws Exception {
        //上传文件夹的路径
        StringBuffer fileUrl = new StringBuffer();
        //文件夹名称
        String folderName = getFolderNameByTime();
        //登录ftp并返回ftp连接
        FTPClient ftpClient = FtpUtil.ftpLogin();
        //设置文件上传类型
        ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //设置缓冲区
        ftpClient.setBufferSize(102400);
        FtpUtil.createFolder(ftpClient,folderName);
        upLoadDir(ftpClient, file);
        if (ftpClient.isConnected()) {
            //退出ftp
            ftpClient.logout();
            //断开连接
            ftpClient.disconnect();
        }
        fileUrl.append(FtpUtil.getFtpPath());
        fileUrl.append(folderName);
        fileUrl.append("/");
        fileUrl.append(file.getName());
        fileUrl.append("/");
        return fileUrl.toString();
    }

    /**
     * 功能描述: 上传文件夹
     *
     * @param ftpClient ftp链接
     * @param dirFile   上传的文件夹
     * @author:""
     * @return:
     * @date: Created in 12:09 2018/8/23
     */
    public static void upLoadDir(FTPClient ftpClient, File dirFile) throws Exception {
        File[] fileList = dirFile.listFiles();
        if (!ftpClient.changeWorkingDirectory(dirFile.getName())) {
            //在ftp上创建文件夹
            ftpClient.makeDirectory(dirFile.getName());
            //切换ftp工作目录
            ftpClient.changeWorkingDirectory(dirFile.getName());
        }
        for (File file : fileList) {
            if (file.isDirectory()) {
                upLoadDir(ftpClient, file);
            } else {
                upLoadFile(ftpClient, file);
            }
        }
        dirFile.delete();
    }

    /**
     * 功能描述: 上传文件
     *
     * @param ftpClient ftp链接
     * @param file      上传的文件
     * @author:""
     * @return:
     * @date: Created in 12:09 2018/8/23
     */
    public static void upLoadFile(FTPClient ftpClient, File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        ftpClient.storeFile(file.getName(), fis);
        fis.close();
        file.delete();
    }
}
