package com.example.calendar.utils;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 *自定义阴影类.
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-13
 */
public class MyShadow extends DropShadow {
    public MyShadow(){
        setBlurType(BlurType.GAUSSIAN);
        setColor(Color.rgb(0,0,0,0.3));
        setRadius(12);
    }
}
