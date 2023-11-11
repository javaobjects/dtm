package com.rabbiter.dm.entity;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author xianxian
 * @date 2022-01-19
 */
public class Building {
    private Long id;

    @NotBlank
    private String name;

    private Long parentId;

    private Integer studentNum;

    private Integer roomNum;

    private List<Building> children;

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

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public List<Building> getChildren() {
        return children;
    }

    public void setChildren(List<Building> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", studentNum=" + studentNum +
                ", roomNum=" + roomNum +
                ", children=" + children +
                '}';
    }
}
