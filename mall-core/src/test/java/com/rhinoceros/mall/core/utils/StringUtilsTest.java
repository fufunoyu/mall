package com.rhinoceros.mall.core.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/02 10:27
 */
public class StringUtilsTest {

    @Test
    public void underline2Camel() {
        String str = "ab_c_title_aa";
        Assert.assertEquals(StringUtils.underline2Camel(str),"abCTitleAa");
    }

    @Test
    public void camel2Underline() {
        String str = "abcDfgHaa";
        Assert.assertEquals(StringUtils.camel2Underline(str),"abc_dfg_haa");
    }
}