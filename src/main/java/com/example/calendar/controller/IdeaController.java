package com.example.calendar.controller;

import com.example.calendar.Entity.Idea;
import com.example.calendar.Entity.Item;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IdeaController {
    public List<Idea> ReadIdeaList(){
        List<Idea> ideas = new ArrayList<>();
        String root = System.getProperty("user.dir");
        String path = root + "/src/main/resources/data/ideaData.xls";
        List<Idea> todolist = new ArrayList<>();
        try {
            FileInputStream in = new FileInputStream(path);
            HSSFWorkbook workbook = new HSSFWorkbook(in);
            HSSFSheet sheet = workbook.getSheetAt(0);
            int row_max = sheet.getLastRowNum();
            for(int i=1;i<=row_max;i++)
            {
                HSSFRow row = sheet.getRow(i);

                String title = row.getCell(0).getStringCellValue();
                String content = row.getCell(1).getStringCellValue();

                Date date = new Date();
                Cell cell = row.getCell(2);
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    date = cell.getDateCellValue();
                }catch (Exception dateWrong){System.out.println("日期转换错误");}
                Idea idea = new Idea(i,title,content,date);
                System.out.println(idea.toString());
                todolist.add(idea);
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("ideaController-ReadIdeaList File open/read fail!");
        }
        return todolist;
    }
}
