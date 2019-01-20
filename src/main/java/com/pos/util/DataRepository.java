package com.pos.util;

import com.pos.models.Item;
import com.pos.models.Order;
import com.pos.models.User;
import java.util.ArrayList;

public class DataRepository {

    private static ArrayList<Order> orderList;
    private static ArrayList<User> userList;
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
        Order o1  = new Order("o1");
        o1.addItem(new Item("i01", "Dhal","160.00"));
        o1.addItem(new Item("i02", "Rice","105.00"));
        o1.addItem(new Item("i03", "Soda","40.00"));
        o1.addItem(new Item("i04", "Beans","80.00"));
        o1.addItem(new Item("i05", "Carrot","75.00"));
        orderList.add(o1);
        lastOrder++;

        Order o2  = new Order("o2");
        o2.addItem(new Item("i06", "Soya","45.00"));
        o2.addItem(new Item("i07", "Chicken","750.00"));
        o2.addItem(new Item("i08", "Butter","190.00"));
        o2.addItem(new Item("i09", "Soda","40.00"));
        o2.addItem(new Item("i10", "Beer","120.00"));
        orderList.add(o2);
        lastOrder++;

        Order o3  = new Order("o3");
        o3.addItem(new Item("i11", "Tea leaves","160.00"));
        o3.addItem(new Item("i12", "Coffee","105.00"));
        o3.addItem(new Item("i13", "Rice","40.00"));
        o3.addItem(new Item("i14", "Potato","80.00"));
        orderList.add(o3);
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
}
