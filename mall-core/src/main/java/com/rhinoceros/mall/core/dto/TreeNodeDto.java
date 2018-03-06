package com.rhinoceros.mall.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeNodeDto {
    private Long id;
    private String text;
    private String state;
    private Long parentId;
}
