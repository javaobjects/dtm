package com.rabbiter.dm.controller;

import com.github.pagehelper.PageInfo;
import com.rabbiter.dm.annotation.Log;
import com.rabbiter.dm.annotation.RequirePermission;
import com.rabbiter.dm.config.Constant;
import com.rabbiter.dm.dto.ListQuery;
import com.rabbiter.dm.dto.Message;
import com.rabbiter.dm.entity.DepartApplication;
import com.rabbiter.dm.entity.Student;
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
 * @date 2022-01-22
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private DepartApplicationService departApplicationService;

    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/list")
    @RequirePermission(permissions = {"student:list"})
    public Result<PageInfo<Student>> list(@RequestBody ListQuery<Student> listQuery, @RequestHeader(Constant.HEADER_TOKEN) String token) {
        SystemUser systemUser = redisUtil.exchange(token).get();
        PageInfo<Student> pageInfo = studentService.list(listQuery, systemUser.getBuildingId());
        return Result.<PageInfo<Student>>ok().add(pageInfo);
    }

    @GetMapping("/list")
    @RequirePermission(permissions = {"student:list"})
    public Result<List<Student>> list(@RequestParam("name") String name, @RequestHeader(Constant.HEADER_TOKEN) String token) {
        SystemUser user = redisUtil.exchange(token).get();
        List<Student> result = studentService.list(name ,user.getBuildingId());
        return Result.<List<Student>>ok().add(result);
    }

    @GetMapping("/query")
    @RequirePermission(permissions = {"student:query"})
    public Result<Student> query(@RequestParam("id") Long id) {
        Student student = studentService.query(id).orElseThrow(() -> new HttpException(HttpCode.FAILED, "该数据不存在"));
        return Result.<Student>ok().add(student);
    }

    @PostMapping("saveOrUpdate")
    @RequirePermission(permissions = {"student:save", "student:update"})
    @Log
    public Result<?> saveOrUpdate(@RequestBody Student student) {
        studentService.saveOrUpdate(student);
        return Result.ok("操作成功");
    }

    @PostMapping("/delete")
    @RequirePermission(permissions = {"student:delete"})
    @Log
    public Result<?> delete(@RequestBody DepartApplication departApplication, @RequestHeader(Constant.HEADER_TOKEN) String token) {
        SystemUser user = redisUtil.exchange(token).get();
        departApplication.setApplyUserId(user.getId());
        departApplicationService.saveApplication(departApplication);
        if (user.getLeaderId() == null) {
            studentService.delete(departApplication.getStudentId());
            return Result.ok("退宿成功");
        } else {
            departApplicationService.saveApplication(user.getLeaderId(), departApplication.getId());
            Message message = new Message();
            message.setTitle("新的退宿申请");
            message.setType(2);
            message.setMessageBody(departApplication.getReason());
            WebSocket.sendMessage(user.getLeaderId(), message, systemUserService);
            return Result.ok("退宿申请已提交，等待上一级审核");
        }

    }
}
