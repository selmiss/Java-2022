package com.example.calendar.components;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Navigate extends AnchorPane {
    public Navigate(int index){
        //基础设置
        setStyle("-fx-background-color: #FFFFFF");
        Button button1 = null;
        Button button2 = null;
        Button button3 = null;

        //添加导航按钮
        if(index==1)
            button1 = new NavigateButton("1",true);
        else
            button1 = new NavigateButton("1",false);
        button1.setLayoutX(0);
        if(index==2)
            button2 = new NavigateButton("2",true);
        else
            button2 = new NavigateButton("2",false);
        button2.setLayoutX(100);
        if(index==3)
            button3 = new NavigateButton("3",true);
        else
            button3 = new NavigateButton("3",false);
        button3.setLayoutX(200);
        getChildren().add(button1);
        getChildren().add(button2);
        getChildren().add(button3);

    }
}
class NavigateButton extends Button{
    public NavigateButton(String var1,boolean isActive){
        super(var1);
        //修改样式
        if(isActive)
            this.setStyle("" +
                    "-fx-background-radius: 0;" +
                    "-fx-background-color: #AAAAAA;" +
                    "-fx-border-style: solid none none none;" +
                    "-fx-border-color: #999999;" +
                    "-fx-border-width: 1;" +
                    "-fx-background-insets:0;"+
                    "-fx-pref-width: 100;" +
                    "-fx-pref-height: 50;" +
                    "");
        else
            this.setStyle("" +
                    "-fx-background-radius: 0;" +
                    "-fx-background-color: #EEEEEE;" +
                    "-fx-border-style: solid none none none;" +
                    "-fx-border-color: #FFFFFF;" +
                    "-fx-border-width: 1;" +
                    "-fx-background-insets:0;"+
                    "-fx-pref-width: 100;" +
                    "-fx-pref-height: 50;" +
                    "");
        this.setCursor(Cursor.HAND);
        //添加阴影
        DropShadow shadow = new DropShadow ();
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.rgb(0,0,0,0.1));
        shadow.setRadius(8);
        shadow.setOffsetY(0);
        shadow.setOffsetX(3);
        setEffect(shadow);
    }
}
