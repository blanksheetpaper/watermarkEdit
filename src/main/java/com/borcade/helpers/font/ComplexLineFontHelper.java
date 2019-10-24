package com.borcade.helpers.font;

import com.borcade.entity.BaseFontWatermark;
import com.borcade.utils.CheckParams;
import com.borcade.utils.StringUtils;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * 〈复杂的的换行字体水印处理〉
 * <p>换行的背景文字水印思路:创建一个矩形容器;将所有的行元素放在次一个矩形容器中</p>
 *
 * @author brocade
 * @since JDK1.8
 */
public class ComplexLineFontHelper extends BaseFontWatermarkHelper {
    private static final long serialVersionUID = 3530520563107440609L;
    private BaseFontWatermark fontWatermark;

    public ComplexLineFontHelper(BaseFontWatermark fontWatermark) {
        this.fontWatermark = fontWatermark;
    }

    @Override
    public void applyWatermarkOnPicture(BufferedImage originalImage, Graphics2D originalGraphics) throws Exception {
        CheckParams.checkFontWaterParams(fontWatermark, "fontText,font,width,height,x,y,degree,backgroundTransparency,backGroundColor,lineSign");
        //得到字体的高度
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(fontWatermark.getFont());
        //处理旋转角度
        originalGraphics.setTransform(AffineTransform.getRotateInstance(0));
        dealDetails(originalImage, originalGraphics, metrics);
    }

    @Override
    public void dealDetails(BufferedImage originalImage, Graphics2D originalGraphics, FontDesignMetrics metrics) throws Exception {
        //获取分割后的每一行的元素
        String[] split = fontWatermark.getFontText().split(fontWatermark.getLineSign());
        //最长的行元素值
        String max = "";
        //矩形的总高
        int heightRectangle = 0;
        //获取最长的一行,
        for (int i = 0; i < split.length; i++) {
            //空格行是否需要去除[请按需添加]
           /* if (StringUtils.isBlank(split[i])) {
                continue;
            }*/
            //处理汉字
            if (split[i].getBytes("GBK").length > max.getBytes("GBK").length) {
                max = split[i];
            }
            int height = metrics.getHeight();
            heightRectangle += height;
        }
        //绘制矩形  start
        //取消锯齿轮
        originalGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //设置旋转角度
        originalGraphics.setTransform(AffineTransform.getRotateInstance(Math.toRadians(fontWatermark.getDegree()), fontWatermark.getX(), fontWatermark.getY()));
        //绘制空心得矩形
        originalGraphics.drawRect(originalImage.getWidth() * fontWatermark.getWidth() / 100, originalImage.getHeight() * fontWatermark.getHeight() / 100,
                //注意此处为最大行元素的宽度
                metrics.stringWidth(max), heightRectangle);
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
                metrics.stringWidth(max), heightRectangle);
        //绘制矩形 end
        //绘制文字
        //下一行元素的位置
        int y = 0;
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
        for (int i = 0; i < split.length; i++) {
            //空格行是否需要去除[请按需添加]
           /* if (StringUtils.isBlank(split[i])) {
                continue;
            }*/
            //设置旋转角度
            originalGraphics.setTransform(AffineTransform.getRotateInstance(Math.toRadians(fontWatermark.getDegree()), fontWatermark.getX(), fontWatermark.getY()));
            //绘制字体
            originalGraphics.drawString(split[i], originalImage.getWidth() * fontWatermark.getWidth() / 100,
                    originalImage.getHeight() * fontWatermark.getHeight() / 100 + metrics.getAscent() + y);
            y += metrics.getHeight();
        }
    }
}
