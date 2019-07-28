package com.john.www.action.upLoad;

import com.john.www.model.apk.ApkInfoVo;
import com.john.www.model.fileModel.FileInfo;
import com.john.www.model.fileModel.FileType;
import com.john.www.model.result.Result;
import com.john.www.model.result.ResultCode;
import com.john.www.util.UploadFilesUtil;
import com.john.www.util.UploadOssUtils;
import com.john.www.util.UrlUtil;
import com.john.www.util.WordHtmlTransitionUtils;
import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhang@self.com
 * @Description: aliyun-upload
 * @Date: 9:29 2019/7/28
 * @Modified By:
 */
@RestController
@RequestMapping("/upLoadOss")
public class UpLoadOssController {

    private static Logger logger = LoggerFactory.getLogger(UpLoadOssController.class);

    @Autowired
    UploadOssUtils uploadFilesUtil;

    @PostMapping("file/upLoad")
    public Result imagesUpload(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) throws Exception {

        List<String> urls = new ArrayList<String>();
        if (files != null) {
            String url;

            for (int i = 0; i < files.length; i++) {
                logger.debug("开始上传文件" + i + "；入参：【files[" + i + "]：" + files[i].getOriginalFilename() + "， request：" + request + "】");
                url = uploadFilesUtil.imageUpload(request);
                url = UrlUtil.remove(url);
                urls.add(url);
                logger.debug("结束上传文件" + i + "：imageUpload()；出参：【url：" + url + "】");
            }
        }

        return Result.success(urls);
    }

    /**
     * 功能描述: 上传视频到ftp
     *
     * @param files 上传的文件
     * @author:""
     * @return:
     * @date: Created in 15:23 2018/8/10
     */
    @PostMapping("video/ftp")
    public Result upLoadVideoToFtp(MultipartFile[] files) throws Exception {
        logger.info("开始上传视频");
        long videoTime = 30;
        List<FileInfo> fileInfoList = new ArrayList<>();
        for (MultipartFile file : files) {
            //先将文件上传到本地
            File localFile = UploadFilesUtil.uploadFileToLocal(file);
            String fileUrl = uploadFilesUtil.uploadToOss(localFile, "video/");
            fileInfoList.add(new FileInfo(fileUrl, videoTime));
        }
        logger.info("上传视频结束");
        return Result.success(fileInfoList);
    }

    @PostMapping("word/html/ftp")
    public Result upLoadWordToFtp(MultipartFile[] files) throws Exception {
        logger.info("开始上传word文件");
        List<FileInfo> fileInfoList = new ArrayList<>();
        File localDir;
        for (MultipartFile file : files) {
            //先将文件上传到本地
            File localFile = UploadFilesUtil.uploadFileToLocal(file);
            String fileType = UploadFilesUtil.getFileType(file);
            //将word文件转为html
            if (FileType.DOC.getName().equals(fileType)) {
                localDir = WordHtmlTransitionUtils.docToHtml(localFile);
            } else {
                localDir = WordHtmlTransitionUtils.docxToHtml(localFile);
            }
            String fileUrl = uploadFilesUtil.uploadDirToOss(localDir, "html/");
            fileUrl += localDir.getName() + ".html";
            fileInfoList.add(new FileInfo(fileUrl));
        }
        logger.info("上传word文件结束");
        return Result.success(fileInfoList);
    }

    /**
     * 功能描述: 上传图片并进行压缩
     *
     * @param files 上传的文件
     * @author:""
     * @return: com.iyungu.www.core.Result
     * @date: Created in 14:00 2018/8/3
     */
    @PostMapping("img/ftp")
    public Result upLoadImgToFtp(MultipartFile[] files) throws Exception {
        logger.info("开始上传图片");
        List<FileInfo> fileInfoList = new ArrayList<>();
        if (files.length <= 0) {
            return new Result(ResultCode.SYS_FILE_EMPTY);
        }
        for (MultipartFile file : files) {
            //先将文件上传到本地
            File localFile = UploadFilesUtil.uploadFileToLocal(file);
            //将文件上传到ftp
            //获取图片大小
            Long localFileSize = localFile.length() / 1024000;
            Long maxImageSize = 3L;
            //记录图片上传地址
            String fileUrl;
            if (localFileSize >= maxImageSize) {
                //压缩后的文件
                File reduceFile = UploadFilesUtil.createFile(UploadFilesUtil.getFileNameByTime(localFile));
//                ImageUtil.scale(localFile, reduceFile, (float) 2 / localFileSize);
                localFile.delete();
                fileUrl = uploadFilesUtil.uploadToOss(reduceFile, "img/");
            } else {
                fileUrl = uploadFilesUtil.uploadToOss(localFile, "img/");
            }
            fileInfoList.add(new FileInfo(fileUrl));
        }
        logger.info("上传图片结束");
        return Result.success(fileInfoList);
    }

    /**
     * 功能描述: 上传apk
     *
     * @param files 上传的文件
     * @author:""
     * @return: com.iyungu.www.core.Result
     * @date: Created in 14:00 2018/8/3
     */
    @PostMapping("apk/ftp")
    public Result upLoadApkToFtp(MultipartFile[] files) throws Exception {
        logger.info("开始上传apk");
        List<ApkInfoVo> fileInfoList = new ArrayList<>();
        if (files.length <= 0) {
            return new Result(ResultCode.SYS_FILE_EMPTY);
        }
        for (MultipartFile file : files) {
            //先将文件上传到本地
            File localFile = UploadFilesUtil.uploadFileToLocal(file);
            //将文件上传到文件服务器
            ApkInfoVo apkInfo = new ApkInfoVo();
            ApkFile apkFile = new ApkFile(localFile);
            ApkMeta apkMeta = apkFile.getApkMeta();
            BeanUtils.copyProperties(apkMeta, apkInfo);
            StringBuffer apkName = new StringBuffer();
            apkName.append(apkMeta.getPackageName());
            apkName.append("_v_");
            apkName.append(apkMeta.getVersionCode());
            apkName.append("_");
            apkName.append(UploadFilesUtil.getFolderNameByTime());
            apkName.append(".apk");
            String fileUrl = uploadFilesUtil.uploadToOss(localFile, "apk/", apkName.toString());
            apkInfo.setFileUrl(fileUrl);
            fileInfoList.add(apkInfo);
        }
        logger.info("上传apk结束");
        return Result.success(fileInfoList);
    }



}
