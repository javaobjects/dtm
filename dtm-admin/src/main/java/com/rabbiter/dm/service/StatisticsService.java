package com.rabbiter.dm.service;

import com.rabbiter.dm.entity.SystemUser;
import com.rabbiter.dm.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiongXinxin
 * @date 2022-03-13
 */
@Service
public class StatisticsService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RoomService roomService;

    public Map<String, Object> get(String token) {
        Map<String, Object> result = new HashMap<>(5);
        SystemUser user = redisUtil.exchange(token).get();
        result.put("building", user.getBuilding() == null ? "无":user.getBuilding().getName());
        int roomNum = roomService.count(user.getBuildingId());
        result.put("roomNum", roomNum);
        return result;
    }
}
