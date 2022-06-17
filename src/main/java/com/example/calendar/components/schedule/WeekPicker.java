package com.example.calendar.components.schedule;

import com.example.calendar.components.Navigate;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 周数选择器.
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-14
 */
public class WeekPicker extends ScrollPane {
    Label src;
    public WeekPicker(Label src){
        this.src = src;

        setHbarPolicy(ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollBarPolicy.NEVER);
        getStylesheets().add("file:src/main/resources/com/example/calendar/css/round.css");
        setEffect(new InnerShadow(5,Color.rgb(0,0,0,0.3)));

        VBox vBox = new VBox();
        vBox.setPrefHeight(150);
        vBox.setSpacing(10);
        for(String s: Schedule.weeks){
            Label label = new Label();
            label.setText(s);
            label.setAlignment(Pos.CENTER);
            label.setPrefWidth(280);
            label.setPadding(new Insets(5,0,5,0));
            label.setStyle("-fx-font-size: 16;");
            label.setOnMouseClicked(new LabelClickHandler());
            label.setOnMouseEntered(new MouseEnterHandler());
            label.setOnMouseExited(new MouseExitHandler());
            vBox.getChildren().add(label);
        }

        setContent(vBox);
    }

    /**
     * 处理标签点击事件.
     * @author 郭一帆
     * @version 1.0
     * @since 2022-6-14
     */
    private class LabelClickHandler implements EventHandler {

        @Override
        public void handle(Event event) {
            Label selectedLabel = (Label)event.getSource();
            src.setText(selectedLabel.getText());

            Stage stage = (Stage)getScene().getWindow();
            stage.close();
        }
    }

    /**
     * 处理标签鼠标移入事件.
     * @author 郭一帆
     * @version 1.0
     * @since 2022-6-14
     */
    private class MouseEnterHandler implements EventHandler{

        @Override
        public void handle(Event event) {
            Label label = (Label)event.getSource();
            label.setStyle("-fx-font-size: 18;");
        }
    }

    /**
     * 处理标签鼠标移出事件.
     * @author 郭一帆
     * @version 1.0
     * @since 2022-6-14
     */
    private class MouseExitHandler implements EventHandler{

        @Override
        public void handle(Event event) {
            Label label = (Label)event.getSource();
            label.setStyle("-fx-font-size: 16;");
        }
    }
}
