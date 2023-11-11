package com.rabbiter.dm.controller;

import com.rabbiter.dm.entity.Image;
import com.rabbiter.dm.entity.SystemFunction;
import com.rabbiter.dm.entity.SystemRole;
import com.rabbiter.dm.entity.SystemUser;
import com.rabbiter.dm.exception.HttpException;
import com.rabbiter.dm.config.Constant;
import com.rabbiter.dm.service.*;
import com.rabbiter.dm.utils.HttpCode;
import com.rabbiter.dm.utils.MD5Util;
import com.rabbiter.dm.utils.RedisUtil;
import com.rabbiter.dm.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.rabbiter.dm.config.Constant.HEADER_TOKEN;

/**
 * @author xianxian
 * @date 2021-12-10
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    private final RedisUtil redisUtil;
    private final SystemUserService systemUserService;
    private final SystemFunctionService systemFunctionService;
    private final SystemRoleService systemRoleService;
    private final NoticeService noticeService;
    private final DepartApplicationService departApplicationService;
    private final ImageService imageService;

    public PermissionController(RedisUtil redisUtil,
                                SystemUserService systemUserService,
                                SystemFunctionService systemFunctionService,
                                SystemRoleService systemRoleService,
                                NoticeService noticeService,
                                DepartApplicationService departApplicationService,
                                ImageService imageService) {
        this.redisUtil = redisUtil;
        this.systemUserService = systemUserService;
        this.systemFunctionService = systemFunctionService;
        this.systemRoleService = systemRoleService;
        this.noticeService = noticeService;
        this.departApplicationService = departApplicationService;
        this.imageService = imageService;
    }

    /**
     * 获取登录用户权限，头像，名称，菜单信息
     *
     * @param token token
     * @return
     */
    @GetMapping("/info")
    public Result<SystemUser> info(@RequestHeader(HEADER_TOKEN) String token) {
        SystemUser user = redisUtil.exchange(token).orElseThrow(() -> new HttpException(HttpCode.FAILED, "没有该用户"));
        List<SystemRole> systemRoles = systemRoleService.listByUserId(user.getId());
        Set<String> permissions;
        if (systemRoles.size() == 0) {
            permissions = new HashSet<>();
        } else {
            permissions = systemFunctionService.getPermission(systemRoles);
        }
        user.setPermissions(permissions);
        List<SystemFunction> functionList = systemFunctionService.listFunctionByParentIdAndIds(null, user.getUserRoleId());
        user.setFunctions(functionList);
        return Result.<SystemUser>ok().add(user);
    }

    /**
     * 获取登录用户基本信息
     *
     * @param token 用于获取请求头中的token
     * @return
     */
    @GetMapping("/userinfo")
    public Result<SystemUser> userInfo(@RequestHeader(HEADER_TOKEN) String token) {
        Long id = redisUtil.get(token);
        SystemUser user = systemUserService.info(id).get();
        return Result.<SystemUser>ok().add(user);
    }
    /**
     * 上传图片
     *
     * @param file  图片
     * @param token token
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file, @RequestHeader(HEADER_TOKEN) String token) {
        String fileName = UUID.randomUUID().toString() + ".png";
        if (file != null) {
            try {
                String md5 = MD5Util.getFileMD5(file.getInputStream());
                if (md5 == null) {
                    throw new HttpException(HttpCode.FAILED, "上传失败");
                }
                // 确认是否存在文件夹，不存在则创建
                File folder = new File(Constant.UPLOAD_PATH);
                if  (!folder.exists()) {
                    folder.mkdir();
                }
                List<Image> list = imageService.selectByMd5(md5);
                if (list.size() == 0) {
                    file.transferTo(new File(Constant.UPLOAD_PATH, fileName));
                    Long userId = redisUtil.get(token);
                    Image image = new Image();
                    image.setUploadUser(userId);
                    image.setOriginalName(file.getOriginalFilename());
                    image.setSaveName(fileName);
                    image.setMd5(md5);
                    imageService.save(image);
                } else {
                    fileName = list.get(0).getSaveName();
                }

            } catch (IOException e) {
                throw new HttpException(HttpCode.FAILED, "上传失败");
            }
        } else {
            throw new HttpException(HttpCode.FAILED, "上传失败");
        }
        return Result.<String>ok().add(fileName);
    }

    @GetMapping("/countUnread")
    public Result<Integer> count(@RequestHeader(HEADER_TOKEN) String token) {
        Long userId = redisUtil.get(token);
        int countNotice = noticeService.countByUserId(userId);
        int countDepartApplication = departApplicationService.countFlowAgreeIsNull(userId);
        return Result.<Integer>ok().add(countNotice + countDepartApplication);
    }
}
