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
 * 〈简单的换行的文字水印〉
 *
 * @author brocade
 * @since JDK1.8
 */
public class SimpleLineFontHelper extends BaseFontWatermarkHelper {
    private static final long serialVersionUID = -4273899943830280846L;
    private BaseFontWatermark fontWatermark;

    public SimpleLineFontHelper(BaseFontWatermark fontWatermark) {
        this.fontWatermark = fontWatermark;
    }

    @Override
    public void applyWatermarkOnPicture(BufferedImage originalImage, Graphics2D originalGraphics) throws Exception {
        CheckParams.checkFontWaterParams(fontWatermark, "fontText,font,width,height,lineSign");
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
        //获取分割后的每一行的元素
        String[] split = fontWatermark.getFontText().split(fontWatermark.getLineSign());
        //下一行元素的位置
        int y = 0;
        for (int i = 0; i < split.length; i++) {
            //取消锯齿轮
            originalGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //设置字体
            originalGraphics.setFont(fontWatermark.getFont());
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
            //字体透明度
            if (null != fontWatermark.getTransparency()) {
                originalGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, this.fontWatermark.getTransparency()));
            }
            //绘制字体
            originalGraphics.drawString(split[i], originalImage.getWidth() * fontWatermark.getWidth() / 100, originalImage.getHeight() * fontWatermark.getHeight() / 100 + metrics.getAscent() + y);
            //修改下一行的偏移量
            y += metrics.getHeight();
        }
    }

}
