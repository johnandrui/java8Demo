package com.john.www.model.fileModel;

/**
 * @ProjectName: biss-parent
 * @Author： @iyungu.com
 * @Description:
 * @Date 2019/4/12  11:53
 * @Modified By:
 */
public class FileInfo {
    /**
     * 视频地址
     */
    private String fileUrl;

    /**
     * 视频时间（单位:s,只有视频文件有）
     */
    private long videoTime;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public long getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(long videoTime) {
        this.videoTime = videoTime;
    }

    public FileInfo(String fileUrl, long videoTime) {
        this.fileUrl = fileUrl;
        this.videoTime = videoTime;
    }

    public FileInfo(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
