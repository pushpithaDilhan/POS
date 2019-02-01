package com.pos.services;

import com.pos.util.DatabaseAccess;
import org.json.JSONObject;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class OrderService {

    private static OrderService orderService;

    public static OrderService getOrderService(){
        if (orderService == null){
            orderService = new OrderService();
        };
        return orderService;
    }

    public List<HashMap<String, String>> getOrders(String user_id) throws SQLException {
        return DatabaseAccess.getDatabaseAccess().getOrderList(user_id);
    }

    public JSONObject getItems(String user_id, int order_id) throws SQLException {
        return DatabaseAccess.getDatabaseAccess().getItems(user_id, order_id);
    }

    public void addOrder(String user_id) throws SQLException {
        DatabaseAccess.getDatabaseAccess().addOrder(user_id);
    }

    public void addItemtoOrder(String user_id, int order_id, int item_id, int quantity) throws SQLException {
        DatabaseAccess.getDatabaseAccess().addItemtoOrder(user_id, order_id, item_id, quantity);
    }

    public void deleteSpecificOrder(String user_id, int order_id) throws SQLException {
        DatabaseAccess.getDatabaseAccess().deleteOrder(user_id, order_id);
    }

    public void deleteSpecificItem(String user_id, int order_id, int item_id) throws SQLException {
        DatabaseAccess.getDatabaseAccess().deleteItemFromOrder(user_id, order_id, item_id);
    }

    public void updateItemCount(String user_id, int order_id, int item_id, int new_quantity) throws SQLException {
        DatabaseAccess.getDatabaseAccess().updateItemCount(user_id, order_id, item_id, new_quantity);
    }
}
