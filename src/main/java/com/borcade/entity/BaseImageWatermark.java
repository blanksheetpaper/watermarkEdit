package com.borcade.entity;

import java.io.File;
import java.io.Serializable;
import java.net.URL;

/**
 * 〈图片水印〉
 *
 * @author brocade
 * @since JDK1.8
 */
public class BaseImageWatermark extends BaseWatermark implements Serializable {

    private static final long serialVersionUID = -1107479578703043083L;
    /**
     * 水印图片的地址
     * <p>file|url其中有一个有值即可</p>
     * <p>如果当FILE和URL两个都存在时,使用URL</p>
     */
    private File file;
    /**
     * 水印图片的网络地址
     * <p>file|url其中有一个有值即可</p>
     * <p>如果当FILE和URL两个都存在时,使用URL</p>
     */
    private URL url;
    /**
     * 新的水印图片宽度
     */
    private Integer newWatermarkWidth;
    /**
     * 新的水印图片高度
     */
    private Integer newWatermarkHeight;
    /**
     * 旋转角度
     */
    private Float degree;

    private Float x;

    private Float y;


    public BaseImageWatermark(Float transparency, Integer size, Integer width, Integer height, File file) {
        super(transparency, size, width, height);
        this.file = file;
    }

    public BaseImageWatermark(Float transparency, Integer size, Integer width, Integer height, URL url) {
        super(transparency, size, width, height);
        this.url = url;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getDegree() {
        return degree;
    }

    public void setDegree(Float degree) {
        this.degree = degree;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Integer getNewWatermarkWidth() {
        return newWatermarkWidth;
    }

    public void setNewWatermarkWidth(Integer newWatermarkWidth) {
        this.newWatermarkWidth = newWatermarkWidth;
    }

    public Integer getNewWatermarkHeight() {
        return newWatermarkHeight;
    }

    public void setNewWatermarkHeight(Integer newWatermarkHeight) {
        this.newWatermarkHeight = newWatermarkHeight;
    }
}
