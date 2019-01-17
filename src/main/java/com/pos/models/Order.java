package com.pos.models;

import java.util.ArrayList;

public class Order {
    private ArrayList<Item> itemlist;

    public ArrayList<Item> getItemlist() {
        return itemlist;
    }

    public void setItemlist(ArrayList<Item> itemlist) {
        this.itemlist = itemlist;
    }
}
