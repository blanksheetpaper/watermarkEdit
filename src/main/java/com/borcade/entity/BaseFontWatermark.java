package com.borcade.entity;

import java.awt.*;
import java.io.Serializable;

/**
 * 〈字体类〉
 *
 * @author brocade
 * @since JDK1.8
 */
public class BaseFontWatermark extends BaseWatermark implements Serializable {
    private static final long serialVersionUID = 4691993030321549768L;

    /**
     * 字体
     * <p>当此属性拥有的时候会使用此属性忽略其他设置的属性</p>
     */
    private Font font;
    /**
     * 字体颜色
     * <p>当此属性和FontColor同时拥有的时候会使用此属性忽略FontColor设置的属性</p>
     */
    private Color color;
    /**
     * 字体内容
     */
    private String fontText;
    /**
     * 字体类型[微软雅黑|宋体...]
     */
    private String fontName;
    /**
     * 字体风格[Font.PLAIN...]
     */
    private int fontStyle = Font.PLAIN;
    /**
     * 字体的颜色
     * <p>RGB---->16进制</p>
     * <p>当此属性和FontColor同时拥有的时候会使用此属性忽略FontColor设置的属性</p>
     */
    private String fontColor;

    /**
     * 背景的底色透明度[0-->1]之间
     */
    private Float backgroundTransparency;

    /**
     * 背景颜色
     * <p>RGB---->16进制</p>
     */
    private String backGroundColor;

    /**
     * 旋转角度
     */
    private Float degree;

    /**
     * 水印放置再原始图片上的x轴的位置
     * <p>设置图片的旋转角度[画笔在画图的时候,是以图片的左上角作为原点开始画的,不管是文字水印还是字体水印,这个里面的x|y对应的坐标应该是文字水印或图片水印左上角的点的坐标]</p>
     */
    private Integer x;
    /**
     * 水印放置在原始图片上面的y轴的位置
     * <p>设置图片的旋转角度[画笔在画图的时候,是以图片的左上角作为原点开始画的,y轴正方向向下,x轴正方向像右;
     * 不管是文字水印还是字体水印, 这个里面的x|y对应的坐标应该是文字水印或图片水印左上角的点的坐标]</p>
     */
    private Integer y;
    /**
     * 文字水印换行符号
     */
    private String lineSign;


    public BaseFontWatermark(String fontText, int fontStyle, String fontName, Float transparency, Integer size, Integer width, Integer height) {
        super(transparency, size, width, height);
        this.fontText = fontText;
        this.fontStyle = fontStyle;
        this.fontName = fontName;
        font = new Font(fontName, fontStyle, size);
    }

    public BaseFontWatermark(Font font, Float transparency, Integer size, Integer width, Integer height) {
        super(transparency, size, width, height);
        this.font = font;
    }


    public String getLineSign() {
        return lineSign;
    }

    public void setLineSign(String lineSign) {
        this.lineSign = lineSign;
    }

    public Float getBackgroundTransparency() {
        return backgroundTransparency;
    }

    public void setBackgroundTransparency(Float backgroundTransparency) {
        this.backgroundTransparency = backgroundTransparency;
    }

    public Float getDegree() {
        return degree;
    }

    public void setDegree(Float degree) {
        this.degree = degree;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(String backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getFontText() {
        return fontText;
    }

    public String getFontName() {
        return fontName;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }
}
