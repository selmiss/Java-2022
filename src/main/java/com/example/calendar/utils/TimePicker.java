package com.example.calendar.utils;


import com.example.calendar.Entity.Item;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimePicker extends VBox{
    public List<Button> dayList;
    public Calendar calendar;
    public Calendar tmpCalendar;
    public SimpleDateFormat sdf;
    public VBox root;
    public VBox root1;
    public FlowPane flowPane;
    public FlowPane flow;
    public Label labelYear;
    public Label labelMouth;
    public String strYear;
    public String strYearMouth;
    public String strYearMouthDay;
    public Label labelSelected;
    public String time;
    public TimePicker(Label src, Item item){
        root = new VBox();
        root.setMinSize(310,200);
        root.setMaxSize(310,200);
        root1 = new VBox();
        root1.setAlignment(Pos.TOP_CENTER);
        root1.getChildren().add(root);
        dayList = new ArrayList<Button>();


        /*
         * 星期
         * */
        flowPane = new FlowPane();
        flowPane.setStyle("-fx-alignment: center;");
        flowPane.setVgap(2);
        flowPane.setHgap(34);
        flowPane.setPrefWrapLength(310);
        flowPane.getChildren().add(new Label("一"));
        flowPane.getChildren().add(new Label("二"));
        flowPane.getChildren().add(new Label("三"));
        flowPane.getChildren().add(new Label("四"));
        flowPane.getChildren().add(new Label("五"));
        flowPane.getChildren().add(new Label("六"));
        flowPane.getChildren().add(new Label("日"));


        /*
         * 当月日期
         * */
        flow = new FlowPane();
        flow.setVgap(8);
        flow.setHgap(15);
        flow.setPrefWrapLength(310);


        //sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


        /*
         * 生成一个Calendar，设置为运行程序的时间
         * */
        calendar = Calendar.getInstance();

        /*
         * 生成日期部分
         * */
        upDataCalendar();

        setSpacing(5);

        Label label = new Label("日期");
        Separator sp = new Separator();
        Separator sp1 = new Separator();
        Separator sp2 = new Separator();


        /**
         * 生成和月的左右选择
         */
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);


        labelYear = new Label(calendar.get(Calendar.YEAR)+"");
        Label label1 = new Label("年");
        labelMouth = new Label(""+(calendar.get(Calendar.MONTH)+1));
        Label label2 = new Label("月");


        Button btnYearLeft = new Button("<|");
        btnYearLeft.setStyle("-fx-text-fill: black;-fx-background-color: #6cc;");
        btnPress(btnYearLeft);

        Button btnYearRight = new Button("|>");
        btnYearRight.setStyle("-fx-text-fill: black;-fx-background-color: #6cc;");
        btnPress(btnYearRight);


        Button btnMouthLeft = new Button("<");
        btnMouthLeft.setStyle("-fx-text-fill: black;-fx-background-color: #c66;");
        btnPress(btnMouthLeft);


        Button btnMouthRight = new Button(">");
        btnMouthRight.setStyle("-fx-text-fill: black;-fx-background-color: #c66;");
        btnPress(btnMouthRight);


        hBox.getChildren().addAll(btnYearLeft,btnMouthLeft,labelYear,label1,labelMouth,label2,btnMouthRight,btnYearRight);


        /**
         * 生成时分秒选择框，默认取当前时间
         */
        HBox hBoxHMS = new HBox(4);
        ComboBox<Integer> hour = new ComboBox<Integer>();
        for (int i = 0;i < 24;i++){
            hour.getItems().add(i);
        }
        hour.getSelectionModel().select(calendar.get(Calendar.HOUR_OF_DAY));

        Label labelHour = new Label("时");

        ComboBox<Integer> minute = new ComboBox<Integer>();
        for (int i = 0;i < 60;i++){
            minute.getItems().add(i);
        }
        minute.getSelectionModel().select(calendar.get(Calendar.MINUTE));

        Label labelMinute = new Label("分");


        ComboBox<Integer> second = new ComboBox<Integer>();
        for (int i = 0;i < 60;i++){
            second.getItems().add(i);
        }
        second.getSelectionModel().select(calendar.get(Calendar.SECOND));

        Label labelSecond = new Label("秒");


        hBoxHMS.setAlignment(Pos.CENTER);
        hBoxHMS.getChildren().addAll(hour,labelHour,minute,labelMinute,second,labelSecond);


        /**
         *
         * 记录当前已选择时间
         */
        HBox hBoxSelect = new HBox(5);
        hBoxSelect.setAlignment(Pos.CENTER);
        Label labelSelect = new Label("当前选择时间：");
        labelSelected = new Label();
        hBoxSelect.getChildren().addAll(labelSelect,labelSelected);


        /**
         *
         * 取消和确认按钮，点击确认按钮获取 年-月-日 时:分:秒
         */
        HBox hBoxOKCancel = new HBox();
        hBoxOKCancel.setAlignment(Pos.CENTER);
        Button btnOK = new Button("确认");
        btnOK.setMinSize(175,30);
        btnOK.setMaxSize(175,30);
        btnOK.setStyle("-fx-background-color:#ACD6FF;-fx-font-size: 15;");
        btnOK.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnOK.setStyle("-fx-background-color:#FFFFB9;-fx-font-size: 15;");
            }
        });
        btnOK.setOnMouseReleased(event -> btnOK.setStyle("-fx-background-color:#ACD6FF;-fx-font-size: 15;"));
        btnOK.setOnAction(event -> {
            if (tmpCalendar!=null){
                /**
                 * 年月日时分秒
                 */
                Stage stage = (Stage) btnOK.getScene().getWindow();
                stage.close();
                src.setText(strYear+"."+(Integer.parseInt(strYearMouth)+1)+"."+strYearMouthDay+" "+strValue(Integer.valueOf(hour.getValue()))+":"+strValue(Integer.valueOf(minute.getValue()))+":"+strValue(Integer.valueOf(second.getValue())));
                time=strYear+"."+(Integer.parseInt(strYearMouth)+1)+"."+strYearMouthDay+" "+strValue(Integer.valueOf(hour.getValue()))+":"+strValue(Integer.valueOf(minute.getValue()))+":"+strValue(Integer.valueOf(second.getValue()));
//                System.out.println(strYear+"."+(Integer.parseInt(strYearMouth)+1)+"."+strYearMouthDay+" "+strValue(Integer.valueOf(hour.getValue()))+":"+strValue(Integer.valueOf(minute.getValue()))+":"+strValue(Integer.valueOf(second.getValue())));

                item.setDate(new Date());
                try{
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                    item.setDate(sf.parse(time));}
                catch (Exception dd){System.out.println("添加事项日期异常");}
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("请先选择日期");
                alert.showAndWait();
            }
        });

        Button btnCancel = new Button("取消");
        btnCancel.setMinSize(175,30);
        btnCancel.setMaxSize(175,30);
        btnCancel.setStyle("-fx-background-color:#FFD2D2;-fx-font-size: 15;");
        btnCancel.setOnMousePressed(event -> btnCancel.setStyle("-fx-background-color:#BEBEBE;-fx-font-size: 15;"));
        btnCancel.setOnMouseReleased(event -> btnCancel.setStyle("-fx-background-color:#FFD2D2;-fx-font-size: 15;"));
        btnCancel.setOnAction(event -> {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        });
        hBoxOKCancel.getChildren().addAll(btnCancel,btnOK);


        getChildren().addAll(label,sp,hBox,root1,sp1,hBoxHMS,sp2,hBoxSelect,hBoxOKCancel);
    }

    public String strValue(int i){
        String res;
        if (i <10){
            res="0"+i;
        }else {
            res = i+"";
        }

        return res;
    }


    /**
     *
     * 设置年月左右选择按钮被按下时和弹起时的颜色
     * @param btn  年月左右选择按钮Button
     */
    public void btnMouthPress(Button btn){
        btn.setOnMousePressed(event -> btn.setStyle("-fx-text-fill: black;-fx-background-color: #FFD306;"));
        btn.setOnMouseReleased(event -> btn.setStyle("-fx-text-fill: black;-fx-background-color: #c66;"));
    }

    public void btnYearPress(Button btn){
        btn.setOnMousePressed(event -> btn.setStyle("-fx-text-fill: black;-fx-background-color:#FF0000;"));
        btn.setOnMouseReleased(event -> btn.setStyle("-fx-text-fill: black;-fx-background-color: #6cc;"));
    }


    /**
     * 给年月左右选择按钮绑定事件
     * @param btn  年月左右选择按钮Button
     */
    public void btnPress(Button btn){
        if (btn.getText().trim().equals("<")){
            btn.setOnAction(event -> {
                calendar.add(Calendar.MONTH,-1);
                upDataLab(labelYear,labelMouth);
                upDataCalendar();
            });
            btnMouthPress(btn);
        }else if (btn.getText().trim().equals(">")){
            btn.setOnAction(event -> {
                calendar.add(Calendar.MONTH,1);
                upDataLab(labelYear,labelMouth);
                upDataCalendar();
            });
            btnMouthPress(btn);
        }else if (btn.getText().trim().equals("<|")){
            btn.setOnAction(event -> {
                calendar.add(Calendar.YEAR,-1);
                upDataLab(labelYear,labelMouth);
                upDataCalendar();
            });
            btnYearPress(btn);
        }else if (btn.getText().trim().equals("|>")){
            btn.setOnAction(event -> {
                calendar.add(Calendar.YEAR,1);
                upDataLab(labelYear,labelMouth);
                upDataCalendar();
            });
            btnYearPress(btn);
        }
    }


    /**
     * 设置上月和下月在本月显示的日期样式，并设置为不可点击
     * @param btn  日期按钮Button
     */
    public void setDisable(Button btn){
        btn.setDisable(true);
        btn.setStyle("-fx-text-fill: black;-fx-background-color: transparent;");
    }


    /**
     * 设置本月日期的点击事件和样式，其中点击时间后，自动记录时间
     * @param btn  日期按钮Button
     */
    public void setAble(Button btn){
        btn.setStyle("-fx-text-fill: black;-fx-background-color: #fff;");
        btn.setOnAction(event -> {
            dayList.forEach(e->{e.setStyle("-fx-text-fill: black;-fx-background-color: #fff;");});
            btn.setStyle("-fx-text-fill: white;-fx-background-color: #5b8cff;");

            strYear = calendar.get(Calendar.YEAR)+"";
            strYearMouth = ""+calendar.get(Calendar.MONTH);
            strYearMouthDay = btn.getText();
            tmpCalendar = Calendar.getInstance();
            tmpCalendar.set(Integer.valueOf(strYear),Integer.valueOf(strYearMouth),Integer.valueOf(strYearMouthDay));
            //System.out.println(tmpCalendar.getTime());
            labelSelected.setText(tmpCalendar.get(Calendar.YEAR)+"年"+(tmpCalendar.get(Calendar.MONTH)+1)+"月"+tmpCalendar.get(Calendar.DAY_OF_MONTH)+"日");
        });
    }

    /**
     * 用于更新年月展示
     * @param lbY
     * @param lbM
     */
    public void upDataLab(Label lbY,Label lbM){
        lbM.setText(""+(calendar.get(Calendar.MONTH)+1));
        lbY.setText(calendar.get(Calendar.YEAR)+"");
    }


    /**
     * 更新日期部分的数据
     */
    public void upDataCalendar(){
        /*
         * 获取当月天数
         * calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
         * */

        /**
         * 获取当前年月，用于判断是否为已选时间所在月份
         */
        String tmpStr = calendar.get(Calendar.YEAR)+","+calendar.get(Calendar.MONTH);


        if (tmpStr.equals(strYear+","+strYearMouth)){
            /**
             * 判断结果为当前年月是已选时间所在月份，自动为已选日期改为选中样式
             */
            dayList.clear();
            flow.getChildren().clear();
            int mouthDays = tmpCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            tmpCalendar.set(tmpCalendar.get(Calendar.YEAR),tmpCalendar.get(Calendar.MONTH),mouthDays,tmpCalendar.get(Calendar.HOUR_OF_DAY),tmpCalendar.get(Calendar.MINUTE),tmpCalendar.get(Calendar.SECOND));
            int weekMouthLastDay = tmpCalendar.get(Calendar.DAY_OF_WEEK);
            tmpCalendar.set(tmpCalendar.get(Calendar.YEAR),tmpCalendar.get(Calendar.MONTH),1,tmpCalendar.get(Calendar.HOUR_OF_DAY),tmpCalendar.get(Calendar.MINUTE),tmpCalendar.get(Calendar.SECOND));
            int weekMouthFirstDay = tmpCalendar.get(Calendar.DAY_OF_WEEK);

            tmpCalendar.add(Calendar.MONTH,-1);
            int lastMouthDays = tmpCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            tmpCalendar.add(Calendar.MONTH,1);

            //System.out.println("本月天数："+mouthDays+"   上月天数"+lastMouthDays);



            if (weekMouthFirstDay == 1){
                //System.out.println("本月第一天是周日，前面有6天");
                for (int i = lastMouthDays-5;i<=lastMouthDays;i++){
                    //dayList.add(i);
                    Button btn = new Button(strValue(i));
                    setDisable(btn);
                    flow.getChildren().add(btn);
                }
                for (int i = 1;i<=mouthDays;i++){
                    Button btn = new Button(strValue(i));
                    dayList.add(btn);
                    setAble(btn);
                    flow.getChildren().add(btn);
                }
            }else if (weekMouthFirstDay == 2){
                //System.out.println("本月第一天是周一，前面没有");
                for (int i = 1;i<=mouthDays;i++){
                    Button btn = new Button(strValue(i));
                    dayList.add(btn);
                    setAble(btn);
                    if (strYearMouthDay.equals(strValue(i))){
                        btn.setStyle("-fx-text-fill: white;-fx-background-color: #5b8cff;");
                    }
                    flow.getChildren().add(btn);
                }
            }else{
                //System.out.println("本月第一天不是周日，也不是周一");
                for (int i = lastMouthDays-weekMouthFirstDay+3;i<=lastMouthDays;i++){
                    //dayList.add(i);
                    Button btn = new Button(strValue(i));
                    setDisable(btn);
                    flow.getChildren().add(btn);
                }
                for (int i = 1;i<=mouthDays;i++){
                    Button btn = new Button(strValue(i));
                    dayList.add(btn);
                    setAble(btn);
                    if (strYearMouthDay.equals(strValue(i))){
                        btn.setStyle("-fx-text-fill: white;-fx-background-color: #5b8cff;");
                    }
                    flow.getChildren().add(btn);
                }
            }

            if (weekMouthLastDay != 1){
                for (int i = 1;i<=8-weekMouthLastDay;i++){
                    //dayList.add(i);
                    Button btn = new Button(strValue(i));
                    setDisable(btn);
                    flow.getChildren().add(btn);
                }
            }


            root.getChildren().clear();
            root.getChildren().addAll(flowPane,flow);
            root1.getChildren().clear();
            root1.getChildren().add(root);
        }else{

            dayList.clear();
            flow.getChildren().clear();
            int mouthDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),mouthDays,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND));
            int weekMouthLastDay = calendar.get(Calendar.DAY_OF_WEEK);
            calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND));
            int weekMouthFirstDay = calendar.get(Calendar.DAY_OF_WEEK);

            calendar.add(Calendar.MONTH,-1);
            int lastMouthDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.add(Calendar.MONTH,1);

            //System.out.println("本月天数："+mouthDays+"   上月天数"+lastMouthDays);



            if (weekMouthFirstDay == 1){
                //System.out.println("本月第一天是周日，前面有6天");
                for (int i = lastMouthDays-5;i<=lastMouthDays;i++){
                    //dayList.add(i);
                    Button btn = new Button(strValue(i));
                    setDisable(btn);
                    flow.getChildren().add(btn);
                }
                for (int i = 1;i<=mouthDays;i++){
                    Button btn = new Button(strValue(i));
                    dayList.add(btn);
                    setAble(btn);
                    flow.getChildren().add(btn);
                }
            }else if (weekMouthFirstDay == 2){
                //System.out.println("本月第一天是周一，前面没有");
                for (int i = 1;i<=mouthDays;i++){
                    Button btn = new Button(strValue(i));
                    dayList.add(btn);
                    setAble(btn);
                    flow.getChildren().add(btn);
                }
            }else{
                //System.out.println("本月第一天不是周日，也不是周一");
                for (int i = lastMouthDays-weekMouthFirstDay+3;i<=lastMouthDays;i++){
                    //dayList.add(i);
                    Button btn = new Button(strValue(i));
                    setDisable(btn);
                    flow.getChildren().add(btn);
                }
                for (int i = 1;i<=mouthDays;i++){
                    Button btn = new Button(strValue(i));
                    dayList.add(btn);
                    setAble(btn);
                    flow.getChildren().add(btn);
                }
            }

            if (weekMouthLastDay != 1){
                for (int i = 1;i<=8-weekMouthLastDay;i++){
                    //dayList.add(i);
                    Button btn = new Button(strValue(i));
                    setDisable(btn);
                    flow.getChildren().add(btn);
                }
            }


            root.getChildren().clear();
            root.getChildren().addAll(flowPane,flow);
            root1.getChildren().clear();
            root1.getChildren().add(root);
        }


    }
}
