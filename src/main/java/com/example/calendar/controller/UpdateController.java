package com.example.calendar.controller;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UpdateController {
    public void updateCourse(File f1){
        String root = System.getProperty("user.dir");
        try {
            FileReader fr = new FileReader(f1);
            File f2 = new File(root+"/src/main/resources/data/courseData.xls");
            FileWriter fw = new FileWriter(f2);
            char c[] = new char[(int) f1.length()];
            int temp = 0;
            while ((temp = fr.read(c)) != -1) {
                fw.write(c);
                fw.flush();
            }

            fw.close();
            fr.close();//关流很重要
        }catch (Exception e){System.out.println("文件路径错误");e.printStackTrace();}
    }
    public void update(File in)
    {
        String root = System.getProperty("user.dir");

        try {
            FileInputStream fis  = new FileInputStream(in);
            File f2 = new File(root+"/src/main/resources/data/courseData.xls");
            FileOutputStream fos = new FileOutputStream(f2);
            byte[] buf = new byte[1024*1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
            if (fis != null) fis.close();
            if (fos != null) fos.close();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("请上传从教务管理系统下载的班级推荐课表");
            alert.showAndWait();
        }
        finally {

        }
    }
}
