package com.rabbiter.dm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @author xianxian
 * @date 2022-02-09
 */
public class DepartApplicationUser {

    private Long id;

    private Long operateUserId;

    private SystemUser operateUser;

    private Boolean isAgree;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime operateTime;

    private String reason;

    private Long applicationId;

    private DepartApplication application;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Long operateUserId) {
        this.operateUserId = operateUserId;
    }

    public SystemUser getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(SystemUser operateUser) {
        this.operateUser = operateUser;
    }

    public Boolean getIsAgree() {
        return isAgree;
    }

    public void setAgree(Boolean agree) {
        isAgree = agree;
    }

    public LocalDateTime getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(LocalDateTime operateTime) {
        this.operateTime = operateTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public DepartApplication getApplication() {
        return application;
    }

    public void setApplication(DepartApplication application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "DepartApplicationUser{" +
                "id=" + id +
                ", operateUserId=" + operateUserId +
                ", operateUser=" + operateUser +
                ", isAgree=" + isAgree +
                ", operateTime=" + operateTime +
                ", reason='" + reason + '\'' +
                ", applicationId=" + applicationId +
                ", application=" + application +
                '}';
    }
}
