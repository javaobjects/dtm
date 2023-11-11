package com.rabbiter.dm.dao;

import com.rabbiter.dm.entity.Leave;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xianxian
 * @date 2022-03-10
 */
@Repository
public interface LeaveMapper {
    /**
     * 保存记录
     * @param leave
     */
    void save(Leave leave);

    /**
     * 更新
     * @param id
     */
    void update(Long id);

    /**
     * 更新
     * @param leave
     */
    void updateEntity(Leave leave);

    /**
     * 获取列表
     * @param leave 查询条件
     * @param bId
     * @return
     */
    List<Leave> list(@Param("leave") Leave leave, @Param("bId") List<Long> bId);

    /**
     * 获取单个
     * @param id
     * @return
     */
    Leave query(Long id);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);
}
