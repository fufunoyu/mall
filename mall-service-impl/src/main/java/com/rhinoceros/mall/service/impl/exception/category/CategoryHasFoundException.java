package com.rhinoceros.mall.service.impl.exception.category;

import com.rhinoceros.mall.core.po.Category;
import com.rhinoceros.mall.service.impl.exception.user.UserException;

public class CategoryHasFoundException extends UserException {
    public CategoryHasFoundException(String msg) {
        super(msg);
    }
}
