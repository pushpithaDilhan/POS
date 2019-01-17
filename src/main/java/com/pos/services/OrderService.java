package com.pos.services;

import org.bson.Document;

import java.util.Arrays;

public class OrderService {

    public void addOrder(){
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));
    }
}
