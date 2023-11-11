package com.rabbiter.dm.dao;

import com.rabbiter.dm.entity.BackLate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xianxian
 * @date 2022-02-16
 */
@Repository
public interface BackLateMapper {

    /**
     * 添加记录
     * @param backLate
     */
    void save(BackLate backLate);

    /**
     * 更新记录
     * @param backLate
     */
    void update(BackLate backLate);

    /**
     * 删除记录
     * @param id
     */
    void delete(Long id);

    /**
     * 记录列表
     * @param backLate 查询条件
     * @param bId 寝室楼id
     * @return
     */
    List<BackLate> list(@Param("backLate") BackLate backLate, @Param("bId") List<Long> bId);

    /**
     * 详情
     * @param id
     * @return
     */
    BackLate query(Long id);
}
