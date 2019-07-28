package com.john.www.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author:""
 * @Description: 操作ftp的工具类
 * @Date: 15:04 2018/8/3
 * @Modified By:
 */

@Component
public class FtpUtil {

    private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    /**
     * Ftp地址
     */
    private static String ftpIp;

    @Value("${ftp.ip}")
    public void setFtpIp(String ftpIp) {
        FtpUtil.ftpIp = ftpIp;
    }

    /**
     * Ftp端口
     */
    private static String ftpPort;

    @Value("${ftp.port}")
    public void setFtpPort(String ftpPort) {
        FtpUtil.ftpPort = ftpPort;
    }


    /**
     * Ftp用户名
     */
    private static String ftpUserName;

    @Value("${ftp.username}")
    public void setFtpUserName(String ftpUserName) {
        FtpUtil.ftpUserName = ftpUserName;
    }

    /**
     * Ftp密码
     */
    private static String ftpPassword;

    @Value("${ftp.password}")
    public void setFtpPassword(String ftpPassword) {
        FtpUtil.ftpPassword = ftpPassword;
    }

    /**
     * 上传文件的路径
     */
    private static String ftpPath;

    @Value("${ftp.path}")
    public void setFtpPath(String ftpPath) {
        FtpUtil.ftpPath = ftpPath;
    }


    public static FTPClient ftpLogin() throws Exception {
        FTPClient ftpClient = new FTPClient();
        //打开ftp链接
        ftpClient.connect(ftpIp, Integer.valueOf(ftpPort));
        //登录ftp
        ftpClient.login(ftpUserName, ftpPassword);
        int iReply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(iReply)) {
            ftpClient.disconnect();
        }
        return ftpClient;
    }

    public static String getFtpPath() {
        StringBuffer ftpLocation = new StringBuffer("http://");
        ftpLocation.append(ftpIp);
        ftpLocation.append(":8080/fileUrl/");
        ftpLocation.append(ftpPath);
        return ftpLocation.toString();
    }


    /**
     * 功能描述: 在ftp上创建文件夹
     *
     * @param ftpClient  ftp链接
     * @param folderName 文件夹名
     * @author:""
     * @return:
     * @date: Created in 16:19 2018/9/6
     */
    public static void createFolder(FTPClient ftpClient, String folderName) throws Exception {
        if (!ftpClient.changeWorkingDirectory(folderName)) {
            //在ftp上创建文件夹
            ftpClient.makeDirectory(folderName);
            //切换ftp工作目录
            ftpClient.changeWorkingDirectory(folderName);
        }
    }

    /**
     * 功能描述: ftp退出
     *
     * @author:""
     * @param ftpClient ftp链接
     * @return:
     * @date: Created in 8:57 2018/9/7
     */
    public static void ftpExit(FTPClient ftpClient) throws Exception {
        if (ftpClient.isConnected()) {
            //退出ftp
            ftpClient.logout();
            //断开连接
            ftpClient.disconnect();
        }
    }


}
