package com.borcade.entity;

import java.io.Serializable;

/**
 * 〈基础水印父类〉
 *
 * @author brocade
 * @since JDK1.8
 */
public class BaseWatermark implements Serializable {
    private static final long serialVersionUID = 8104513406364167407L;
    /**
     * 文字字体|图片水印的透明度[0-->1]之间
     */
    private Float transparency;
    /**
     * 文字水印|图片水印的尺寸大小
     * <p>如果为文字水印这个大小就是每个文字的字体大小</p>
     * <p>如果为图片水印这个大小在计算的时候会当做百分比进行等比裁剪计算</p>
     */
    private Integer size;

    /**
     * 水印摆放的宽度百分比
     */
    private Integer width;
    /**
     * 水印摆放的高度百分比
     */
    private Integer height;

    public BaseWatermark(Float transparency, Integer size, Integer width, Integer height) {
        this.transparency = transparency;
        this.size = size;
        this.width = width;
        this.height = height;
    }

    public Float getTransparency() {
        return transparency;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }
}
