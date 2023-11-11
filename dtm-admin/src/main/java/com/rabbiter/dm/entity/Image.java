package com.rabbiter.dm.entity;

import java.time.LocalDateTime;

/**
 * @author XiongXinxin
 * @date 2022-03-19
 */
public class Image {
    private Long id;

    private String saveName;

    private String originalName;

    private String md5;

    private LocalDateTime uploadTime;

    private Long uploadUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(Long uploadUser) {
        this.uploadUser = uploadUser;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", saveName='" + saveName + '\'' +
                ", originalName='" + originalName + '\'' +
                ", md5='" + md5 + '\'' +
                ", uploadTime=" + uploadTime +
                ", uploadUser=" + uploadUser +
                '}';
    }
}
