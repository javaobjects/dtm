package com.rabbiter.dm.dao;

import com.rabbiter.dm.entity.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author XiongXinxin
 * @date 2022-03-19
 */
@Repository
public interface ImageMapper {

    /**
     * 保存上传记录
     * @param image
     */
    void save(Image image);

    /**
     * 通过md5 获取
     * @param md5 MD5
     * @return
     */
    List<Image> selectByMd5(String md5);
}
