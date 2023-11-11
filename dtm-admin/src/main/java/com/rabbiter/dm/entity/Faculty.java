package com.rabbiter.dm.entity;


import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author xianxian
 * @date 2022-01-19
 */
public class Faculty {
    private Long id;

    @NotBlank
    private String name;

    private Long parentId;

    private Integer orderNum;

    private List<Faculty> children;

    private Integer studentNum;

    private List<Student> students;

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

    public List<Faculty> getChildren() {
        return children;
    }

    public void setChildren(List<Faculty> children) {
        this.children = children;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", orderNum=" + orderNum +
                ", children=" + children +
                ", studentNum=" + studentNum +
                ", students=" + students +
                '}';
    }
}
