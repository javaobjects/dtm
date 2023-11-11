package com.rabbiter.dm.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author xianxian
 * @date 2022-01-08
 */
public class TestDO {

    private Integer id;
    @Size(min = 3, max = 6)
    private String name;
    @NotNull
    private String a;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "TestDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", a='" + a + '\'' +
                '}';
    }
}
