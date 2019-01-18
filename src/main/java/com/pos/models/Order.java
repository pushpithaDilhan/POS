package com.pos.models;

import java.util.ArrayList;

public class Order {
    private String order_id;
    private ArrayList<Item> itemlist;

    public ArrayList<Item> getItemlist() {
        return itemlist;
    }

    public void setItemlist(ArrayList<Item> itemlist) {
        this.itemlist = itemlist;
    }

    public void addItem(Item item){
        if(itemlist == null){
            itemlist = new ArrayList<Item>();
        }
        itemlist.add(item);
    }
}
