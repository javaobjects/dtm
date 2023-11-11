package com.rabbiter.dm.utils;

import com.rabbiter.dm.config.Constant;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author xianxian
 * @date 2021-12-11
 */
public class FileUtil {
    public static String save(MultipartFile file) {
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        File file1 = new File(Constant.UPLOAD_PATH + fileName);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            return null;
        }
        return fileName;
    }
}
