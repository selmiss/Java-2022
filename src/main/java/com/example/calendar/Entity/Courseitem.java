package com.example.calendar.Entity;

public class Courseitem {
    private String content;
    private int start;
    private int end;

    @Override
    public String toString() {
        return "Courseitem{" +
                "content='" + content + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public Courseitem(String content, int start, int end) {
        this.content = content;
        this.start = start;
        this.end = end;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
