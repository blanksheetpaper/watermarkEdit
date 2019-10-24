package com.borcade;

import com.borcade.entity.BaseFontWatermark;
import com.borcade.entity.BaseImageWatermark;
import com.borcade.helpers.font.ComplexLineFontHelper;
import com.borcade.helpers.font.RectangleBackgroundFontHelper;
import com.borcade.helpers.font.RotateRectangleFontHelper;
import com.borcade.helpers.font.SimpleFontHelper;
import com.borcade.helpers.font.SimpleLineFontHelper;
import com.borcade.helpers.images.DesignationCropImageHelper;
import com.borcade.helpers.images.ProportionalCropImageHelper;
import com.borcade.helpers.images.SimpleImageWatermarkHelper;
import com.borcade.helpers.images.SimpleRotateImageHelper;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static Graphics2D graphics;
    private static BufferedImage read;
    private static URL resource;
    private static String waterPath;
    private static String testImagePath;

    static {
        try {
            resource = AppTest.class.getClassLoader().getResource("originalImage.png");
            waterPath = AppTest.class.getClassLoader().getResource("watermarkImage.png").getFile();
            testImagePath = new File(resource.toURI()).getParent() + "\\images\\";
            read = ImageIO.read(resource);
            graphics = read.createGraphics();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void simpleFontHelper() throws Exception {
        BaseFontWatermark baseFontWatermark = new BaseFontWatermark("你好" + "中国", 1, "宋体"
                , 0.8f, 80, 20, 50);
        baseFontWatermark.setFontColor("#002B36");
        SimpleFontHelper s = new SimpleFontHelper(baseFontWatermark);
        s.applyWatermarkOnPicture(read, graphics);
        graphics.dispose();
        write("/simpleFontHelper");
    }


    @Test
    public void simpleLineFontHelper() throws Exception {
        BaseFontWatermark baseFontWatermark = new BaseFontWatermark("你好<br>中国<br>我爱你", 1, "宋体"
                , 0.8f, 80, 20, 50);
        baseFontWatermark.setFontColor("#002B36");
        baseFontWatermark.setLineSign("<br>");
        SimpleLineFontHelper s = new SimpleLineFontHelper(baseFontWatermark);
        s.applyWatermarkOnPicture(read, graphics);
        graphics.dispose();
        write("/simpleLineFontHelper");
    }

    @Test
    public void rotateRectangleFontHelper() throws Exception {
        BaseFontWatermark baseFontWatermark = new BaseFontWatermark("你好中国", 1, "宋体"
                , 0.8f, 80, 10, 50);
        baseFontWatermark.setBackgroundTransparency(0.8F);
        // baseFontWatermark.setDegree(20F);
        baseFontWatermark.setDegree(-90F);
        baseFontWatermark.setX(100);
        baseFontWatermark.setY(500);
        baseFontWatermark.setBackGroundColor("#DC322F");
        baseFontWatermark.setFontColor("#002B36");
        // baseFontWatermark.setColor( new Color(Integer.valueOf("#09F768".substring(1), 16)));
        RotateRectangleFontHelper s = new RotateRectangleFontHelper(baseFontWatermark);
        s.applyWatermarkOnPicture(read, graphics);
        graphics.dispose();
        write("/rotateRectangleFontHelper");
    }


    @Test
    public void rectangleBackgroundFontHelper() throws Exception {
        BaseFontWatermark baseFontWatermark = new BaseFontWatermark("你好中国", 1, "宋体"
                , 0.8f, 80, 10, 50);
        baseFontWatermark.setBackgroundTransparency(1F);
        baseFontWatermark.setBackGroundColor("#DC322F");
        baseFontWatermark.setFontColor("#09F768");
        RectangleBackgroundFontHelper s = new RectangleBackgroundFontHelper(baseFontWatermark);
        s.applyWatermarkOnPicture(read, graphics);
        graphics.dispose();
        write("/rectangleBackgroundFontHelper");
    }


    @Test
    public void complexLineFontHelper() throws Exception {
        BaseFontWatermark baseFontWatermark = new BaseFontWatermark("你好中国<br><br><br>我爱你<br>I LOVE YOU", 1, "宋体"
                , 0.8f, 80, 10, 50);
        baseFontWatermark.setBackgroundTransparency(0.8F);
        baseFontWatermark.setLineSign("<br>");
        // baseFontWatermark.setDegree(20F);
        baseFontWatermark.setDegree(-90F);
        baseFontWatermark.setX(100);
        baseFontWatermark.setY(500);
        baseFontWatermark.setBackGroundColor("#DC322F");
        baseFontWatermark.setFontColor("#002B36");
        // baseFontWatermark.setColor( new Color(Integer.valueOf("#09F768".substring(1), 16)));
        ComplexLineFontHelper s = new ComplexLineFontHelper(baseFontWatermark);
        s.applyWatermarkOnPicture(read, graphics);
        graphics.dispose();
        write("/complexLineFontHelper");
    }

    @Test
    public void simpleImageWatermarkHelper() throws Exception {
        System.out.println(waterPath);
        BaseImageWatermark baseImageWatermark = new BaseImageWatermark(1f, 100, 20, 20
                , new File(waterPath));
        SimpleImageWatermarkHelper simpleImageWatermarkHelper = new SimpleImageWatermarkHelper(baseImageWatermark);
        simpleImageWatermarkHelper.applyWatermarkOnPicture(read, graphics);
        write("/simpleImageWatermarkHelper");
    }

    @Test
    public void proportionalCropImageHelper() throws Exception {
        System.out.println(waterPath);
        BaseImageWatermark baseImageWatermark = new BaseImageWatermark(1f, 300, 20, 20
                , new File(waterPath));
        ProportionalCropImageHelper proportionalCropImageHelper = new ProportionalCropImageHelper(baseImageWatermark);
        proportionalCropImageHelper.applyWatermarkOnPicture(read, graphics);
        write("/proportionalCropImageHelper");
    }


    @Test
    public void designationCropImageHelper() throws Exception {
        BaseImageWatermark baseImageWatermark = new BaseImageWatermark(1f, 300, 40, 30
                , new File(waterPath));
        baseImageWatermark.setNewWatermarkHeight(200);
        baseImageWatermark.setNewWatermarkWidth(300);
        DesignationCropImageHelper designationCropImageHelper = new DesignationCropImageHelper(baseImageWatermark);
        designationCropImageHelper.applyWatermarkOnPicture(read, graphics);
        graphics.dispose();
        write("/designationCropImageHelper");
    }

    @Test
    public void simpleRotateImageHelper() throws Exception {
        BaseImageWatermark baseImageWatermark = new BaseImageWatermark(1f, 300, 40, 30
                , new File(waterPath));
        baseImageWatermark.setDegree(50f);
        baseImageWatermark.setX(100f);
        baseImageWatermark.setY(200f);
        SimpleRotateImageHelper designationCropImageHelper = new SimpleRotateImageHelper(baseImageWatermark);
        designationCropImageHelper.applyWatermarkOnPicture(read, graphics);
        graphics.dispose();
        write("/simpleRotateImageHelper");
    }

    private void write(String name) throws IOException {
        File file = new File(testImagePath);
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(file.getPath() + name + resource.getPath().substring(resource.getPath().lastIndexOf(".")));
        if (!file.exists()) {
            file.createNewFile();
        }
        System.out.println("the path of watermark :------------->" + file.getAbsolutePath());
        ImageIO.write(read, resource.getPath().substring(resource.getPath().lastIndexOf(".") + 1), new FileOutputStream(file));
    }
}
