package com.example.calendar.controller;

import com.example.calendar.Entity.Course;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CourseController {
    public List<List<String>> getCourseList(){
        List<Course> course = new ArrayList<>();
        List< List<String> > list = new ArrayList<>();
        String root = System.getProperty("user.dir");
        String path = root + "/src/main/resources/data/courseData.xls";
        try{
            FileInputStream in = new FileInputStream(path);
            HSSFWorkbook workbook = new HSSFWorkbook(in);
            HSSFSheet sheet = workbook.getSheetAt(0);
            for(int i = 2; i<=7 ;i++){
                HSSFRow row = sheet.getRow(i);
                List<String> arr = new ArrayList<>();

                String monday = row.getCell(2).getStringCellValue();
                String tuesday = row.getCell(3).getStringCellValue();
                String wednesday = row.getCell(4).getStringCellValue();
                String thursday = row.getCell(5).getStringCellValue();
                String friday = row.getCell(6).getStringCellValue();
                String saturday = row.getCell(7).getStringCellValue();
                String sunday = row.getCell(8).getStringCellValue();
                arr.add(monday);arr.add(tuesday); arr.add(wednesday); arr.add(thursday);arr.add(friday); arr.add(saturday); arr.add(sunday);
                list.add(arr);
            }
        }catch (Exception e){
        }
        return list;
    }
    public List<List<String>> getCourseList(int week){
        List< List<String> > list = new ArrayList<>();
        WordAnalisys wordAnalisys = new WordAnalisys();
        String root = System.getProperty("user.dir");
        String path = root + "/src/main/resources/data/courseData.xls";
        try{
            FileInputStream in = new FileInputStream(path);
            HSSFWorkbook workbook = new HSSFWorkbook(in);
            HSSFSheet sheet = workbook.getSheetAt(0);
            for(int i = 2; i<=7 ;i++){
                HSSFRow row = sheet.getRow(i);
                List<String> arr = new ArrayList<>();
                List<String> temp = new ArrayList<>();

                String monday = row.getCell(2).getStringCellValue();
                String tuesday = row.getCell(3).getStringCellValue();
                String wednesday = row.getCell(4).getStringCellValue();
                String thursday = row.getCell(5).getStringCellValue();
                String friday = row.getCell(6).getStringCellValue();
                String saturday = row.getCell(7).getStringCellValue();
                String sunday = row.getCell(8).getStringCellValue();
                temp.add(monday);temp.add(tuesday); temp.add(wednesday); temp.add(thursday);temp.add(friday); temp.add(saturday); temp.add(sunday);

                for(String str : temp){
                    if(str.equals("")){
                        arr.add("");
                        continue;
                    }
                    int start = wordAnalisys.Analysis(str).getStart(), end= wordAnalisys.Analysis(str).getEnd();
                    String name = wordAnalisys.Analysis(str).getContent();
                    int len = wordAnalisys.Analysis(str).getLen();
//                    System.out.println("该元素的name:" + name);

                    if(start<=week && end>=week){
                        arr.add(name+" "+len);
                    }else {
                        arr.add("");
                    }
                }
                list.add(arr);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("异常地点为 get Course List (week版)");
        }
        return list;
    }
    public void courseRead(List<List<String>> course){
        for(int i=0;i<course.size();i++){
            List<String> temp = course.get(i);
            for(int j=0;j<temp.size();j++){
                String str = temp.get(j);
                if(str.equals("")){
//                    System.out.println("NULL");
                }else {
//                    System.out.println(str);
                }
            }
        }
    }
    public void courseAllRead(List<List<List<String>>> course){
        for(int i=0;i<course.size();i++){
            List<List<String>> course1 = course.get(i);
            courseRead(course1);
        }
    }
    public List<List<List<String>>> getAllCourseList(){
        List<List<List<String>>> ans = new ArrayList<>();
        for(int i=1;i<=20;i++){
            List<List<String>> temp = getCourseList(i);
            ans.add(temp);
        }
        return ans;
    }
    public void courseWrite(List<List<String>> course ,int week){
        String root = System.getProperty("user.dir");
        String path = root + "/src/main/resources/data/allCourseData.xls";
        File file = new File(path);
        try{
            OutputStream outputStream = new FileOutputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("sheet" + (week+1));
            {HSSFRow row0 = sheet.createRow(0);
                row0.createCell(0).setCellValue("");
                row0.createCell(1).setCellValue("");
                row0.createCell(2).setCellValue("星期一");
                row0.createCell(3).setCellValue("星期二");
                row0.createCell(4).setCellValue("星期三");
                row0.createCell(5).setCellValue("星期四");
                row0.createCell(5).setCellValue("星期五");
                row0.createCell(5).setCellValue("星期六");
                row0.createCell(5).setCellValue("星期日");}
            {HSSFRow row1= sheet.createRow(1);
                row1.createCell(0).setCellValue("上午");
                row1.createCell(1).setCellValue("第1,2节");}
            {HSSFRow row2= sheet.createRow(2);
                row2.createCell(0).setCellValue("上午");
                row2.createCell(1).setCellValue("第3,4,5节");}
            {HSSFRow row3= sheet.createRow(3);
                row3.createCell(0).setCellValue("下午");
                row3.createCell(1).setCellValue("第6,7节");}
            {HSSFRow row4= sheet.createRow(4);
                row4.createCell(0).setCellValue("下午");
                row4.createCell(1).setCellValue("第8,9,10节");}
            {HSSFRow row5= sheet.createRow(5);
                row5.createCell(0).setCellValue("晚上");
                row5.createCell(1).setCellValue("第11,12节");}
            {HSSFRow row1= sheet.createRow(6);
                row1.createCell(0).setCellValue("晚上");
                row1.createCell(1).setCellValue("第13,14节");}
            for(int i=0;i<course.size();i++){
                List<String> arr = course.get(i);
                int j = i + 1;
                HSSFRow row = sheet.getRow(j);
                for(int k = 2 ; k<=8 ; k++){
                    row.createCell(k).setCellValue(arr.get(k-2));
                }
            }
            workbook.setActiveSheet(0);
            workbook.write(outputStream);
            outputStream.close();
        }catch (Exception e){e.printStackTrace();System.out.println("course write出现异常");}
    }
    public void courseAllWrite(List<List<List<String>>> course){
        for(int i=0;i<course.size();i++){
            courseWrite(course.get(i), i+1);
        }
    }
}
