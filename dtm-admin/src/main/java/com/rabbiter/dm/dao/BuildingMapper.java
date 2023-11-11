package com.rabbiter.dm.dao;

import com.rabbiter.dm.entity.Building;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author xianxian
 * @date 2022-01-19
 */
@Repository
public interface BuildingMapper {

    /**
     * 列表
     * @return
     */
    List<Building> listAll();

    /**
     * 列表数
     * @param parentId 父节点id
     * @return
     */
    List<Building> list(Long parentId);

    /**
     * 插入
     * @param building
     */
    void insert(Building building);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 通过父id 获取列表
     * @param parentId 父id
     * @return 列表
     */
    List<Long> listByParentId(Long parentId);

    /**
     *
     * @param id
     * @return
     */
    Optional<Building> query(Long id);

    /**
     * 更新
     * @param building
     */
    void update(Building building);


    /**
     * 获取学生数量
     * @param buildingIds 建筑id
     * @return 学生数量
     */
    int countStudentByBuildingIds(@Param("buildingIds") List<Long> buildingIds);

    /**
     * 获取宿舍数量
     * @param buildingIds 建筑id
     * @return 宿舍数量
     */
    int countRoomByBuildingIds(@Param("buildingIds") List<Long> buildingIds);

}
