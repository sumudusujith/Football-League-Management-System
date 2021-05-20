package controllers;

import java.io.Serializable;

public class Date implements Serializable {

    private static final long serialVersionUID = 1L;
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day=day;
    }

    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getYear() {
            return year;
    }
    public void setYear(int year) {
            this.year = year;
    }
    //
    @Override
    public String toString(){
            return (day + "/" + month + "/" + year);
    }


}

