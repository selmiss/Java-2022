package com.example.calendar.controller;

import com.example.calendar.Entity.Idea;
import com.example.calendar.Entity.Item;
import javafx.print.Collation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 添加删除的待办事项
 * <p>待办事件处理</p >
 * @author 赵城
 * @version 1.0
 * @since 2022-6-17
 */
public class UserOpController {
    /** 添加单个待办元素 **/
    public void addTodoItem(Item item, List<Item> list) throws Exception {
        list.add(item);
        item.setId(list.size()+1);
        list = sortedItemList(list);//排序
        addTodoItemList(list);
    }
    /** 添加多个待办元素 **/
    public void addTodoItemList(List<Item> arr) throws Exception {
        arr = sortedItemList(arr);//排序
        String root = System.getProperty("user.dir");
        String path = root + "/src/main/resources/data/itemData.xls";
        System.out.println("path" + path);
        //System.out.println("add list ok 1");
        File file = new File(path);
        OutputStream outputStream = new FileOutputStream(file);
        System.out.println("add list ok");
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
    /** 删除单个待办元素 **/
    public void deleteTodoItem(Item item, List<Item> list){
        int id = item.getId(), flag = 0, i=0;
        for(i=0;i<list.size();i++){
            if(flag == 1){
                System.out.println("没问题0");
                list.get(i).setId(list.get(i).getId()-1);
                System.out.println("没问题1");
            }
            if(list.get(i).getId() == id && flag == 0) {
                list.remove(list.get(i));
                System.out.println("没问题2");
                flag = 1;
            }
        }
        try{
            list = sortedItemList(list);
            addTodoItemList(list);
        }catch (Exception e){
            System.out.println("这里是删除方法 add List 方法出了问题");
        }
    }
    /** 将给予的list进行时间从近到远排序 **/
    public List<Item> sortedItemList(List<Item> list){
        list.sort(Item::compareTo);
        return list;
    }
    /** 添加单个灵感 **/
    public void addIdea(Idea idea, List<Idea> list) throws Exception{
        list.add(idea);
        idea.setId(list.size()+1);
        list = sortedIdeaList(list);//排序
        addIdeaList(list);
    }
    /** 添加灵感列表 **/
    public void addIdeaList(List<Idea> arr) throws Exception{
        arr = sortedIdeaList(arr);//排序
        String root = System.getProperty("user.dir");
        String path = root + "/src/main/resources/data/ideaData.xls";
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
        for(Idea x : arr){
            HSSFRow temp_row = sheet.createRow(sheet.getLastRowNum()+1);
            temp_row.createCell(0).setCellValue(x.getTitle());
            temp_row.createCell(1).setCellValue(x.getContent());
            temp_row.createCell(2).setCellValue(x.getDate());
        }
        workbook.setActiveSheet(0);
        workbook.write(outputStream);
        outputStream.close();
    }
    /** 删除灵感 **/
    public void deleteIdea(Idea idea, List<Idea> list){
        int id = idea.getId(), flag = 0, i=0;
        for(i=0;i<list.size();i++){
            if(flag == 1){
                list.get(i).setId(list.get(i).getId()-1);
            }
            if(list.get(i).getId() == id && flag == 0) {
                list.remove(list.get(i));
                flag = 1;
            }
        }
        try{
            list = sortedIdeaList(list);
            addIdeaList(list);
        }catch (Exception e){
            System.out.println("这里是删除方法 add idea List 方法出了问题");
        }
    }
    /** 灵感排序 **/
    public List<Idea> sortedIdeaList(List<Idea> list){
        list.sort(Idea::compareTo);
        return list;
    }
}
