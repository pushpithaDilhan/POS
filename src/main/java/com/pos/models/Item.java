package com.pos.models;

public class Item {
    private String item_id;
    private String item_name;
    private String item_price;

    public Item(String item_id, String item_name, String item_price) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }
}
