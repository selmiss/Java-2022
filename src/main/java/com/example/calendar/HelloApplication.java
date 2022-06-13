package com.example.calendar;

import com.example.calendar.components.*;
import com.example.calendar.components.todo.TodoDetail;
import com.example.calendar.components.todo.TodoLists;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * 启动应用.
 * <p>包括窗口的一些基础设置以及基本框架搭建.</p>
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-12
 */
public class HelloApplication extends Application {

    static BorderPane borderPane = new BorderPane();//最外层布局

    static Navigate navigate = new Navigate();//底部导航栏
    static Header header = new Header();//顶部标题栏
    static TodoDetail todoDetail = new TodoDetail();//待办事项详细
    static TodoLists todoLists = new TodoLists();//待办事项列表

    static CircleButton addItemButton = new CircleButton("file:src/main/resources/com/example/calendar/images/addButton.png");
    static CircleButton confirmButton = new CircleButton("file:src/main/resources/com/example/calendar/images/arrow.png");

    /**
     * 启动函数.
     * <p>窗口基础设置、添加布局、初始化页面元素.</p>
     * @param stage
     * @throws IOException
     */
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


        //当点击文本框以外的地方时转移焦点，从而使得文本框可以通过判断焦点失去事件而移除
        borderPane.addEventHandler(MouseDragEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                borderPane.requestFocus();
            }
        });
        borderPane.setBackground(new Background(new BackgroundFill(Color.web("#F1F9EE"), CornerRadii.EMPTY, Insets.EMPTY)));

        //添加控件
        borderPane.setBottom(navigate);
        header.addButton(addItemButton);
        borderPane.setTop(header);
        borderPane.setCenter(todoLists);

        //显示
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();

        //添加事件
        addItemButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                borderPane.setCenter(todoDetail);
                header.removeButton(addItemButton);
                header.addButton(confirmButton);
            }
        });
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                borderPane.setCenter(todoLists);
                header.removeButton(confirmButton);
                header.addButton(addItemButton);
            }
        });
    }


    public static void main(String[] args) {
        launch();
    }
}