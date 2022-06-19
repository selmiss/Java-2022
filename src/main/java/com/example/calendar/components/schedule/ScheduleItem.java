package com.example.calendar.components.schedule;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * 课程类.
 * <p>显示课程名、上课地点.</p>
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-14
 */
public class ScheduleItem extends AnchorPane {
    String[] colors = new String[]{"#FF9F9F","#8bc34a","#5C92E4","#E45C5C","#ce93d8","#ff6e40"};
    public int x,y,h;
    Label title = new Label();
    Label location = new Label();
    public ScheduleItem(int x,int y,int h,String content){
        this.x = x;
        this.y = y;
        this.h = h;

        int cNum = (x+y*3+2*x*y)%6;

        setBackground(new Background(new BackgroundFill(Color.valueOf(colors[cNum]),new CornerRadii(8),new Insets(0,0,0,0))));
        setPrefWidth(36);
        setPrefHeight(60*h);
        String Title = "";
        String Text = "";
        Title = content.split("\n")[0];
        try {

            Text = content.split("\n")[1];
        }catch (Exception fe){//System.out.println("课程名字转换不完全");
        }
        //课程名设置
        title.setStyle("-fx-font-size: 12;" +
                "font-width: normal;");
        title.setTextFill(Color.WHITE);
        title.setText(Title);
        title.setPrefWidth(36);
        title.setWrapText(true);
        title.setLayoutY(10*h);
        title.setMaxHeight(40);

        //上课地点设置
        location.setStyle("-fx-font-size: 12;" +
                "font-width: normal;");
        location.setTextFill(Color.WHITE);
        location.setText(Text);
        location.setPrefWidth(36);
        location.setWrapText(true);
        location.setLayoutY(30*h);
        location.setMaxHeight(40);

        getChildren().addAll(title,location);
    }
}