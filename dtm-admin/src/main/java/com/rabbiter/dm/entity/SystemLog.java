package com.rabbiter.dm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * @author xianxian
 * @date 2021-12-29
 */
public class SystemLog {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operateTime;

    private Long userId;

    private String clas;

    private String method;

    private String ip;

    private String param;

    private String url;

    private String result;

    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operateTimeStart;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operateTimeEnd;

    private SystemUser systemUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(LocalDateTime operateTime) {
        this.operateTime = operateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getOperateTimeStart() {
        return operateTimeStart;
    }

    public void setOperateTimeStart(LocalDateTime operateTimeStart) {
        this.operateTimeStart = operateTimeStart;
    }

    public LocalDateTime getOperateTimeEnd() {
        return operateTimeEnd;
    }

    public void setOperateTimeEnd(LocalDateTime operateTimeEnd) {
        this.operateTimeEnd = operateTimeEnd;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    @Override
    public String toString() {
        return "SystemLog{" +
                "id=" + id +
                ", operateTime=" + operateTime +
                ", userId=" + userId +
                ", clas='" + clas + '\'' +
                ", method='" + method + '\'' +
                ", ip='" + ip + '\'' +
                ", param='" + param + '\'' +
                ", url='" + url + '\'' +
                ", result='" + result + '\'' +
                ", description='" + description + '\'' +
                ", operateTimeStart=" + operateTimeStart +
                ", operateTimeEnd=" + operateTimeEnd +
                ", systemUser=" + systemUser +
                '}';
    }
}
