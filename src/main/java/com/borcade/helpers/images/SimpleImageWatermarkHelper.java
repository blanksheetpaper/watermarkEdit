package com.borcade.helpers.images;

import com.borcade.entity.BaseImageWatermark;
import com.borcade.utils.CheckParams;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * 〈简单的图片水印处理〉
 *
 * @author brocade
 * @since JDK1.8
 */
public class SimpleImageWatermarkHelper extends BaseImageWatermarkHelper {
    private static final long serialVersionUID = -1918576413243867108L;

    public SimpleImageWatermarkHelper(BaseImageWatermark imageWatermark) {
        super(imageWatermark);
    }

    @Override
    public void applyWatermarkOnPicture(BufferedImage originalImage, Graphics2D originalGraphics) throws Exception {
        //检查参数
        checkParam();
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
        originalGraphics.setTransform(AffineTransform.getRotateInstance(0));
        //绘制水印
        originalGraphics.drawImage(watermarkIconImage, originalImage.getWidth() * imageWatermark.getWidth() / 100,
                originalImage.getHeight() * imageWatermark.getHeight() / 100, null);

    }


}
