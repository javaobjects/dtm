package com.rabbiter.dm.controller;

import com.rabbiter.dm.annotation.Log;
import com.rabbiter.dm.annotation.RequirePermission;
import com.rabbiter.dm.entity.Building;
import com.rabbiter.dm.entity.Room;
import com.rabbiter.dm.exception.HttpException;
import com.rabbiter.dm.service.BuildingService;
import com.rabbiter.dm.service.RoomService;
import com.rabbiter.dm.utils.HttpCode;
import com.rabbiter.dm.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xianxian
 * @date 2022-01-19
 */
@RestController
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private RoomService roomService;


    @GetMapping("/listAll")
    @RequirePermission(permissions = {"manage:building:list"})
    public Result<List<Building>> listAll() {
        List<Building> list = buildingService.listAll();
        return Result.<List<Building>>ok().add(list);
    }

    @GetMapping("/list")
    @RequirePermission(permissions = {"manage:building:list"})
    public Result<List<Building>> list() {
        List<Building> list = buildingService.list();
        return Result.<List<Building>>ok().add(list);
    }

    @GetMapping("delete")
    @RequirePermission(permissions = {"manage:building:delete"})
    @Log("删除building")
    public Result<?> delete(@RequestParam("id") Long id) {
        List<Room> list = roomService.listByBuildingId(id);
        if (list.size() > 0) {
            throw new HttpException(HttpCode.FAILED, "该节点下或子节点还有寝室，无法删除");
        }
        buildingService.delete(id);
        return Result.ok("删除成功");
    }

    @GetMapping("/query")
    @RequirePermission(permissions = {"manage:building:query"})
    public Result<Building> query(@RequestParam("id") Long id) {
        Building building = buildingService.query(id).orElseThrow(() -> new HttpException(HttpCode.FAILED, "该数据不存在"));
        return Result.<Building>ok().add(building);
    }

    @PostMapping("/saveOrUpdate")
    @RequirePermission(permissions = {"manage:building:save","manage:building:update"})
    @Log
    public Result<?> saveOrUpdate(@RequestBody @Validated Building building) {
        if (building.getId() == null) {
            buildingService.save(building);
        } else {
            if (building.getId().equals(building.getParentId())) {
                throw new HttpException(HttpCode.FAILED, "父节点不能为自己");
            }
            buildingService.update(building);
        }
        return Result.ok("操作成功");
    }
}
