package com.rhinoceros.mall.admin.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/05 14:06
 */
@Controller
public class IndexController {

    /**
     * 返回首页
     *
     * @return
     */
    @RequestMapping({"/", "/index"})
    public String index() {

        return "index";
    }

    /**
     * 返回首页
     *
     * @return
     */
    @RequestMapping({"/test"})
    public String test(@RequestParam("file")MultipartFile [] multipartFiles, HttpServletRequest request) {
        System.out.println("--------------------------------"+multipartFiles);
        System.out.println("--------------------------------"+ multipartFiles.length);
        try {


            for (MultipartFile file : multipartFiles){
                file.getInputStream();
                System.out.println("is = "+file.getInputStream());
                System.out.println("riginalFilename = "+file.getOriginalFilename());
//                System.out.println("name = "+file.getName());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "test";
    }

    /**
     * 返回首页
     *
     * @return
     */
    @RequestMapping({"/test2"})
    public String test2() {
//
////构造一个带指定Zone对象的配置类
//        Configuration cfg = new Configuration(Zone.zone2());
////...其他参数参考类注释
//        UploadManager uploadManager = new UploadManager(cfg);
////...生成上传凭证，然后准备上传
//        String accessKey = "--v9g6tQBIff98Hh9yCFUFO11nGp9HedpH16HFX1";
//        String secretKey = "Guh68YUhFLGl87dUtfrjF7O-4XU3QNasKpJ6FNd-";
//        String bucket = "mall";
////如果是Windows情况下，格式是 D:\\qiniu\\test.png
////        String localFilePath = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3245716057,3112804808&fm=27&gp=0.jpg";
//        String localFilePath = "E:\\a.jpg";
//
////默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = null;
//        Auth auth = Auth.create(accessKey, secretKey);
//        String upToken = auth.uploadToken(bucket);
//        try {
//            Response response = uploadManager.put(localFilePath, key, upToken);
//            //解析上传成功的结果
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println("---------------putRet.key = "+putRet.key);
//            System.out.println("---------------putRet.hash = "+putRet.hash);
//        } catch (QiniuException ex) {
//            Response r = ex.response;
//            System.err.println(r.toString());
//            try {
//                System.err.println(r.bodyString());
//            } catch (QiniuException ex2) {
//                //ignore
//            }
//        }


        return "test2";
    }

}
