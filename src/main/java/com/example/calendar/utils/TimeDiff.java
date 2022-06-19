package com.example.calendar.utils;

import java.util.Date;

/**
 * 工具类，计算两个时间的差.
 * @author 郭一帆
 * @version 1.0
 * @since 2022.6.12
 */
public class TimeDiff {
    private long diffSeconds;
    private long diffMinutes;
    private long diffHours;
    private long diffDays;
    public  TimeDiff(Date d1, Date d2){
        long diff = d2.getTime() - d1.getTime();
        diffSeconds = diff / 1000 % 60;
        diffMinutes = diff / (60 * 1000) % 60;
        diffHours = diff / (60 * 60 * 1000) % 24;
        diffDays = diff / (24 * 60 * 60 * 1000);
    }

    /**
     * 根据时间段的长度，显示不同的样式.
     * @return 时间差的最终显示文字.
     */
    @Override
    public String toString() {
        if(diffSeconds<0||diffMinutes<0||diffHours<0||diffDays<0)
            return "已过期";
        else if(diffDays>=10)
            return diffDays+"d";
        else if(diffDays==0)
            return diffHours+"h"+diffMinutes+"m";
        else if(diffHours==0)
            return diffMinutes+"m"+diffSeconds+"s";
        else if(diffMinutes==0)
            return diffSeconds+"s";
        else
        return diffDays+"d"+diffHours+"h";
    }
}
