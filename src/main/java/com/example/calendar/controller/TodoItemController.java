package com.example.calendar.controller;

import com.example.calendar.Entity.TodoItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodoItemController {
    public List<TodoItem> GetEmergencyItme()
    {
        return null;
    }
    public List<TodoItem> ReadItemList()
    {
        List<TodoItem> todoItems = new ArrayList<>();
        String root = System.getProperty("user.dir");
        String path = root + "src/main/resources/data/itemList.xlsx";
        System.out.println("path:"+path );
        try {
            FileInputStream in = new FileInputStream(path);
        }catch (Exception e) {System.out.println("File open faile!");}

        return null;
    }
}
