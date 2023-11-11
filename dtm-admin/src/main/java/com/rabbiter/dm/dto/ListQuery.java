package com.rabbiter.dm.dto;

/**
 * 用于分页
 *
 * @author xianxian
 * @date 2021-12-13
 */
public class ListQuery<T> {
    /**
     * 查询条件
     */
    private T entity;

    /**
     * 页
     */
    private Integer page = 1;

    /**
     * 每页行数
     */
    private Integer rows = 10;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "ListQuery{" +
                "entity=" + entity +
                ", page=" + page +
                ", rows=" + rows +
                '}';
    }
}
