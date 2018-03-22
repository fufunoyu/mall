package com.rhinoceros.mall.manager.impl.manager;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.rhinoceros.mall.manager.impl.exception.FileUplodException;
import com.rhinoceros.mall.manager.manager.FileUploadManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * 文件上传接口实现
 */
@Component
public class FileUploadManagerImpl implements FileUploadManager {
    @Value("#{qiniuConfig['qiniu.bucket']}")
    private String bucket;
    @Value("#{qiniuConfig['qiniu.secretKey']}")
    private String secretKey;
    @Value("#{qiniuConfig['qiniu.accessKey']}")
    private String accessKey;
    private static final String basePath = "http://p5u6o7frf.bkt.clouddn.com";


    @Override
    public String upload(InputStream is, String filePath, String fileName) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //通过该类上传文件
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String key = filePath.endsWith("/") ? (filePath + fileName) : (filePath + "/" + fileName);
        String uploadToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(is, key, uploadToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            return basePath + "/" + putRet.key;
        } catch (QiniuException e) {
            e.printStackTrace();
            throw new FileUplodException("文件上传失败");
        }
    }
}
