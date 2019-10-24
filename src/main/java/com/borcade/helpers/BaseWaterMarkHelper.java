package com.borcade.helpers;

import com.borcade.utils.WatermarkTypeEnum;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * @author brocade
 * @since JDK1.8
 */
public interface BaseWaterMarkHelper extends Serializable {

    /**
     * 处理水印
     *
     * @param originalImage    需要处理水印的原始图片
     * @param originalGraphics 需要处理水印的原始图片的画笔
     * @throws Exception 位置异常
     */
    void applyWatermarkOnPicture(BufferedImage originalImage, Graphics2D originalGraphics) throws Exception;

}
