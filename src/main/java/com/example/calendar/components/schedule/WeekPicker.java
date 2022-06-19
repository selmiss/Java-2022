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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * 周数选择器.
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-14
 */
public class WeekPicker extends ScrollPane {
    Label src;
    HBox hBox;
    int index;
    GridPane gridPane;
    List<List<List<String>>> allList;
    public WeekPicker(Label src, HBox hBox, int index, GridPane gridPane, List<List<List<String>>> allList){
        this.src = src;
        this.hBox = hBox;
        this.index = index;
        this.gridPane = gridPane;
        this.allList = allList;

        setHbarPolicy(ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollBarPolicy.NEVER);
        getStylesheets().add("file:src/main/resources/com/example/calendar/css/round.css");
        setEffect(new InnerShadow(5,Color.rgb(0,0,0,0.3)));

        VBox vBox = new VBox();
        vBox.setPrefHeight(150);
        vBox.setSpacing(10);
        int i=0;
        for(String s: Schedule.weeks){
            WeekLabel label = new WeekLabel();
            label.setIndex(i);
            label.setText(s);
            label.setAlignment(Pos.CENTER);
            label.setPrefWidth(280);
            label.setPadding(new Insets(5,0,5,0));
            label.setStyle("-fx-font-size: 16;");
            label.setOnMouseClicked(new LabelClickHandler());
            label.setOnMouseEntered(new MouseEnterHandler());
            label.setOnMouseExited(new MouseExitHandler());
            vBox.getChildren().add(label);
            i++;
        }

        setContent(vBox);
    }

    /**
     *
     */
    private class WeekLabel extends Label{
        int index;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
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
            WeekLabel selectedLabel = (WeekLabel)event.getSource();
            src.setText(selectedLabel.getText());
            hBox.getChildren().clear();
            index = selectedLabel.getIndex();


            Calendar dayOfWeek = Calendar.getInstance();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dayOfWeek.setTime(df.parse("2022-02-28"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dayOfWeek.add(Calendar.WEEK_OF_YEAR,index);
            DateLabel empty = new DateLabel("",-1);
            empty.setPrefWidth(30);
            hBox.getChildren().add(empty);
            for(int i=0;i<7;i++)
            {
                DateLabel dateLabel = new DateLabel(dayOfWeek.get(Calendar.MONTH)+1+"."+dayOfWeek.get(Calendar.DAY_OF_MONTH),i);
                dayOfWeek.add(Calendar.DAY_OF_YEAR,1);
                hBox.getChildren().add(dateLabel);
            }

            gridPane.getChildren().clear();

            //网格面板样式
            for(int i=0;i<14;i++)
            {
                DateLabel dateLabel = new DateLabel(String.valueOf(i+1),-1);
                dateLabel.setPrefWidth(29);
                //dateLabel.setEffect(new InnerShadow(5,0,-1,Color.rgb(0,0,0,0.3)));
                gridPane.add(dateLabel,0,i,1,1);
            }

            int[] numarr={0,2,5,7,10,12};
            for(int i=0;i<6;i++)
            {
                for(int j=0;j<7;j++)
                {
                    String content= allList.get(index).get(i).get(j);
                    if(content=="")
                        continue;
                    int len =content.charAt(content.length()-1)-'0';
                    content=content.substring(0,content.length()-1);
                    ScheduleItem Sitem = new ScheduleItem(j,numarr[i],len,content);
                    gridPane.add(Sitem,Sitem.x+1,Sitem.y,1,Sitem.h);
                }
            }

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
