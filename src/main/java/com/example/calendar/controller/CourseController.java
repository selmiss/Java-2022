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
        System.out.println("开始读取课表");
        List<Course> course = new ArrayList<>();
        List< List<String> > list = new ArrayList<>();
        String root = System.getProperty("user.dir");
        String path = root + "/src/main/resources/data/courseData.xls";
        System.out.println("路径" + path);
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
            e.printStackTrace();
            System.out.println("异常地点为 Read Course List");
        }
        return list;
    }
    public List<List<String>> getCourseList(int week){
        System.out.println("开始读取课表");
        List<Course> course = new ArrayList<>();
        List< List<String> > list = new ArrayList<>();
        String root = System.getProperty("user.dir");
        String path = root + "/src/main/resources/data/courseData.xls";
        System.out.println("路径" + path);
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
            e.printStackTrace();
            System.out.println("异常地点为 Read Course List");
        }
        return list;
    }
    public void courseRead(List<List<String>> course){
        for(int i=0;i<course.size();i++){
            System.out.println("时间段" + (i+1));
            List<String> temp = course.get(i);
            for(int j=0;j<temp.size();j++){
                String str = temp.get(j);
                if(str.equals("")){
                    System.out.println("NULL");
                }else {
                    System.out.println(str);
                }
            }
        }
    }
    public List<List<List<String>>> getAllCourseList(){
        List<List<List<String>>> ans = new ArrayList<>();
        for(int i=1;i<=16;i++){
            List<List<String>> temp = getCourseList(i);
            ans.add(temp);
        }
        return ans;
    }
}
