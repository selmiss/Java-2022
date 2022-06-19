package com.example.calendar.controller;

import com.example.calendar.Entity.Courseitem;

public class WordAnalisys {
    public Courseitem Analysis(String source)
    {
        String[] arr = source.split("◇");
        String content;
        int start=0,end=0;
        if(arr.length>2)
        {
            String position = arr[2];
            position = position.split("第")[0];
            content = arr[0]+'\n'+position;
            String[] time=arr[1].split("\\[|周|-|,|\\]");
            try{
                start=Integer.parseInt(time[1]);
                end=Integer.parseInt(time[2]);
            }catch (Exception time1){System.out.println("周数转换失败");}
        }
        else {
            content = arr[0];
            String[] time=arr[1].split("\\[|周|-|,|\\]");
            try{
                start=Integer.parseInt(time[1]);
                end=Integer.parseInt(time[2]);
            }catch (Exception time2){System.out.println("周数转换失败");}
        }
        Courseitem courseitem = new Courseitem(content,start,end);
        return courseitem;
    }
}
