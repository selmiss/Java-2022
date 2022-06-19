package com.example.calendar.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class UpdateController {
    public void updateCourse(File f1){
        try {
            FileReader fr = new FileReader(f1);
            File f2 = new File("/src/main/resources/data/courseData.xls");
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
}
