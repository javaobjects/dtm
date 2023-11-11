package com.rabbiter.dm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xianxian
 * @date 2022-02-04
 */
public class Notice {
    private Long id;

    private String msg;

    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    private Boolean isRead;

    private SystemUser user;

    private List<Notice> receiveUsers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }

    public List<Notice> getReceiveUsers() {
        return receiveUsers;
    }

    public void setReceiveUsers(List<Notice> receiveUsers) {
        this.receiveUsers = receiveUsers;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", userId=" + userId +
                ", time=" + time +
                ", isRead=" + isRead +
                ", user=" + user +
                ", receiveUsers=" + receiveUsers +
                '}';
    }
}
