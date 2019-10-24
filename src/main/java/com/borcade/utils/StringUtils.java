package com.borcade.utils;

import java.io.Serializable;

/**
 * 〈字符串工具类〉
 *
 * @author GJJ
 * @date 2019/9/18
 * @since JDK1.8
 */
public class StringUtils implements Serializable {
    private static final long serialVersionUID = 3670513213002718928L;

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }
}
