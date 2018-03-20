package com.rhinoceros.mall.manager.manager;

import java.io.InputStream;

/**
 * 文件上传接口
 */
public interface FileUploadManager {
    String upload(InputStream is, String filePath, String fileName);
}
