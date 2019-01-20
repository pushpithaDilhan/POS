package com.pos.services;

import com.pos.models.Order;
import com.pos.util.DataRepository;
import java.util.ArrayList;

public class OrderService {

    private static OrderService orderService;

    public static OrderService getOrderService(){
        if (orderService == null){
            orderService = new OrderService();
        };
        return orderService;
    }

    public ArrayList<Order> getOrderList(){
        return DataRepository.getOrderList();
    }

    public Order getSpecificOrder(String order_id){
        return DataRepository.getDataRepository().getOrder(order_id);
    }

    public void deleteSpecificOrder(String order_id){
        DataRepository.getDataRepository().deleteOrder(order_id);
    }

    public void deleteSpecificItem(String order_id, String item_id){
        DataRepository.getDataRepository().deleteItem(order_id, item_id);
    }

    public void addOrder(Order order){
        DataRepository.getDataRepository().addOrder(order);
    }

    public void addItemtoOrder(String order_id, String item_name, String item_price){
        DataRepository.getDataRepository().addItemtoOrder(order_id, item_name, item_price);
    }

    public int getLastOrderID(){
        return DataRepository.getDataRepository().getLastOrder();
    }

}
