package com.rhinoceros.mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ImageController {
    @RequestMapping("/upload")
    public String uploadimg(MultipartFile [] multipartFiles,HttpServletRequest request) throws Exception {

//            String filename = file.getOriginalFilename();
////            System.out.println(filename);
////            //写入本地磁盘
////            InputStream is = file.getInputStream();
////            byte[] bs = new byte[1024];
////            int len;
////            OutputStream os = new FileOutputStream(new File("D:/temp/" + filename));
////            while ((len = is.read(bs)) != -1) {
////                os.write(bs, 0, len);
////            }
////            os.close();
////            is.close();
        System.out.println("--------------------------------");
//        MultipartFile [] files = {multipartFiles};
//        uploadFileList(multipartFiles,request);
            return "test2";
        }

}