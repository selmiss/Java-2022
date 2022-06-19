package com.example.calendar.components.notes;

import com.example.calendar.Entity.Idea;
import com.example.calendar.components.CircleButton;
import com.example.calendar.controller.UserOpController;
import com.example.calendar.utils.MyShadow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 笔记列表类.
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-16
 */
public class NoteList extends AnchorPane
{
    HBox notesNavigate = new HBox();//选择显示笔记的创建时间
    ScrollPane scrollPane = new ScrollPane();
    VBox notesBox = new VBox();
    UserOpController userOpController= new UserOpController();
    public NoteList(List<Idea> idea_list){
        //基础设置
        setMaxHeight(340);
        //滚动面板
        scrollPane.getStylesheets().add("file:src/main/resources/com/example/calendar/css/scroll.css");
        scrollPane.setBackground(new Background(new BackgroundFill(Color.web("#F1F9EE"), CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setLayoutX(4);
        scrollPane.setMaxHeight(420);

        notesBox.setPadding(new Insets(10,5,20,5));
        notesBox.setSpacing(10);
        notesBox.setBackground(new Background(new BackgroundFill(Color.web("#F1F9EE"), CornerRadii.EMPTY, Insets.EMPTY)));
        for(Idea i:idea_list)
        {
            Note idea1 = new Note(i);
            idea1.deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    userOpController.deleteIdea(i,idea_list);
                    notesBox.getChildren().remove(idea1);
                }
            });
            idea1.noteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    userOpController.deleteIdea(i,idea_list);
                    notesBox.getChildren().remove(idea1);
                }
            });
            notesBox.getChildren().add(idea1);
        }


        scrollPane.setContent(notesBox);

        getChildren().add(scrollPane);
    }
}

/**
 * 待办事项类.
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-13
 */
class Note extends AnchorPane{
    CircleButton noteButton;
    CircleButton deleteButton;
    public Note(Idea idea){
        //基础设置
        setPrefWidth(100);
        setPrefHeight(50);
        setPadding(new Insets(5,10,5,10));
        setStyle("-fx-background-color: #FFF8F8;" +
                "-fx-background-radius: 15;" +
                "-fx-font-size: 16;" +
                "-fx-font-weight: normal;" +
                "-fx-spacing: 20;");
        setEffect(new MyShadow());

        //已完成按钮

        noteButton = new CircleButton("file:src/main/resources/com/example/calendar/images/confirm/green.png");
        noteButton.setLayoutX(20);
        noteButton.setLayoutY(10);

        //笔记标题
        Label title = new Label(idea.getTitle());
        title.setLayoutY(10);
        title.setLayoutX(60);
        title.setAlignment(Pos.CENTER);
        title.setStyle(
                "-fx-font-size: 14;" +
                        "-fx-font-weight: normal");

        //创建或修改时间
        Label updateDate = new Label();
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        updateDate.setText(df.format(new Date()));
        updateDate.setLayoutY(30);
        updateDate.setLayoutX(60);
        updateDate.setAlignment(Pos.CENTER);
        updateDate.setStyle(
                "-fx-font-size: 10;-fx-font-weight: normal");

        updateDate.setTextFill(Color.valueOf("#31B02F"));


        //删除按钮

            deleteButton = new CircleButton("file:src/main/resources/com/example/calendar/images/delete/green.png");
        deleteButton.setLayoutX(240);
        deleteButton.setLayoutY(10);

        getChildren().addAll(noteButton,title,updateDate,deleteButton);
    }
}
