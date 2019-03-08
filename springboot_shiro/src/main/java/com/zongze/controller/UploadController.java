package com.zongze.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Create By xzz on 2019/3/8
 */
@Controller
public class UploadController {

    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    private String uploadPath = "F:\\upload";

    @PostMapping("/upload")
    public Object upload(HttpServletRequest request) {

        StandardMultipartHttpServletRequest request1 = (StandardMultipartHttpServletRequest) request;

        Iterator<String> fileIterator = request1.getFileNames();

        File uploadFile = new File(uploadPath);

        List<String> fileNames = new ArrayList<>();

        while (fileIterator.hasNext()) {
            String fileName = fileIterator.next();
            MultipartFile file = request1.getFile(fileName);

            String originalFilename = file.getOriginalFilename();
            originalFilename = UUID.randomUUID().toString().replaceAll("", "-") + originalFilename.substring(originalFilename.indexOf("."));

            try {
                file.transferTo(uploadFile);
                fileNames.add(originalFilename);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("图片上传错误，%s", fileName);
            }

        }
        return JSON.toJSONString(fileNames);
    }


}
