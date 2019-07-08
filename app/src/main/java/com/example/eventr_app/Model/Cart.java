package com.example.eventr_app.Model;

public class Cart
{
    private String eid, ename, price,  quantity,  category, eDate, place, limit;

    public Cart()
    {

    }

    public Cart(String eid, String ename, String price, String quantity, String category, String eDate, String place, String limit) {
        this.eid = eid;
        this.ename = ename;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.eDate = eDate;
        this.place = place;
        this.limit = limit;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
