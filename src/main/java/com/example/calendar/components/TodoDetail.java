package com.example.calendar.components;

import com.example.calendar.HelloApplication;
import com.example.calendar.utils.TimeDiff;
import com.example.calendar.utils.TimePicker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.FocusModel;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoDetail extends AnchorPane {
    private AnchorPane todoContent = new AnchorPane();
    private Label title = new Label("添加标题");
    private Label ddl = new Label();
    private Label detail = new Label("添加内容");

    public TodoDetail(){
        //通用css
        String css = "";

        //基础设置

        todoContent.setPrefWidth(280);
        todoContent.setPrefHeight(380);
        todoContent.setLayoutX(10);
        todoContent.setLayoutY(10);
        todoContent.setStyle("-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 40");

        //添加阴影
        DropShadow shadow = new DropShadow ();
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.rgb(0,0,0,0.1));
        shadow.setRadius(8);

        todoContent.setEffect(shadow);

        //内容
        title.setPrefWidth(240);
        title.setPrefHeight(40);
        title.setLayoutX(20);
        title.setLayoutY(20);
        title.setAlignment(Pos.CENTER);
        title.setStyle("-fx-background-color: #F1F1F1;" +
                "-fx-background-radius: 15;" +
                "-fx-font-size: 14;");

        DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        ddl.setText(df.format(new Date()));
        ddl.setPrefWidth(110);
        ddl.setPrefHeight(40);
        ddl.setLayoutX(150);
        ddl.setLayoutY(80);
        ddl.setAlignment(Pos.CENTER);
        ddl.setTextFill(Color.valueOf("#E45C5C"));
        ddl.setCursor(Cursor.HAND);
        ddl.setStyle("-fx-background-color: #F1F1F1;" +
                "-fx-background-radius: 15;" +
                "-fx-font-size: 10;"+
                "-fx-font-weight: bolder");

        CountDown countDown = new CountDown(ddl);
        countDown.setPrefWidth(110);
        countDown.setPrefHeight(40);
        countDown.setLayoutX(20);
        countDown.setLayoutY(80);
        countDown.setAlignment(Pos.CENTER);
        countDown.setTextFill(Color.valueOf("#E45C5C"));
        countDown.setStyle("-fx-background-color: #F1F1F1;" +
                "-fx-background-radius: 15;" +
                "-fx-font-size: 16;" +
                "-fx-font-weight: bolder");


        detail.setPrefWidth(240);
        detail.setPrefHeight(200);
        detail.setLayoutX(20);
        detail.setLayoutY(140);
        detail.setWrapText(true);
        detail.setAlignment(Pos.TOP_LEFT);
        detail.setStyle("-fx-background-color: #F1F1F1;" +
                "-fx-background-radius: 15;" +
                "-fx-font-size: 12;" +
                "-fx-label-padding: 10" );

        //事件
        title.addEventHandler(MouseDragEvent.MOUSE_CLICKED,new TextEventHandler());
        detail.addEventHandler(MouseDragEvent.MOUSE_CLICKED,new TextEventHandler());
        ddl.addEventHandler(MouseDragEvent.MOUSE_CLICKED,new TimeEventHandler());

        todoContent.getChildren().addAll(title,countDown,ddl,detail);
        getChildren().add(todoContent);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getDdl() {
        return ddl.getText();
    }

    public String getDetail() {
        return detail.getText();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setDdl(String ddl) {
        this.ddl.setText(ddl);
    }

    public void setDetail(String detail) {
        this.detail.setText(detail);
    }

    private class TextEventHandler implements EventHandler {

        @Override
        public void handle(Event event) {
            Object source = event.getSource();
            Label src = (Label)source;
            TextArea textArea = new TextArea();
            //基础设置
            textArea.setWrapText(true);
            //用输入框覆盖标签
            textArea.setMinSize(90,20);
            textArea.setPrefWidth(src.getPrefWidth()-12);
            textArea.setPrefHeight(src.getPrefHeight()-12);
            textArea.setLayoutY(src.getLayoutY()+6);
            textArea.setLayoutX(src.getLayoutX()+6);
            textArea.setStyle(
                    src.getStyle());
            textArea.setText(src.getText());
            textArea.selectAll();

            //失去焦点时消失事件
            textArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                    if(oldValue&&!newValue)
                    {
                        src.setText(textArea.getText());
                        todoContent.getChildren().remove(textArea);
                    }

                }
            });

            todoContent.getChildren().add(textArea);
        }
    }

    private class TimeEventHandler implements EventHandler{

        @Override
        public void handle(Event event) {
            Object source = event.getSource();
            Label src = (Label)source;
            TimePicker timePicker = new TimePicker(src);

            //打开新的窗口选择时间
            Stage timeStage = new Stage();
            timeStage.initStyle(StageStyle.UNDECORATED);
            Scene timeScene = new Scene(timePicker);
            timeStage.setScene(timeScene);
            timeStage.show();
        }
    }
}

class CountDown extends Label{
    public CountDown( Label ddl){
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        EventHandler<ActionEvent> eventHandler = e->{
            try {
                Date ddltime = df.parse(ddl.getText());
                setText(new TimeDiff(new Date(),ddltime).toString());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

        };
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000),eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
}


