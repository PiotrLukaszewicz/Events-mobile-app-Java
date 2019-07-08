package com.example.eventr_app.Model;

public class Events {
    private String category, date, eDate, eTime, image, limit, name, pid, place, price, time;

    public Events()
    {

    }

    public Events(String category, String date, String eDate, String eTime, String image, String limit, String name, String pid, String place, String price, String time) {
        this.category = category;
        this.date = date;
        this.eDate = eDate;
        this.eTime = eTime;
        this.image = image;
        this.limit = limit;
        this.name = name;
        this.pid = pid;
        this.place = place;
        this.price = price;
        this.time = time;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

