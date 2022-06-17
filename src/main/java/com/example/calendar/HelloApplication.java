package com.example.calendar;

import com.example.calendar.Entity.Item;
import com.example.calendar.components.*;
import com.example.calendar.components.notes.NoteDetail;
import com.example.calendar.components.notes.NoteList;
import com.example.calendar.components.schedule.Schedule;
import com.example.calendar.components.todo.TodoDetail;
import com.example.calendar.components.todo.TodoLists;
import com.example.calendar.controller.TodoItemController;
import com.example.calendar.controller.UserOpController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


/**
 * 启动应用.
 * <p>包括窗口的一些基础设置以及基本框架搭建.</p>
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-12
 */
public class HelloApplication extends Application {
    // 全局变量添加
    List<Item> AllItem = new ArrayList<>();
    // 控制类添加
    TodoItemController todoItemController = new TodoItemController();

    BorderPane borderPane = new BorderPane();//最外层布局

    Navigate navigate = new Navigate();//底部导航栏
    Header header1 = new Header("what's your plan today","来看看有什么需要做的");//待办列表页面顶部标题栏
    Header header2 = new Header("what's your plan today","来看看有什么需要做的");//待办详细页面顶部标题栏
    Header header3 = new Header("you can take notes here","来记录此刻的灵感吧");//笔记列表页面顶部标题栏
    Header header4 = new Header("you can take notes here","来记录此刻的灵感吧");//笔记详细页面顶部标题栏
    TodoDetail todoDetail = new TodoDetail();//待办事项详细
    TodoLists todoLists = new TodoLists(AllItem);//待办事项列表
    Schedule schedule = new Schedule();//课程表
    NoteList noteList = new NoteList();//笔记列表
    NoteDetail noteDetail = new NoteDetail();//笔记详细

    CircleButton addItemButton = new CircleButton("file:src/main/resources/com/example/calendar/images/addButton.png");
    CircleButton confirmButton = new CircleButton("file:src/main/resources/com/example/calendar/images/arrow.png");
    CircleButton addNoteButton = new CircleButton("file:src/main/resources/com/example/calendar/images/addButton.png");
    CircleButton finishNoteButton = new CircleButton("file:src/main/resources/com/example/calendar/images/arrow.png");


    // 全局变量添加
    // 控制类添加
    UserOpController userOpController = new UserOpController();


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

        // 控制类测试
        System.out.println("Start our program!");
        AllItem = todoItemController.ReadItemList(); //初始化list
        todoLists = new TodoLists(AllItem);

//        todoItemController.ReadItemList();

        try{
//            userOpController.addTodoItem("数据库考试",new Date(2022, 06, 21),"不知道写什么content", 0, 17);
            userOpController.addTodoItem(todoItemController.ReadItemList());
        }catch (Exception e){System.out.println("userControllerError!");}

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
        header1.addButton(addItemButton);
        header2.addButton(confirmButton);
        header3.addButton(addNoteButton);
        header4.addButton(finishNoteButton);
        borderPane.setTop(header1);
        borderPane.setCenter(todoLists);

        //显示
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();

        //添加事件
        //转到添加item界面
        addItemButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                borderPane.setCenter(todoDetail);
                borderPane.setTop(header2);
            }
        });
        //转到list_item主页面
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                borderPane.setCenter(todoLists);
                borderPane.setTop(header1);
            }
        });
        //转到添加笔记界面
        addNoteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                borderPane.setCenter(noteDetail);
                borderPane.setTop(header4);
            }
        });
        //结束添加note界面
        finishNoteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                borderPane.setCenter(noteList);
                borderPane.setTop(header3);
            }
        });

        //导航栏监听事件
        EventHandler<ActionEvent> onTodoButtonClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                navigate.activate((Button) actionEvent.getSource());
                borderPane.setCenter(todoLists);
                borderPane.setTop(header1);
                //header.addButton(addItemButton);
            }
        };
        //导航栏监听事件
        EventHandler<ActionEvent> onScheduleButtonClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                navigate.activate((Button) actionEvent.getSource());
                borderPane.setCenter(schedule);
                borderPane.setTop(null);
            }
        };
        //导航栏监听事件
        EventHandler<ActionEvent> onNotesButtonClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                navigate.activate((Button) actionEvent.getSource());
                borderPane.setCenter(schedule);
                borderPane.setTop(header3);
                borderPane.setCenter(noteList);
            }
        };

        navigate.addListener(onTodoButtonClicked,onScheduleButtonClicked,onNotesButtonClicked);

    }


    public static void main(String[] args) {
        launch();
    }
}