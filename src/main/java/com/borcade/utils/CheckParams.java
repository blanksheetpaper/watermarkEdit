package com.borcade.utils;

import com.borcade.entity.BaseWatermark;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈检查〉
 *
 * @author brocade
 * @since JDK1.8
 */
public final class CheckParams implements Serializable {
    private static final long serialVersionUID = -7544041225707608272L;

    private CheckParams() {
    }

    /**
     * 检查水印的参数
     *
     * @param watermark 字体|图片水印
     * @param filed     检查字段;多字段使用逗号分隔
     * @return true|false
     */
    public static boolean checkFontWaterParams(BaseWatermark watermark, String filed) throws Exception {
        if (null == watermark) {
            throw new NullPointerException("水印为null");
        }
        if (StringUtils.isBlank(filed)) {
            throw new IllegalArgumentException("无校验字段");
        }
        String[] split = filed.split(",");
        Class<? extends BaseWatermark> aClass = watermark.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        Map<String, Object> map = new HashMap<>(20);
        for (int i = 0; i < declaredFields.length; i++) {
            declaredFields[i].setAccessible(true);
            map.put(declaredFields[i].getName(), declaredFields[i].get(watermark));
        }
        Class<?> superclass;
        superclass = aClass.getSuperclass();
        while (null != superclass) {
            Field[] fields = superclass.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                map.put(fields[i].getName(), fields[i].get(watermark));
            }
            superclass = superclass.getSuperclass();
        }
        for (int i = 0; i < split.length; i++) {
            Object o = map.get(split[i]);
            if (null == o) {
                throw new IllegalArgumentException(split[i] + "值为null");
            }
        }
        return true;
    }
}
