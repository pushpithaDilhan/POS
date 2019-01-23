package com.pos.models;

import java.util.ArrayList;

public class Order {
    private String order_id;
    private double order_total;
    private ArrayList<Item> itemlist;

    public double getOrder_total() {
        return order_total;
    }

    public void setOrder_total(double order_total) {
        this.order_total = order_total;
    }

    public Order(String order_id) {
        this.order_id = order_id;
    }

    public ArrayList<Item> getItemlist() {
        return itemlist;
    }

    public void setItemlist(ArrayList<Item> itemlist) {
        this.itemlist = itemlist;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void addItem(Item item){
        if(itemlist == null){
            itemlist = new ArrayList<Item>();
        }
        itemlist.add(item);
        order_total += Double.parseDouble(item.getItem_price());
    }
}
