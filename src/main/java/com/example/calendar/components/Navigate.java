package com.example.calendar.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * 底部导航栏.
 * @author 郭一帆
 * @version 1.0
 * @since 2022.6.12
 */
public class Navigate extends AnchorPane {
    public NavigateButton[] buttons = new NavigateButton[3];
    public Navigate(){
        //基础设置
        setStyle("-fx-background-color: #FFFFFF");

        buttons[0] = new NavigateButton("file:src/main/resources/com/example/calendar/images/todo.png",true);
        buttons[1] = new NavigateButton("file:src/main/resources/com/example/calendar/images/schedule.png",false);
        buttons[2] = new NavigateButton("file:src/main/resources/com/example/calendar/images/notes.png",false);

        buttons[0].setLayoutX(0);
        buttons[1].setLayoutX(100);
        buttons[2].setLayoutX(200);
        getChildren().addAll(buttons);
    }
    public void activate(Button button){
        for(int i=0;i<3;i++)
        {
            if(button.equals(buttons[i]))
                buttons[i].activate();
            else
                buttons[i].deactivate();
        }
    }
    public void addListener(EventHandler<ActionEvent> handler1,EventHandler<ActionEvent> handler2,EventHandler<ActionEvent> handler3){
        buttons[0].setOnAction(handler1);
        buttons[1].setOnAction(handler2);
        buttons[2].setOnAction(handler3);
    }
}

/**
 * 导航栏里的按钮类.
 * @author 郭一帆
 * @version 1.0
 * @since 2022.6.12
 */
class NavigateButton extends Button{
    private Image image;
    public NavigateButton(String url,boolean isActive){
        image = new Image(url);
        //修改样式
        if(isActive) {
            this.setStyle("" +
                    "-fx-border-style: solid none none none;" +
                    "-fx-border-color: #999999;" +
                    "-fx-border-width: 1;" +
                    "-fx-pref-width: 100;" +
                    "-fx-pref-height: 50;" +
                    "");
            setBackground(new Background(
                    new BackgroundFill[]{new BackgroundFill(
                            Color.valueOf("#AAAAAA"),
                            new CornerRadii(0),
                            new Insets(0, 0, 0, 0))},
                    new BackgroundImage[]{new BackgroundImage(
                            image,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.CENTER,
                            new BackgroundSize(20, 20, false, false, false, false))}));
        }
        else{
            this.setStyle("" +
                    "-fx-border-style: solid none none none;" +
                    "-fx-border-color: #FFFFFF;" +
                    "-fx-border-width: 1;" +
                    "-fx-pref-width: 100;" +
                    "-fx-pref-height: 50;" +
                    "");
            setBackground(new Background(
                    new BackgroundFill[]{new BackgroundFill(
                            Color.valueOf("#EEEEEE"),
                            new CornerRadii(0),
                            new Insets(0,0,0,0))},
                    new BackgroundImage[]{new BackgroundImage(
                            image,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.CENTER,
                            new BackgroundSize(20, 20, false, false, false, false))}));
        }

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

    public void activate(){
        this.setStyle("" +
                "-fx-border-style: solid none none none;" +
                "-fx-border-color: #999999;" +
                "-fx-border-width: 1;" +
                "-fx-pref-width: 100;" +
                "-fx-pref-height: 50;" +
                "");
        setBackground(new Background(
                new BackgroundFill[]{new BackgroundFill(
                        Color.valueOf("#AAAAAA"),
                        new CornerRadii(0),
                        new Insets(0, 0, 0, 0))},
                new BackgroundImage[]{new BackgroundImage(
                        image,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(20, 20, false, false, false, false))}));
    }
    public void deactivate(){
        this.setStyle("" +
                "-fx-border-style: solid none none none;" +
                "-fx-border-color: #FFFFFF;" +
                "-fx-border-width: 1;" +
                "-fx-pref-width: 100;" +
                "-fx-pref-height: 50;" +
                "");
        setBackground(new Background(
                new BackgroundFill[]{new BackgroundFill(
                        Color.valueOf("#EEEEEE"),
                        new CornerRadii(0),
                        new Insets(0,0,0,0))},
                new BackgroundImage[]{new BackgroundImage(
                        image,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(20, 20, false, false, false, false))}));
    }
}
