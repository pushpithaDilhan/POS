package com.pos.util;

import com.pos.models.Item;
import com.pos.models.Order;
import com.pos.models.User;
import java.util.ArrayList;
import java.util.HashMap;

public class DataRepository {

    private static ArrayList<Order> orderList;
    private static ArrayList<User> userList;
    private static HashMap<String, Integer> lastItem;
    private static DataRepository dataRepository;
    private static int lastOrder;

    public static DataRepository getDataRepository(){
        if(dataRepository == null){
            dataRepository = new DataRepository();
            seedRepository();
        }
        return dataRepository;
    }

    public static ArrayList<Order> getOrderList(){
        if(orderList == null){
            seedRepository();
        }
        return orderList;
    }

    public static Order getOrder(String order_id){
        for (Order order: getOrderList()) {
            if(order.getOrder_id().equals(order_id)){
                return order;
            }
        }
        return null;
    }

    public static void deleteOrder(String order_id){
        for(int i = 0; i< orderList.size(); i++){
            if(orderList.get(i).getOrder_id().equals(order_id)){
                orderList.remove(i);
            }
        }
    }

    public static void deleteItem(String order_id, String item_id){
        for(int i = 0; i< orderList.size(); i++){
            if(orderList.get(i).getOrder_id().equals(order_id)){
                for(int j = 0; j< orderList.get(i).getItemlist().size(); j++){
                    if(orderList.get(i).getItemlist().get(j).getItem_id().equals(item_id)){
                        orderList.get(i).getItemlist().remove(j);
                    }
                }
            }
        }
    }

    public static ArrayList<User> getUserList(){
        if(userList == null){
            seedusers();
        }
        return userList;
    }

    public static void seedRepository(){
        orderList = new ArrayList<Order>();
        lastItem = new HashMap<String, Integer>();
        Order o1  = new Order("1");
        o1.addItem(new Item("1", "Dhal","160.00"));
        o1.addItem(new Item("2", "Rice","105.00"));
        o1.addItem(new Item("3", "Soda","40.00"));
        o1.addItem(new Item("4", "Beans","80.00"));
        o1.addItem(new Item("5", "Carrot","75.00"));
        orderList.add(o1);
        lastItem.put("1", 5);
        lastOrder++;

        Order o2  = new Order("2");
        o2.addItem(new Item("1", "Soya","45.00"));
        o2.addItem(new Item("2", "Chicken","750.00"));
        o2.addItem(new Item("3", "Butter","190.00"));
        o2.addItem(new Item("4", "Soda","40.00"));
        o2.addItem(new Item("5", "Beer","120.00"));
        orderList.add(o2);
        lastItem.put("2", 5);
        lastOrder++;

        Order o3  = new Order("3");
        o3.addItem(new Item("1", "Tea leaves","160.00"));
        o3.addItem(new Item("2", "Coffee","105.00"));
        o3.addItem(new Item("3", "Rice","40.00"));
        o3.addItem(new Item("4", "Potato","80.00"));
        orderList.add(o3);
        lastItem.put("3", 4);
        lastOrder++;
    }

    public static void seedusers(){
        userList = new ArrayList<User>();
        userList.add(new User("u01", "0AD28F73D534E4CF3941A477375D1D75", "E3A6B8B322FD02D0A5172256ECED4301"));
    }

    public static int getLastOrder(){
        return lastOrder;
    }

    public static void addOrder(Order order){
        getOrderList().add(order);
    }

    public static void addItemtoOrder(String order_id, String item_name, String item_price){
        for(int i = 0; i < getOrderList().size(); i++){
            if(getOrderList().get(i).getOrder_id().equals(order_id)){
                getOrderList().get(i).addItem(new Item(Integer.toString(lastItem.get(order_id) + 1) ,item_name, item_price));
            }
        }
    }
}
