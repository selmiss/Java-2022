package com.example.calendar.controller;

import com.example.calendar.Entity.Item;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;

public class TodoItemController {

    public List<Item> ReadItemList()
    {
        System.out.println("Start read item_list!");
        List<Item> items = new ArrayList<>();
        String root = System.getProperty("user.dir");
        String path = root + "/src/main/resources/data/itemData.xls";
        List<Item> todolist = new ArrayList<>();
        System.out.println("path:"+path );
        try {
            FileInputStream in = new FileInputStream(path);
            HSSFWorkbook workbook = new HSSFWorkbook(in);
            HSSFSheet sheet = workbook.getSheetAt(0);
            int row_max = sheet.getLastRowNum();
            for(int i=1;i<=row_max;i++)
            {
                HSSFRow row = sheet.getRow(i);

                String title = row.getCell(0).getStringCellValue();
                String content = row.getCell(2).getStringCellValue();
                int subject=0,weight=0;
                try {
                    subject = (int)row.getCell(3).getNumericCellValue();
                    weight = (int)row.getCell(4).getNumericCellValue();
                }catch (Exception f){System.out.println("数据类型转换错误");}
                Date date = new Date();
                Cell cell = row.getCell(1);
                try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        date = cell.getDateCellValue();
                }catch (Exception dateWrong){System.out.println("日期转换错误");}
                Item item = new Item(i,title,content,date,subject,weight);
                System.out.println(item.toString());
                todolist.add(item);
            }
        }catch (Exception e) {System.out.println("File open/read fail!");}
        return todolist;
    }
}
