package com.borcade.utils;

/**
 * @author brocade
 */

public enum WatermarkTypeEnum {

    FontWatermark((byte) 0),
    ImageWatermark((byte) 1);

    private Byte code;

    WatermarkTypeEnum(Byte code) {
        this.code = code;
    }

}
