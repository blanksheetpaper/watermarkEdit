package com.borcade.helpers.font;

import com.borcade.helpers.BaseWaterMarkHelper;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;

/**
 * 〈基础的字体水印类〉
 *
 * @author brocade
 * @since JDK1.8
 */
public abstract class BaseFontWatermarkHelper implements BaseWaterMarkHelper {
    private static final long serialVersionUID = -298749465417924679L;

    /**
     * 处理文字水印的细节
     *
     * @param originalImage    需要添加水印的图片
     * @param originalGraphics 画笔
     * @param metrics          字体度量
     */
    public abstract void dealDetails(BufferedImage originalImage, Graphics2D originalGraphics, FontDesignMetrics metrics) throws Exception;
}
