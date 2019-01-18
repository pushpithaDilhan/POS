package com.pos.services;

import com.pos.models.Order;
import com.pos.util.DataRepository;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderService {

    private static OrderService orderService;

    static{
        if (orderService == null){
            orderService = new OrderService();
        };
    }

    public static OrderService getOrderService(){
        return orderService;
    }

    public ArrayList<Order> getOrderList(){
        return DataRepository.getOrderList();
    }

    public void addOrder(){
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));
    }

}
