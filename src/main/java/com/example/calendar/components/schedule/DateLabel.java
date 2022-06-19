package com.example.calendar.components.schedule;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 * 时间标签类.
 * <p>用来显示课表表头，包括日期、节数.</p>
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-14
 */
class DateLabel extends Label {
    public DateLabel(String var1){
        super(var1);
        setPrefWidth(36);
        setPrefHeight(60);
        setStyle("-fx-font-size: 12;" +
                "font-width: normal;" +
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #D8D8D8;" +
                "-fx-border-width: 0.5");
        setAlignment(Pos.CENTER);
        setWrapText(true);
    }
}