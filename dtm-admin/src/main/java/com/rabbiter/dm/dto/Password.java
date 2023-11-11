package com.rabbiter.dm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 用于密码修改
 *
 * @author xianxian
 * @date 2021-12-13
 */
public class Password implements Serializable {
    private static final long serialVersionUID = -1771036907310685922L;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String oldPassword;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String current1;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String current2;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getCurrent1() {
        return current1;
    }

    public void setCurrent1(String current1) {
        this.current1 = current1;
    }

    public String getCurrent2() {
        return current2;
    }

    public void setCurrent2(String current2) {
        this.current2 = current2;
    }

    @Override
    public String toString() {
        return "Password{" +
                "oldPassword='" + oldPassword + '\'' +
                ", current1='" + current1 + '\'' +
                ", current2='" + current2 + '\'' +
                '}';
    }
}
