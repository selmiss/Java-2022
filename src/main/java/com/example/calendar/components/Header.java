package com.example.calendar.components;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.function.Consumer;


/**
 * 顶部标题栏.
 * <p>显示时间和一些提醒.</p>
 * @author 郭一帆
 * @version 1.0
 * @since 2022.6.12
 */
public class Header extends AnchorPane {
    public Header(){
        Text text1 = new Text("what's your plan today");
        text1.setX(10);
        text1.setY(20);
        text1.setFont(Font.font("Microsoft YaHei",10));
        TimeText text2 = new TimeText();
        text2.setX(10);
        text2.setY(45);
        text2.setFont(Font.font("Microsoft YaHei",FontWeight.BOLD,14));
        Text text3 = new Text("来看看有什么需要做的");
        text3.setX(10);
        text3.setY(70);
        text3.setFont(Font.font("Microsoft YaHei",14));

        getChildren().addAll(text1,text2,text3);
    }

    /**
     * 在header上添加按钮.
     * <p>由于在不同页面上header右侧的按钮功能不同，因此每次切换页面都需要一并切换右侧的按钮.</p>
     * @param newButton 标题栏右侧按钮
     */
    public void addButton(CircleButton newButton){
        newButton.setLayoutX(250);
        newButton.setLayoutY(52);
        newButton.setMinSize(25,25);
        this.getChildren().forEach(new Consumer<Node>() {
            @Override
            public void accept(Node node) {
                if(node instanceof CircleButton)
                    getChildren().remove(node);
            }
        });
        this.getChildren().add(newButton);
    }
}

/**
 * 显示时间的文本.
 * @author 郭一帆
 * @version 1.0
 * @since 2022.6.12
 */
class TimeText extends Text{
    public TimeText(){
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        setText("现在是："+df.format(new Date()));

        EventHandler<ActionEvent> eventHandler = e->{
            setText("现在是："+df.format(new Date()));
        };
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000),eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
}



