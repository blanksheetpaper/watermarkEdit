package com.borcade.helpers.images;

import com.borcade.entity.BaseImageWatermark;
import com.borcade.utils.CheckParams;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * 〈等比例裁剪水印〉
 *
 * @author brocade
 * @since JDK1.8
 */
public class ProportionalCropImageHelper extends BaseImageWatermarkHelper {
    private static final long serialVersionUID = 9178083771705053090L;

    public ProportionalCropImageHelper(BaseImageWatermark imageWatermark) {
        super(imageWatermark);
    }

    @Override
    public void applyWatermarkOnPicture(BufferedImage originalImage, Graphics2D originalGraphics) throws Exception {
        checkParam();
        CheckParams.checkFontWaterParams(imageWatermark, "size,width,height,transparency");
        dealImageWatermark(originalImage, originalGraphics, watermarkImage);
    }

    @Override
    public void dealImageWatermark(BufferedImage originalImage, Graphics2D originalGraphics, Image watermarkImage) {
        // 设置水印旋转度
        originalGraphics.setTransform(AffineTransform.getRotateInstance(0));
        //水印是否为透明图片   TODO:暂时这样判断是否为透明色;
        if (isTransparentImageWatermark(watermarkImage)) {
            dealTransparentImageWatermark(originalImage, originalGraphics, watermarkImage);
        } else {
            dealOpaqueImageWatermark(originalImage, originalGraphics, watermarkImage);
        }
    }

    /**
     * 是否为透明背景的图片水印
     *
     * @param waterImage 原始的水印图
     * @return true|false
     */
    private boolean isTransparentImageWatermark(Image waterImage) {
        return isTransparentCheck((BufferedImage) watermarkImage, watermarkImage.getWidth(null), watermarkImage.getHeight(null));
    }

    /**
     * 检查是不是透明底色
     *
     * @param watermarkImage 水印图片对象
     * @param width          水印图片宽度
     * @param height         水印图片高度
     * @return true|false
     */
    private boolean isTransparentCheck(BufferedImage watermarkImage, int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (watermarkImage.getRGB(j, i) >> 24 == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 透明图片处理
     *
     * @param originalImage         原始的图片水印
     * @param originalImageGraphics 画笔
     * @param watermarkImage        图片水印
     * @return 处理之后的水印
     */
    private BufferedImage dealTransparentImageWatermark(BufferedImage originalImage, Graphics2D originalImageGraphics, Image watermarkImage) {
        BufferedImage afterDealWatermark = new BufferedImage(watermarkImage.getWidth(null) * imageWatermark.getSize() / 100,
                watermarkImage.getHeight(null) * imageWatermark.getSize() / 100, Transparency.TRANSLUCENT);
        applyWatermarkInImage(originalImage, originalImageGraphics, watermarkImage, afterDealWatermark);
        return afterDealWatermark;
    }

    /**
     * 非透明水印图片处理
     *
     * @param originalImage    原始的图片水印
     * @param originalGraphics 画笔
     * @param watermarkImage   图片水印
     * @return 处理之后的水印
     */
    private BufferedImage dealOpaqueImageWatermark(BufferedImage originalImage, Graphics2D originalGraphics, Image watermarkImage) {
        //处理之后的水印
        BufferedImage afterDealWatermark = new BufferedImage(watermarkImage.getWidth(null) * imageWatermark.getSize() / 100,
                watermarkImage.getHeight(null) * imageWatermark.getSize() / 100, BufferedImage.TYPE_INT_RGB);
        applyWatermarkInImage(originalImage, originalGraphics, watermarkImage, afterDealWatermark);
        return afterDealWatermark;
    }

    /**
     * 将裁剪后的水印应用到图片上
     *
     * @param originalImage         需要添加水印的图片
     * @param originalImageGraphics 画板
     * @param watermarkImage        水印图片
     * @param afterDealWatermark    处理之后的水印
     */
    private void applyWatermarkInImage(BufferedImage originalImage, Graphics2D originalImageGraphics, Image watermarkImage, BufferedImage afterDealWatermark) {
        Graphics2D waterGraphics;
        ImageIcon imageIcon;
        waterGraphics = afterDealWatermark.createGraphics();
        //取消锯齿
        waterGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //绘制等比裁剪后的水印
        waterGraphics.drawImage(watermarkImage.getScaledInstance(watermarkImage.getWidth(null) * imageWatermark.getSize() / 100,
                watermarkImage.getHeight(null) * imageWatermark.getSize() / 100, Image.SCALE_SMOOTH), 0, 0, null);
        //释放资源
        waterGraphics.dispose();
        //将水印图片转化为图标
        imageIcon = new ImageIcon(afterDealWatermark);
        //获取裁剪后的水印对象
        Image watermarkIconImage = imageIcon.getImage();
        //设置水印图片的透明度
        originalImageGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, imageWatermark.getTransparency()));
        //绘制水印
        originalImageGraphics.drawImage(watermarkIconImage, originalImage.getWidth() * imageWatermark.getWidth() / 100, originalImage.getHeight() * imageWatermark.getHeight() / 100, null);
    }

}
