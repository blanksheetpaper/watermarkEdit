package com.borcade.helpers.font;

import com.borcade.entity.BaseFontWatermark;
import com.borcade.helpers.BaseWaterMarkHelper;
import com.borcade.utils.CheckParams;
import com.borcade.utils.StringUtils;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * 〈旋转矩形文本水印背景文字水印〉
 *
 * @author brocade
 * @since JDK1.8
 */
public class RotateRectangleFontHelper extends BaseFontWatermarkHelper {
    private static final long serialVersionUID = 338284290539412694L;

    private BaseFontWatermark fontWatermark;

    public RotateRectangleFontHelper(BaseFontWatermark fontWatermark) {
        this.fontWatermark = fontWatermark;
    }

    @Override
    public void applyWatermarkOnPicture(BufferedImage originalImage, Graphics2D originalGraphics) throws Exception {
        CheckParams.checkFontWaterParams(fontWatermark, "fontText,font,width,height,x,y,degree,backgroundTransparency,backGroundColor");
        //得到字体的高度
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(fontWatermark.getFont());
        //处理旋转角度
        originalGraphics.setTransform(AffineTransform.getRotateInstance(0));
        dealDetails(originalImage, originalGraphics, metrics);
    }

    /**
     * 处理文字水印的细节
     *
     * @param originalImage    需要添加水印的图片
     * @param originalGraphics 画笔
     * @param metrics          字体度量
     */
    @Override
    public void dealDetails(BufferedImage originalImage, Graphics2D originalGraphics, FontDesignMetrics metrics) throws Exception {
        //取消锯齿轮
        originalGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //设置旋转角度
        originalGraphics.setTransform(AffineTransform.getRotateInstance(Math.toRadians(fontWatermark.getDegree()), fontWatermark.getX(), fontWatermark.getY()));
        //绘制空心得矩形
        originalGraphics.drawRect(originalImage.getWidth() * fontWatermark.getWidth() / 100, originalImage.getHeight() * fontWatermark.getHeight() / 100,
                metrics.stringWidth(fontWatermark.getFontText()), metrics.getHeight());
        //设置背景颜色
        originalGraphics.setColor(new Color(Integer.valueOf(fontWatermark.getBackGroundColor().substring(1), 16)));
        //设置实心矩形的透明度
        originalGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, fontWatermark.getBackgroundTransparency()));
        //设置旋转角度
        originalGraphics.setTransform(AffineTransform.getRotateInstance(Math.toRadians(fontWatermark.getDegree()),
                fontWatermark.getX(), fontWatermark.getY()));
        //再次取消锯齿轮
        originalGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //填充矩形背景
        originalGraphics.fillRect(originalImage.getWidth() * fontWatermark.getWidth() / 100, originalImage.getHeight() * fontWatermark.getHeight() / 100,
                metrics.stringWidth(fontWatermark.getFontText()), metrics.getHeight());
        //设置字体
        originalGraphics.setFont(fontWatermark.getFont());
        //设置字体透明度
        if (null != fontWatermark.getTransparency()) {
            originalGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, fontWatermark.getTransparency()));
        }
        //如果有传入的颜色设置
        if (null != fontWatermark.getColor()) {
            originalGraphics.setColor(fontWatermark.getColor());
        } else {
            if (StringUtils.isNotBlank(fontWatermark.getFontColor())) {
                //字体的颜色
                Color color = new Color(Integer.valueOf(fontWatermark.getFontColor().substring(1), 16));
                originalGraphics.setColor(color);
            }
        }
        //设置旋转角度
        originalGraphics.setTransform(AffineTransform.getRotateInstance(Math.toRadians(fontWatermark.getDegree()), fontWatermark.getX(), fontWatermark.getY()));
        //绘制字体
        originalGraphics.drawString(fontWatermark.getFontText(), originalImage.getWidth() * fontWatermark.getWidth() / 100,
                originalImage.getHeight() * fontWatermark.getHeight() / 100 + metrics.getAscent());
    }
}
