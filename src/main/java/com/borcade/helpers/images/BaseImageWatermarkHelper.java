package com.borcade.helpers.images;

import com.borcade.entity.BaseImageWatermark;
import com.borcade.helpers.BaseWaterMarkHelper;
import com.borcade.utils.CheckParams;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 〈基础父类图片水印〉
 *
 * @author brocade
 * @since JDK1.8
 */
public abstract class BaseImageWatermarkHelper implements BaseWaterMarkHelper {


    protected BaseImageWatermark imageWatermark;

    protected Image watermarkImage;

    public BaseImageWatermarkHelper(BaseImageWatermark imageWatermark) {
        this.imageWatermark = imageWatermark;
    }

    /**
     * 校验参数
     *
     * @throws Exception
     */
    protected void checkParam() throws Exception {
        //检查参数
        if (null == imageWatermark.getFile() && null == imageWatermark.getUrl()) {
            throw new IllegalArgumentException("[file|url]---->字段不能同时为null");
        }
        if (null != imageWatermark.getUrl()) {
            watermarkImage = ImageIO.read(imageWatermark.getUrl());
        } else {
            watermarkImage = ImageIO.read(imageWatermark.getFile());
        }
        CheckParams.checkFontWaterParams(imageWatermark, "width,height,transparency");
    }

    /**
     * 处理水印图片细节
     *
     * @param originalImage    原始图片
     * @param originalGraphics 原始图片的画笔
     * @param watermarkImage   水印图片
     */
    public abstract void dealImageWatermark(BufferedImage originalImage, Graphics2D originalGraphics, Image watermarkImage);
}
