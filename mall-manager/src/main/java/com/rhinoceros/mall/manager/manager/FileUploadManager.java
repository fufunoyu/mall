package com.rhinoceros.mall.manager.manager;

import java.io.InputStream;

public interface FileUploadManager {
    String upload(InputStream is, String filePath, String fileName);
}
