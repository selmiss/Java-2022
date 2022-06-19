package com.example.calendar.components.todo;

import com.example.calendar.Entity.Item;
import com.example.calendar.HelloApplication;
import com.example.calendar.components.CircleButton;
import com.example.calendar.components.Header;
import com.example.calendar.controller.UserOpController;
import com.example.calendar.utils.MyShadow;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DialogEvent;
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
import java.util.List;
import java.util.Stack;

/**
 * 三种类型的待办事项列表的集合.
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-13
 */
public class TodoLists extends ScrollPane {

    VBox vBox = new VBox();
    TodoList badTodoList = new TodoList();
    TodoList normalTodoList = new TodoList();
    TodoList happyTodoList = new TodoList();

    public TodoLists(List<Item> item_arr){
        //基础设置

        getStylesheets().add("file:src/main/resources/com/example/calendar/css/scroll.css");
        setBackground(new Background(new BackgroundFill(Color.web("#F1F9EE"), CornerRadii.EMPTY, Insets.EMPTY)));
        setHbarPolicy(ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollBarPolicy.NEVER);


        vBox.setPadding(new Insets(0,5,20,10));
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#F1F9EE"), CornerRadii.EMPTY, Insets.EMPTY)));
        badTodoList = new TodoList("bad",item_arr);
        normalTodoList = new TodoList("normal",item_arr);
        happyTodoList = new TodoList("happy",item_arr);
        vBox.getChildren().addAll(badTodoList,normalTodoList,happyTodoList);
        this.setContent(vBox);
    }

    public void setPaneAndDetail(BorderPane borderPane , TodoDetail todoDetail, Header header){
        badTodoList.setPaneAndDetail(borderPane, todoDetail, header);
        normalTodoList.setPaneAndDetail(borderPane, todoDetail, header);
        happyTodoList.setPaneAndDetail(borderPane, todoDetail, header);
    }
}

/**
 * 待办事项列表类.
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-13
 */
class TodoList extends VBox{

    BorderPane borderPane = null;
    TodoDetail todoDetail = null;
    Header header = null;

    public Stack<TodoItem> itemStack;
    private boolean isCollected=false;
    UserOpController userOpController = new UserOpController();
    public TodoList()
    {

    }
    public TodoList(String type, List<Item> item_list){
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

        //设置展开收起
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
        if(type.equals("bad")){
            label = new Label("紧急事项");
            label.setTextFill(Color.valueOf("#E45C5C"));
            //item = new TodoItem(2,);
        }
        else if(type.equals("normal"))
        {
            label = new Label("一般事项");
            label.setTextFill(Color.valueOf("#5C92E4"));
            //item = new TodoItem(1);
        }
        else{
            label = new Label("宽松事项");
            label.setTextFill(Color.valueOf("#31B02F"));
            //item = new TodoItem(0);
        }
        title.getChildren().addAll(emotionIcon1,label,emotionIcon2);
        getChildren().addAll(title);
        Date now = new Date();
        for(Item e : item_list)
        {
            TodoItem item2;
            long diff = e.getDate().getTime() - now.getTime();
            long days = diff / (1000*60*60*24);
            if(type.equals("bad") && days <= 1 ){
                item2 = new TodoItem(2,e, item_list);
            }
            else if(type.equals("normal") && days>1 && days<=6)
            {
                item2 = new TodoItem(1,e, item_list);
            }
            else if(type.equals("happy") && days > 6){
                item2 = new TodoItem(0,e, item_list);
            }
            else continue;
            //设置删除监听事件 两个按钮都是删除
            item2.finishButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    for(int i=0;i < item_list.size();i++)
                    {
                        if(item_list.get(i).getId() == e.getId())
                        {
                            item_list.remove(i);
                            System.out.println("找到删除项!");
                            userOpController.deleteTodoItem(e,item_list);
                            getChildren().remove(item2);
                            break;
                        }
                    }
                    itemStack.remove(item2);
                }
            });
            item2.deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    for(int i=0;i < item_list.size();i++)
                    {
                        if(item_list.get(i).getId() == e.getId())
                        {
                            item_list.remove(i);
                            System.out.println("找到删除项!");
                            userOpController.deleteTodoItem(e,item_list);
                            getChildren().remove(item2);
                            break;
                        }
                    }
                    itemStack.remove(item2);
                }
            });
            itemStack.add(item2);
            getChildren().add(item2);
        }
    }
    public void setPaneAndDetail(BorderPane borderPane ,TodoDetail todoDetail,Header header ){
        this.borderPane = borderPane;
        this.todoDetail = todoDetail;
        this.header = header;
        for(TodoItem todoItem :itemStack){
            todoItem.addEventHandler(MouseDragEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    todoDetail.setItem(todoItem.getItem());
                    borderPane.setCenter(todoDetail);
                    borderPane.setTop(header);
                }
            });
        }

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
    CircleButton finishButton;
    CircleButton deleteButton;
    private Item item;
    public TodoItem(int st, Item item, List<Item> item_list){
        this.state = st;
        this.item = item;
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
        String item_title = item.getTitle();
        Label title = new Label(item_title);
        title.setLayoutY(10);
        title.setMaxWidth(100);
        title.setLayoutX(60);
        title.setAlignment(Pos.CENTER);
        title.setStyle(
                "-fx-font-size: 14;" +
                "-fx-font-weight: normal");

        //截止时间
        Label ddl = new Label();
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        ddl.setText(df.format(item.getDate()));
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
        countDown.setLayoutX(170);
        countDown.setMaxWidth(70);
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

    public Item getItem() {
        return item;
    }
}


