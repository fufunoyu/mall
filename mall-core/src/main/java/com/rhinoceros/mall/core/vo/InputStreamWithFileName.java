package com.rhinoceros.mall.core.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

@Getter
@Setter
public class InputStreamWithFileName {
    private String fileName;
    InputStream is;
}
