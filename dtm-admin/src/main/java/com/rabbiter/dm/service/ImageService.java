package com.rabbiter.dm.service;

import com.rabbiter.dm.dao.ImageMapper;
import com.rabbiter.dm.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XiongXinxin
 * @date 2022-03-19
 */
@Service
public class ImageService {
    @Autowired
    private ImageMapper imageMapper;

    public void save(Image image) {
        imageMapper.save(image);
    }

    public List<Image> selectByMd5(String md5) {
        return imageMapper.selectByMd5(md5);
    }
}
