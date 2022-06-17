package com.example.calendar.controller;

import com.example.calendar.Entity.Item;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserOpController {

    public void addTodoItem(String title, Date date, String content, int subject, int weight) throws Exception {
        System.out.println("add TodoItem begin");
        String root = System.getProperty("user.dir");
        String path = root + "/src/main/resources/data/new_itemData.xls";
        System.out.println("path" + path);

        File file = new File(path);
        OutputStream outputStream = new FileOutputStream(file);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");

        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("标题");
        row.createCell(1).setCellValue("时间");
        row.createCell(2).setCellValue("内容");
        row.createCell(3).setCellValue("类别");
        row.createCell(4).setCellValue("权重");

        HSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue(title);
        row1.createCell(1).setCellValue(date);
        row1.createCell(2).setCellValue(content);
        row1.createCell(3).setCellValue(subject);
        row1.createCell(4).setCellValue(weight);

        workbook.setActiveSheet(0);
        workbook.write(outputStream);
        outputStream.close();
    }
    public void addTodoItem(List<Item> arr) throws Exception {
        System.out.println("add arr TodoItem begin");
        String root = System.getProperty("user.dir");
        String path = root + "/src/main/resources/data/itemData.xls";
        System.out.println("path" + path);

        File file = new File(path);
        OutputStream outputStream = new FileOutputStream(file);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");


        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("标题");
        row.createCell(1).setCellValue("时间");
        row.createCell(2).setCellValue("内容");
        row.createCell(3).setCellValue("类别");
        row.createCell(4).setCellValue("权重");

        for(Item x : arr){
            HSSFRow temp_row = sheet.createRow(sheet.getLastRowNum()+1);
            temp_row.createCell(0).setCellValue(x.getTitle());
            temp_row.createCell(1).setCellValue(x.getDate());
            temp_row.createCell(2).setCellValue(x.getContent());
            temp_row.createCell(3).setCellValue(x.getSubject());
            temp_row.createCell(4).setCellValue(x.getWeight());
        }

        workbook.setActiveSheet(0);
        workbook.write(outputStream);
        outputStream.close();
    }
}
