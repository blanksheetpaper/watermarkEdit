package com.borcade.helpers.images;

import com.borcade.entity.BaseImageWatermark;
import com.borcade.utils.CheckParams;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * 〈简单的图片旋转处理〉
 *
 * @author brocade
 * @since JDK1.8
 */
public class SimpleRotateImageHelper extends BaseImageWatermarkHelper {
    private static final long serialVersionUID = 2762331240318268352L;

    public SimpleRotateImageHelper(BaseImageWatermark imageWatermark) {
        super(imageWatermark);
    }

    @Override
    public void applyWatermarkOnPicture(BufferedImage originalImage, Graphics2D originalGraphics) throws Exception {
        checkParam();
        CheckParams.checkFontWaterParams(imageWatermark, "x,y,degree");
        dealImageWatermark(originalImage, originalGraphics, watermarkImage);
    }

    @Override
    public void dealImageWatermark(BufferedImage originalImage, Graphics2D originalGraphics, Image watermarkImage) {
        //将水印图片转化为图标
        ImageIcon imageIcon = new ImageIcon(watermarkImage);
        Image watermarkIconImage = imageIcon.getImage();
        //设置透明度
        originalGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, imageWatermark.getTransparency()));
        //处理旋转角度
        dealRotate(imageWatermark.getDegree(), originalGraphics);
        //绘制水印
        originalGraphics.drawImage(watermarkIconImage, originalImage.getWidth() * imageWatermark.getWidth() / 100,
                originalImage.getHeight() * imageWatermark.getHeight() / 100, null);

    }

    private void dealRotate(Float degree, Graphics2D originalImageGraphics) {
        originalImageGraphics.setTransform(AffineTransform.getRotateInstance(Math.toRadians(degree), imageWatermark.getX(),
                imageWatermark.getY()));
    }

}
