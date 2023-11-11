package com.rabbiter.dm.controller;

import com.rabbiter.dm.config.Constant;
import com.rabbiter.dm.service.StatisticsService;
import com.rabbiter.dm.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author XiongXinxin
 * @date 2022-03-13
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public Result<Map<String, Object>> statistics(@RequestHeader(Constant.HEADER_TOKEN) String token) {
        Map<String, Object> result = statisticsService.get(token);
        return Result.<Map<String, Object>>ok().add(result);
    }
}
