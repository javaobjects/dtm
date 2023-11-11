package com.rabbiter.dm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbiter.dm.config.Constant;
import com.rabbiter.dm.dto.ListQuery;
import com.rabbiter.dm.dto.Message;
import com.rabbiter.dm.entity.DepartApplication;
import com.rabbiter.dm.entity.DepartApplicationUser;
import com.rabbiter.dm.entity.SystemUser;
import com.rabbiter.dm.exception.HttpException;
import com.rabbiter.dm.service.DepartApplicationService;
import com.rabbiter.dm.service.StudentService;
import com.rabbiter.dm.service.SystemUserService;
import com.rabbiter.dm.socket.WebSocket;
import com.rabbiter.dm.utils.HttpCode;
import com.rabbiter.dm.utils.RedisUtil;
import com.rabbiter.dm.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xianxian
 * @date 2022-02-09
 */
@RestController
@RequestMapping("/depart")
public class DepartApplicationController {
    @Autowired
    private DepartApplicationService departApplicationService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SystemUserService systemUserService;

    @PostMapping("/listMy")
    public Result<PageInfo<DepartApplication>> listMyApplication(@RequestBody ListQuery<DepartApplication> listQuery,
                                    @RequestHeader(Constant.HEADER_TOKEN) String token) {
        Long id = redisUtil.get(token);
        PageHelper.startPage(listQuery.getPage(), listQuery.getRows());
        List<DepartApplication> list = departApplicationService.listMyApplication(id);
        PageInfo<DepartApplication> pageInfo = new PageInfo<>(list);
        return Result.<PageInfo<DepartApplication>>ok().add(pageInfo);
    }

    @PostMapping("/listMyFlow")
    public Result<PageInfo<DepartApplicationUser>> listMyFlow(@RequestBody ListQuery<DepartApplication> listQuery,
                             @RequestHeader(Constant.HEADER_TOKEN) String token) {
        Long id = redisUtil.get(token);
        PageHelper.startPage(listQuery.getPage(), listQuery.getRows());
        List<DepartApplicationUser> list = departApplicationService.listMyFlow(id);
        PageInfo<DepartApplicationUser> pageInfo = new PageInfo<>(list);
        return Result.<PageInfo<DepartApplicationUser>>ok().add(pageInfo);
    }

    @GetMapping("/listFlow/{id}")
    public Result<List<DepartApplicationUser>> listApplicationFlow(@PathVariable("id") Long id) {
        List<DepartApplicationUser> list = departApplicationService.listApplicationFlow(id);
        return Result.<List<DepartApplicationUser>>ok().add(list);
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody DepartApplicationUser departApplicationUser,
                         @RequestHeader(Constant.HEADER_TOKEN) String token) {
        String msg = "成功，";
        SystemUser user = redisUtil.exchange(token).get();
        DepartApplicationUser applicationUser =
                departApplicationService
                        .getApplicationUser(departApplicationUser.getId());
        if (!user.getId().equals(applicationUser.getOperateUserId())) {
            throw new HttpException(HttpCode.FAILED, "无法审核");
        }
        if (applicationUser.getIsAgree() != null) {
            throw new HttpException(HttpCode.FAILED, "已审核过，无法修改");
        }
        departApplicationService.update(departApplicationUser);
        DepartApplication application =
                departApplicationService
                        .getByDepartApplicationUserId(departApplicationUser.getId());

        if (departApplicationUser.getIsAgree()) {
            if (user.getLeaderId() == null) {
                studentService.delete(application.getStudentId());
                msg += "该学生已成功退宿";
            } else {
                departApplicationService.saveApplication(user.getLeaderId(), application.getId());
                Message message = new Message();
                message.setTitle("新的退宿申请");
                message.setType(2);
                message.setMessageBody(application.getReason());
                WebSocket.sendMessage(user.getLeaderId(), message, systemUserService);
                msg += "等待上一级审核";
            }
        } else {
            msg += "审核未通过";
        }

        return Result.ok(msg);
    }

    @GetMapping("delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        departApplicationService.deleteApplication(id);
        return Result.ok("撤销成功");
    }

    @GetMapping("/query/{id}")
    public Result<DepartApplication> query(@PathVariable Long id) {
        DepartApplication data = departApplicationService.getApplication(id);
        return Result.<DepartApplication>ok().add(data);
    }

}
