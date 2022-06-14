package com.example.calendar.components.schedule;

import com.example.calendar.utils.TimePicker;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 课程表类.
 * <p></p>
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-14
 */
public class Schedule extends AnchorPane {
    static String[] weeks = new String[]{"第一周","第二周","第三周","第四周",
            "第五周","第六周","第七周","第八周","第九周","第十周","第十一周",
    "第十二周","第十三周","第十四周","第十五周","第十六周","第十七周",
            "第十八周","第十九周","第二十周"};

    Calendar nowCalendar = Calendar.getInstance();//当前时间
    Calendar startCalendar = Calendar.getInstance();//学期开始时间

    GridPane gridPane = new GridPane();//网格容器
    ScrollPane scrollPane = new ScrollPane();//滚动容器
    HBox hBox = new HBox();//水平盒容器
    Label weekIndexLabel = new Label();//显示周数的标签
    public Schedule(){
        //设置学期起始日期
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            startCalendar.setTime(df.parse("2022-02-28"));
        }catch (ParseException e){
            e.printStackTrace();
        }

        //周数标签样式
        weekIndexLabel.setPrefWidth(150);
        weekIndexLabel.setPrefHeight(30);
        weekIndexLabel.setLayoutX(75);
        weekIndexLabel.setLayoutY(20);
        weekIndexLabel.setText(weeks[nowCalendar.get(Calendar.WEEK_OF_YEAR)-startCalendar.get(Calendar.WEEK_OF_YEAR)]);
        weekIndexLabel.setAlignment(Pos.CENTER);
        weekIndexLabel.setStyle("-fx-background-color: #ffffff;" +
                "-fx-background-radius: 15;" +
                "-fx-font-size: 16;" +
                "font-width: normal;");
        weekIndexLabel.setOnMouseClicked(new WeekEventHandler());

        //日期栏
        hBox.setLayoutX(4);
        hBox.setLayoutY(60);
        hBox.setPrefHeight(30);
        hBox.setPrefWidth(280);
        hBox.setEffect(new InnerShadow(2,0,1,Color.rgb(0,0,0,0.3)));
        Calendar dayOfWeek = Calendar.getInstance();
        dayOfWeek.add(Calendar.DAY_OF_YEAR,-nowCalendar.get(Calendar.DAY_OF_WEEK)+1);
        DateLabel empty = new DateLabel("");
        empty.setPrefWidth(30);
        hBox.getChildren().add(empty);
        for(int i=0;i<7;i++)
        {
            DateLabel dateLabel = new DateLabel(dayOfWeek.get(Calendar.MONTH)+1+"."+dayOfWeek.get(Calendar.DAY_OF_MONTH));
            dayOfWeek.add(Calendar.DAY_OF_YEAR,1);
            hBox.getChildren().add(dateLabel);
        }

        //滚动面板样式
        scrollPane.setContent(gridPane);
        scrollPane.setLayoutX(4);
        scrollPane.setLayoutY(90);
        scrollPane.setPrefHeight(400);
        scrollPane.setPrefWidth(292);
        scrollPane.setEffect(new InnerShadow(1,Color.rgb(0,0,0,0.3)));
        scrollPane.getStylesheets().add("file:src/main/resources/com/example/calendar/css/round.css");

        //网格面板样式
        for(int i=0;i<14;i++)
        {
            DateLabel dateLabel = new DateLabel(String.valueOf(i+1));
            dateLabel.setPrefWidth(30);
            //dateLabel.setEffect(new InnerShadow(5,0,-1,Color.rgb(0,0,0,0.3)));
            gridPane.add(dateLabel,0,i,1,1);
        }
        ScheduleItem Sitem = new ScheduleItem(0,0,2);
        gridPane.add(Sitem,Sitem.x+1,Sitem.y,1,Sitem.h);

        getChildren().addAll(weekIndexLabel,hBox,scrollPane);
    }

    /**
     * 处理周数选择点击事件.
     * @author 郭一帆
     * @version 1.0
     * @since 2022-6-14
     */
    private class WeekEventHandler implements EventHandler{

        @Override
        public void handle(Event event) {
            Label src = (Label)event.getSource();

            WeekPicker weekPicker = new WeekPicker(src);
            Stage stage = new Stage();
            stage.setX(1110);
            stage.setY(420);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(weekPicker);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.showAndWait();
        }
    }

}

/**
 * 时间标签类.
 * <p>用来显示课表表头，包括日期、节数.</p>
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-14
 */
class DateLabel extends Label{
    public DateLabel(String var1){
        super(var1);
        setPrefWidth(36);
        setPrefHeight(60);
        setStyle("-fx-font-size: 12;" +
                "font-width: normal;" +
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #D8D8D8;" +
                "-fx-border-width: 0.5");
        setAlignment(Pos.CENTER);
        setWrapText(true);
    }
}

/**
 * 课程类.
 * <p>显示课程名、上课地点.</p>
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-14
 */
class ScheduleItem extends AnchorPane{
    public int x,y,h;
    Label title = new Label();
    Label location = new Label();
    public ScheduleItem(int x,int y,int h){
        this.x = x;
        this.y = y;
        this.h = h;
        setBackground(new Background(new BackgroundFill(Color.valueOf("#FF9F9F"),new CornerRadii(8),new Insets(0,0,0,0))));
        setPrefWidth(36);
        setPrefHeight(60*h);

        //课程名设置
        title.setStyle("-fx-font-size: 12;" +
                "font-width: normal;");
        title.setTextFill(Color.WHITE);
        title.setText("操作系统");
        title.setPrefWidth(36);
        title.setWrapText(true);
        title.setLayoutY(10*h);
        title.setMaxHeight(40);

        //上课地点设置
        location.setStyle("-fx-font-size: 12;" +
                "font-width: normal;");
        location.setTextFill(Color.WHITE);
        location.setText("@(一)101");
        location.setPrefWidth(36);
        location.setWrapText(true);
        location.setLayoutY(30*h);
        location.setMaxHeight(40);

        getChildren().addAll(title,location);
    }
}
