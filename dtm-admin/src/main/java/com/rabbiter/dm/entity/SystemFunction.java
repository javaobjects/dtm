package com.rabbiter.dm.entity;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xianxian
 * @date 2021-12-08
 */
public class SystemFunction implements Serializable {
    private static final long serialVersionUID = 3805867595133356807L;
    private Long id;
    @NotBlank
    private String name;

    private Long parentId;

    private Integer orderNum;

    private String path;

    private String component;
    @NotBlank
    private String menuType;

    private String permission;

    private String icon;

    private LocalDateTime createTime;

    private List<SystemFunction> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<SystemFunction> getChildren() {
        return children;
    }

    public void setChildren(List<SystemFunction> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SystemFunction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", orderNum=" + orderNum +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", menuType='" + menuType + '\'' +
                ", permission='" + permission + '\'' +
                ", icon='" + icon + '\'' +
                ", createTime=" + createTime +
                ", children=" + children +
                '}';
    }
}
