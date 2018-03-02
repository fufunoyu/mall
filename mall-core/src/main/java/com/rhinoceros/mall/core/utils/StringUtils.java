package com.rhinoceros.mall.core.utils;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/02 09:37
 * <p>
 * 字符串工具类
 */
public class StringUtils {

    /**
     * 下划线转驼峰法
     *
     * @param str
     * @return
     */
    public static String underline2Camel(String str) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '_') {
                i++;
                if (i < str.length()) {
                    sb.append(Character.toUpperCase(str.charAt(i)));
                }
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰法转下划线
     *
     * @param str
     * @return
     */
    public static String camel2Underline(String str) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                sb.append("_").append(Character.toLowerCase(str.charAt(i)));
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }
}
