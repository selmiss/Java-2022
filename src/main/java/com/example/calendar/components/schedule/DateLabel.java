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
    public DateLabel(String var1,int i){
        switch (i){
            case 0:
                setText("一"+"\n"+var1);
                break;
            case 1:
                setText("二"+"\n"+var1);
                break;
            case 2:
                setText("三"+"\n"+var1);
                break;
            case 3:
                setText("四"+"\n"+var1);
                break;
            case 4:
                setText("五"+"\n"+var1);
                break;
            case 5:
                setText("六"+"\n"+var1);
                break;
            case 6:
                setText("日"+"\n"+var1);
                break;
            default:
                setText(var1);
                break;

        }
        setPrefWidth(36);
        setPrefHeight(60);
        setStyle("-fx-font-size: 10;" +
                "font-width: normal;" +
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #D8D8D8;" +
                "-fx-border-width: 0.5");
        setAlignment(Pos.CENTER);
        setWrapText(true);
    }
}