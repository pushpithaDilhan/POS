package com.pos.util;

import com.pos.models.Item;
import com.pos.models.Order;
import com.pos.models.User;
import java.util.ArrayList;
import java.util.HashMap;

public class DataRepository {

//    private static ArrayList<Order> orderList;
//    private static ArrayList<User> userList;
//    private static HashMap<String, Integer> lastItem;
//    private static DataRepository dataRepository;
//    private static int lastOrder;
//
//    public static DataRepository getDataRepository(){
//        if(dataRepository == null){
//            dataRepository = new DataRepository();
//            seedRepository();
//        }
//        return dataRepository;
//    }
//
//    public static ArrayList<Order> getOrderList(){
//        if(orderList == null){
//            seedRepository();
//        }
//        return orderList;
//    }
//
//    // Create Order and add it updating last item of that order
//    public static void addOrder(Order order, int last_item){
//        if(orderList == null){
//            orderList = new ArrayList<Order>();
//        }
//        if(lastItem == null){
//            lastItem = new HashMap<String, Integer>();
//        }
//        lastItem.put(order.getOrder_id(), last_item);
//        lastOrder = Integer.parseInt(order.getOrder_id());
//        getOrderList().add(order);
//    }
//
//    // Read order using order_id
//    public static Order getOrder(String order_id, String user_id){
//        for (Order order: getOrderList()) {
//            if(order.getOrder_id().equals(order_id) && order.getUser_id().equals(user_id)){
//                return order;
//            }
//        }
//        return null;
//    }
//
//    public static HashMap<String, String> getOrders(String user_id){
//        HashMap<String, String> output = new HashMap<>();
//        for (Order order: orderList) {
//            if(order.getUser_id().equals(user_id)){
//                output.put(order.getOrder_id(),Double.toString(order.getOrder_total()));
//            }
//        }
//        return output;
//    }
//
//    // Delete order
//    public static void deleteOrder(String order_id){
//        for(int i = 0; i< orderList.size(); i++){
//            if(orderList.get(i).getOrder_id().equals(order_id)){
//                orderList.remove(i);
//            }
//        }
//    }
//
//    // Delete item from an order
//    public static void deleteItem(String order_id, String item_id){
//        for(int i = 0; i< orderList.size(); i++){
//            if(orderList.get(i).getOrder_id().equals(order_id)){
//                for(int j = 0; j< orderList.get(i).getItemlist().size(); j++){
//                    if(orderList.get(i).getItemlist().get(j).getItem_id().equals(item_id)){
//                        orderList.get(i).getItemlist().remove(j);
//                    }
//                }
//            }
//        }
//    }
//
//    // Add item to an order
//    public static void addItemtoOrder(String order_id, String item_name, String item_price){
//        for(int i = 0; i < getOrderList().size(); i++){
//            if(getOrderList().get(i).getOrder_id().equals(order_id)){
//                getOrderList().get(i).addItem(new Item(Integer.toString(lastItem.get(order_id) + 1) ,item_name, item_price));
//            }
//        }
//    }
//
//    public static void seedRepository(){
//        Order o1  = new Order("u01","1");
//        o1.addItem(new Item("1", "Dhal","160.00"));
//        o1.addItem(new Item("2", "Rice","105.00"));
//        o1.addItem(new Item("3", "Soda","40.00"));
//        o1.addItem(new Item("4", "Beans","80.00"));
//        o1.addItem(new Item("5", "Carrot","75.00"));
//        addOrder(o1, 5);
//
//        Order o2  = new Order("u01","2");
//        o2.addItem(new Item("1", "Soya","45.00"));
//        o2.addItem(new Item("2", "Chicken","750.00"));
//        o2.addItem(new Item("3", "Butter","190.00"));
//        o2.addItem(new Item("4", "Soda","40.00"));
//        o2.addItem(new Item("5", "Beer","120.00"));
//        addOrder(o2, 5);
//
//        Order o3  = new Order("u01","3");
//        o3.addItem(new Item("1", "Tea leaves","160.00"));
//        o3.addItem(new Item("2", "Coffee","105.00"));
//        o3.addItem(new Item("3", "Rice","40.00"));
//        o3.addItem(new Item("4", "Potato","80.00"));
//        addOrder(o3, 4);
//
//        Order o4  = new Order("u02","1");
//        o4.addItem(new Item("1", "Tea leaves","160.00"));
//        o4.addItem(new Item("2", "Coffee","105.00"));
//        o4.addItem(new Item("3", "Mix Rice","40.00"));
//        o4.addItem(new Item("4", "Potato","80.00"));
//        o4.addItem(new Item("5", "Pizza","780.00"));
//        addOrder(o4, 5);
//    }
//
//    public static void clearRepository(){
//        DataRepository dataRepository = DataRepository.getDataRepository();
//        orderList.clear();
//        lastItem.clear();
//        lastOrder = 0;
//    }
//
//    public static int getLastOrder(){
//        return lastOrder;
//    }
//
//    public static int getLastItemId(String order_id){
//        return lastItem.get(order_id);
//    }
//
//    public static void seedusers(){
//        userList = new ArrayList<User>();
//        userList.add(new User("u01", "0AD28F73D534E4CF3941A477375D1D75", "E3A6B8B322FD02D0A5172256ECED4301"));
//    }
//
//    public static ArrayList<User> getUserList(){
//        if(userList == null){
//            seedusers();
//        }
//        return userList;
//    }

}
