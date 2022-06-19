package com.example.calendar.components.notes;

import com.example.calendar.Entity.Idea;
import com.example.calendar.components.CircleButton;
import com.example.calendar.components.Header;
import com.example.calendar.controller.UserOpController;
import com.example.calendar.utils.MyShadow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Stack;

/**
 * 笔记列表类.
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-16
 */
public class NoteList extends AnchorPane
{
    BorderPane borderPane = null;
    NoteDetail noteDetail = null;
    Header header = null;
    HBox notesNavigate = new HBox();//选择显示笔记的创建时间
    ScrollPane scrollPane = new ScrollPane();
    VBox notesBox = new VBox();
    Stack<Note> noteStack = new Stack<>();
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
            notesBox.getChildren().add(idea1);
            noteStack.add(idea1);
        }


        scrollPane.setContent(notesBox);

        getChildren().add(scrollPane);
    }

    public void setPaneAndDetail(BorderPane borderPane , NoteDetail noteDetail, Header header){
        this.borderPane = borderPane;
        this.noteDetail = noteDetail;
        this.header = header;
        for(Note noteItem :noteStack){
            noteItem.addEventHandler(MouseDragEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    noteDetail.setIdea(noteItem.getIdea());
                    borderPane.setCenter(noteDetail);
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
class Note extends AnchorPane{
    CircleButton noteButton;
    CircleButton deleteButton;
    Idea idea;
    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }
    public Note(Idea idea){
        this.idea = idea;
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

        noteButton = new CircleButton("file:src/main/resources/com/example/calendar/images/note.png");
        noteButton.setLayoutX(20);
        noteButton.setLayoutY(10);

        //笔记标题
        Label title = new Label(idea.getTitle());
        title.setLayoutY(15);
        title.setLayoutX(60);
        title.setMaxWidth(100);
        title.setAlignment(Pos.CENTER);
        title.setStyle(
                "-fx-font-size: 14;" +
                        "-fx-font-weight: normal");

        //内容简介
        String briefStr = idea.getContent();
        briefStr = briefStr.replace('#','\0');
        briefStr = briefStr.replace('`','\0');
        String[] strs =  briefStr.split("\n");
        System.out.println(strs);
        briefStr = "";
        int maxline=3;
        for(int i=0;i<maxline&&i<strs.length;i++)
        {
            if(strs[i].equals("")){
                maxline++;
                continue;
            }
            briefStr += strs[i]+"\n";
        }
        Label brief = new Label(briefStr);
        brief.setLayoutY(35);
        brief.setLayoutX(60);
        brief.setMaxWidth(100);
        brief.setAlignment(Pos.CENTER);
        brief.setStyle(
                "-fx-font-size: 12;-fx-font-weight: normal");

        brief.setTextFill(Color.valueOf("#31B02F"));


        //删除按钮
        deleteButton = new CircleButton("file:src/main/resources/com/example/calendar/images/delete/green.png");
        deleteButton.setLayoutX(240);
        deleteButton.setLayoutY(10);

        getChildren().addAll(noteButton,title,brief,deleteButton);
    }
}
