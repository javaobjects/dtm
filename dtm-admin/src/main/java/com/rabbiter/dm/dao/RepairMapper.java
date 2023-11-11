package com.rabbiter.dm.dao;

import com.rabbiter.dm.entity.Repair;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author XiongXinxin
 * @date 2022-03-13
 */
@Repository
public interface RepairMapper {
    void save(Repair repair);

    void update(Repair repair);

    void updateStatus(Long id);

    List<Repair> list(@Param("repair") Repair repair, @Param("bid") List<Long> bid);

    Repair query(Long id);

    void delete(Long id);

    void deleteByRoomId(Long roomId);

    void deletePictureByRoomId(Long roomId);

    void savePicture(@Param("r_id") Long repairId, @Param("pic") String pic);

    void deletePicture(Long repairId);
}
