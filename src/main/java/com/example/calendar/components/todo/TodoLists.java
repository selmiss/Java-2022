package com.example.calendar.components.todo;

import com.example.calendar.components.CircleButton;
import com.example.calendar.utils.MyShadow;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.GestureEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

/**
 * 三种类型的待办事项列表的集合.
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-13
 */
public class TodoLists extends ScrollPane {

    VBox vBox = new VBox();
    TodoList badTodoList = new TodoList("bad");
    TodoList normalTodoList = new TodoList("normal");
    TodoList happyTodoList = new TodoList("happy");

    public TodoLists(){
        //基础设置

        getStylesheets().add("file:src/main/resources/com/example/calendar/css/scroll.css");
        setBackground(new Background(new BackgroundFill(Color.web("#F1F9EE"), CornerRadii.EMPTY, Insets.EMPTY)));
        setHbarPolicy(ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollBarPolicy.NEVER);


        vBox.setPadding(new Insets(0,5,20,10));
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#F1F9EE"), CornerRadii.EMPTY, Insets.EMPTY)));
        TodoList badTodoList = new TodoList("bad");
        TodoList normalTodoList = new TodoList("normal");
        TodoList happyTodoList = new TodoList("happy");
        vBox.getChildren().addAll(badTodoList,normalTodoList,happyTodoList);
        this.setContent(vBox);
    }
}

/**
 * 待办事项列表类.
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-13
 */
class TodoList extends VBox{
    public Stack<TodoItem> itemStack;
    private boolean isCollected=false;

    public TodoList(String type){
        itemStack = new Stack<>();

        //基础设置
        setPadding(new Insets(10,0,5,0));
        setStyle("-fx-spacing: 10;");

        //标题
        HBox title = new HBox();
        title.setPadding(new Insets(5,65,5,65));
        title.setPrefHeight(30);
        title.setStyle("-fx-background-color: #FFF8F8;" +
                "-fx-background-radius: 15;" +
                "-fx-font-size: 16;" +
                "-fx-font-weight: normal;" +
                "-fx-spacing: 20;");
        title.setEffect(new MyShadow());
        title.addEventHandler(MouseDragEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for(TodoItem item:itemStack)
                {
                    if(!isCollected)
                        getChildren().remove(item);
                    else
                        getChildren().add(item);
                }
                isCollected = !isCollected;
            }
        });


        Image image = new Image("file:src/main/resources/com/example/calendar/images/emotion/"+type+".png");
        ImageView emotionIcon1 = new ImageView(image);
        ImageView emotionIcon2 = new ImageView(image);
        Label label;
        TodoItem item;
        if(type.equals("bad")){
            label = new Label("紧急事项");
            label.setTextFill(Color.valueOf("#E45C5C"));
            item = new TodoItem(2);
        }
        else if(type.equals("normal"))
        {
            label = new Label("一般事项");
            label.setTextFill(Color.valueOf("#5C92E4"));
            item = new TodoItem(1);
        }
        else{
            label = new Label("宽松事项");
            label.setTextFill(Color.valueOf("#31B02F"));
            item = new TodoItem(0);
        }

        TodoItem item2;
        if(type.equals("bad")){
            item2 = new TodoItem(2);
        }
        else if(type.equals("normal"))
        {
            item2 = new TodoItem(1);
        }
        else{
            item2 = new TodoItem(0);
        }

        itemStack.add(item);
        itemStack.add(item2);

        title.getChildren().addAll(emotionIcon1,label,emotionIcon2);
        getChildren().addAll(title,item,item2);
    }
}

/**
 * 待办事项类.
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-13
 */
class TodoItem extends AnchorPane{
    private int state=0;
    public TodoItem(int st){
        this.state = st;
        //基础设置
        setPrefWidth(100);
        setPrefHeight(50);
        setStyle("-fx-background-color: #FFF8F8;" +
                "-fx-background-radius: 15;" +
                "-fx-font-size: 16;" +
                "-fx-font-weight: normal;" +
                "-fx-spacing: 20;");
        setEffect(new MyShadow());

        //已完成按钮
        CircleButton finishButton;
        if(state==0)
        {
            finishButton = new CircleButton("file:src/main/resources/com/example/calendar/images/confirm/green.png");
        }
        else if(state ==1)
        {
            finishButton = new CircleButton("file:src/main/resources/com/example/calendar/images/confirm/blue.png");
        }
        else
        {
            finishButton = new CircleButton("file:src/main/resources/com/example/calendar/images/confirm/red.png");
        }
        finishButton.setLayoutX(20);
        finishButton.setLayoutY(10);

        //待办标题
        Label title = new Label("操作系统理论作业");
        title.setLayoutY(10);
        title.setLayoutX(60);
        title.setAlignment(Pos.CENTER);
        title.setStyle(
                "-fx-font-size: 14;" +
                "-fx-font-weight: normal");

        //截止时间
        Label ddl = new Label();
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        ddl.setText(df.format(new Date()));
        ddl.setLayoutY(30);
        ddl.setLayoutX(60);
        ddl.setAlignment(Pos.CENTER);
        ddl.setStyle(
                "-fx-font-size: 10;" +
                "-fx-font-weight: normal");
        switch (state){
            case 0:
                ddl.setTextFill(Color.valueOf("#31B02F"));
                break;
            case 1:
                ddl.setTextFill(Color.valueOf("#5C92E4"));
                break;
            case 2:
                ddl.setTextFill(Color.valueOf("#E45C5C"));
                break;
        }

        //剩余时间
        CountDown countDown = new CountDown(ddl);
        countDown.setLayoutY(10);
        countDown.setLayoutX(190);
        countDown.setAlignment(Pos.CENTER);
        countDown.setStyle(
                "-fx-font-size: 25;" +
                        "-fx-font-weight: normal");
        switch (state){
            case 0:
                countDown.setTextFill(Color.valueOf("#31B02F"));
                break;
            case 1:
                countDown.setTextFill(Color.valueOf("#5C92E4"));
                break;
            case 2:
                countDown.setTextFill(Color.valueOf("#E45C5C"));
                break;
        }

        //删除按钮
        CircleButton deleteButton;
        if(state==0)
        {
            deleteButton = new CircleButton("file:src/main/resources/com/example/calendar/images/delete/green.png");
        }
        else if(state ==1)
        {
            deleteButton = new CircleButton("file:src/main/resources/com/example/calendar/images/delete/blue.png");
        }
        else
        {
            deleteButton = new CircleButton("file:src/main/resources/com/example/calendar/images/delete/red.png");
        }
        deleteButton.setLayoutX(240);
        deleteButton.setLayoutY(10);

        getChildren().addAll(finishButton,title,ddl,countDown,deleteButton);
    }
}


