package com.rabbiter.dm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbiter.dm.dao.BackLateMapper;
import com.rabbiter.dm.dto.ListQuery;
import com.rabbiter.dm.entity.BackLate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xianxian
 * @date 2022-02-16
 */
@Service
public class BackLateService {
    @Autowired
    private BackLateMapper backLateMapper;

    @Autowired
    private BuildingService buildingService;

    public PageInfo<BackLate> list(ListQuery<BackLate> listQuery, Long bid) {
        List<Long> bIds = buildingService.getIdsByParentId(bid);
        BackLate backLate = listQuery.getEntity();
        PageHelper.startPage(listQuery.getPage(), listQuery.getRows());
        List<BackLate> backLateList = backLateMapper.list(backLate, bIds);
        return new PageInfo<>(backLateList);
    }

    public void saveOrUpdate(BackLate backLate) {
        if (backLate.getId() == null) {
            backLateMapper.save(backLate);
        } else {
            backLateMapper.update(backLate);
        }
    }

    public void delete(Long id) {
        backLateMapper.delete(id);
    }

    public BackLate query(Long id) {
        return backLateMapper.query(id);
    }
}
