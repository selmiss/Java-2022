package com.example.calendar;

import com.example.calendar.components.CircleButton;
import com.example.calendar.components.Header;
import com.example.calendar.components.Navigate;
import com.example.calendar.components.TodoDetail;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //stage基础设置
        stage.setResizable(false);
        //stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setHeight(550);
        stage.setWidth(300);
        stage.setX(1100);
        stage.setY(80);

        /**
         * 第一个场景-待办事项列表
         */
        //新增事项按钮
        CircleButton addButton = new CircleButton("file:src/main/resources/com/example/calendar/images/addButton.png");
        Header header1 = new Header(addButton);
        Navigate navigate1 = new Navigate(1);
        BorderPane borderPane1 = new BorderPane();
        borderPane1.setBackground(new Background(new BackgroundFill(Color.web("#F1F9EE"), CornerRadii.EMPTY, Insets.EMPTY)));

        borderPane1.setTop(header1);
        borderPane1.setBottom(navigate1);
        Scene todoListScene = new Scene(borderPane1);

        /**
         * 第二个场景-待办事项详细
         */
        //确认保存按钮
        CircleButton comfirmButton = new CircleButton("file:src/main/resources/com/example/calendar/images/confirmButton.png");

        Header header2 = new Header(comfirmButton);
        Navigate navigate2 = new Navigate(1);
        TodoDetail todoDetail = new TodoDetail();
        BorderPane borderPane2 = new BorderPane();
        borderPane2.setBackground(new Background(new BackgroundFill(Color.web("#F1F9EE"), CornerRadii.EMPTY, Insets.EMPTY)));
        borderPane2.setTop(header2);
        borderPane2.setCenter(todoDetail);
        borderPane2.setBottom(navigate2);
        //当点击文本框以外的地方时转移焦点，从而使得文本框可以通过判断焦点失去事件而移除
        borderPane2.addEventHandler(MouseDragEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                borderPane2.requestFocus();
            }
        });

        Scene todoDetailScene = new Scene(borderPane2);

        stage.setScene(todoListScene);

        /**
         * 添加事件
         */
        //新增事项
        addButton.setOnMouseClicked(event->{
            System.out.println(todoDetailScene.getRoot().getChildrenUnmodifiable());
            stage.setScene(todoDetailScene);
        });
        //确认保存
        comfirmButton.setOnMouseClicked(event->{
            stage.setScene(todoListScene);
        });

        /**
         * 显示
         */
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}