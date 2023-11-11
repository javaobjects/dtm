package com.rabbiter.dm.entity;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xianxian
 * @date 2021-12-08
 */
public class SystemRole implements Serializable {
    private static final long serialVersionUID = -5639318500991429824L;
    private Long id;
    @NotBlank
    private String name;

    private LocalDateTime createTime;

    private List<Long> functionIds;

    private List<SystemFunction> functions;

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<Long> getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(List<Long> functionIds) {
        this.functionIds = functionIds;
    }

    public List<SystemFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(List<SystemFunction> functions) {
        this.functions = functions;
    }

    @Override
    public String toString() {
        return "SystemRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", functionIds=" + functionIds +
                ", functions=" + functions +
                '}';
    }
}
