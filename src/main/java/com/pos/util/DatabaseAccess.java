package com.pos.util;

import com.pos.models.Order;
import org.json.JSONObject;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class DatabaseAccess {

    private static Connection connection;
    private static DatabaseAccess databaseAccess;
    private ArrayList<Order> orderList;

    public static DatabaseAccess getDatabaseAccess(){
        if(databaseAccess == null){
            return new DatabaseAccess();
        }
        return databaseAccess;
    }

    public static Connection getConnection(){
        if(connection == null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos","pushpitha", "");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public JSONObject getOrderList(String user_id) throws SQLException {
        String sql = "SELECT * FROM pos.userorders WHERE user_id = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        JSONObject output = new JSONObject();
        ArrayList<JSONObject> orders = new ArrayList<>();
        while (resultSet.next()) {
            JSONObject order = new JSONObject();
            order.put("order_id",resultSet.getString("order_id"));
            order.put("order_total",resultSet.getString("order_total"));
            orders.add(order);
        }
        output.put("orders",orders);
        return output;
    }

    public String Login(String username, String password) throws SQLException, NoSuchAlgorithmException {
        String sql = "SELECT cookie FROM login WHERE username = ? AND password = ? LIMIT 1";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        // flush cookie and set new one
        if(resultSet.next() == false){
            // username and password doesnt match any record
            return "INVALID_LOGIN";
        }
        else{
            // username and password matches
            // set a new cookie - MD5 Hash of a generated UUID
            String uuid = UUID.randomUUID().toString().replace("-", "");
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(uuid));
            String cookie = String.format("%032x", new BigInteger(1, md5.digest()));
            String cookieInsertsql = "UPDATE login SET cookie = ? WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement_cookie = getConnection().prepareStatement(cookieInsertsql);
            preparedStatement_cookie.setString(1, cookie);
            preparedStatement_cookie.setString(2, username);
            preparedStatement_cookie.setString(3, password);
            preparedStatement_cookie.executeUpdate();
            return cookie;
        }
    }

    public String Authorize(String cookie) throws SQLException {
        String sql = "SELECT user_id FROM login WHERE cookie = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1, cookie);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next() == false){
            // username and password doesnt match any record
            return "INVALID_USER";
        }
        else{
            return resultSet.getString(1);
        }
    }

    public JSONObject getItems(String user_id, int order_id) throws SQLException {
        String sql = "SELECT useritems.item_id, item_name, quantity, item_price " +
                "FROM pos.useritems JOIN pos.items ON useritems.item_id = items.item_id " +
                "WHERE user_id = ? AND order_id = ?;";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1, user_id);
        preparedStatement.setInt(2, order_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        JSONObject output = new JSONObject();
        ArrayList<JSONObject> items = new ArrayList<>();
        while (resultSet.next()) {
            JSONObject item = new JSONObject();
            item.put("item_id", resultSet.getString("item_id"));
            item.put("item_name", resultSet.getString("item_name"));
            item.put("quantity", resultSet.getInt("quantity"));
            item.put("item_price", resultSet.getDouble("item_price"));
            items.add(item);
        }
        output.put("items", items.toArray());
        return output;
    }

    public void addOrder(String user_id) throws SQLException {
        // get maximum order_id for a specific user
        String max_sql = "SELECT MAX(order_id) FROM pos.userorders WHERE user_id = ?;";
        PreparedStatement max_preparedStatement = getConnection().prepareStatement(max_sql);
        max_preparedStatement.setString(1, user_id);
        ResultSet resultSet = max_preparedStatement.executeQuery();
        resultSet.first();
        int order_id = resultSet.getInt(1) + 1;

        String sql = "INSERT INTO  pos.userorders values (?, ?, ?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1, user_id);
        preparedStatement.setInt(2, order_id);
        preparedStatement.setDouble(3, 0.00);
        preparedStatement.executeUpdate();
    }

    public void addItemtoOrder(String user_id, int order_id, int item_id, int quantity) throws SQLException {
        String sql = "INSERT INTO  pos.useritems values (?, ?, ?, ?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1, user_id);
        preparedStatement.setInt(2, order_id);
        preparedStatement.setInt(3, item_id);
        preparedStatement.setDouble(4, quantity);
        preparedStatement.executeUpdate();
        // adjust order total
        UpdateTotal(user_id, order_id);
    }

    public void deleteOrder(String user_id, int order_id) throws SQLException {
        String sql = "DELETE FROM pos.userorders WHERE user_id = ? AND order_id = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1, user_id);
        preparedStatement.setInt(2, order_id);
        preparedStatement.executeUpdate();
    }

    public void deleteItemFromOrder(String user_id, int order_id, int item_id) throws SQLException {
        String sql = "DELETE FROM pos.useritems WHERE user_id = ? AND order_id = ? AND item_id = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1, user_id);
        preparedStatement.setInt(2, order_id);
        preparedStatement.setInt(3, item_id);
        preparedStatement.executeUpdate();
        // adjust order_total - edit the relevant row of userorders
        UpdateTotal(user_id, order_id);
    }

    public void updateItemCount(String user_id, int order_id, int item_id, int new_quantity) throws SQLException {
        String sql = "UPDATE pos.useritems SET quantity = ? WHERE user_id =? AND order_id = ? AND item_id = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, new_quantity);
        preparedStatement.setString(2, user_id);
        preparedStatement.setInt(3, order_id);
        preparedStatement.setInt(4, item_id);
        preparedStatement.executeUpdate();
        // adjust order_total
        UpdateTotal(user_id, order_id);
    }

    public void UpdateTotal(String user_id, int order_id) throws SQLException {
        // get the total from useritems table
        String total_sql = "SELECT SUM(quantity*item_price) FROM pos.useritems JOIN pos.items ON useritems.item_id = items.item_id WHERE user_id = ? AND order_id = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(total_sql);
        preparedStatement.setString(1, user_id);
        preparedStatement.setInt(2, order_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.first();
        double sum = resultSet.getDouble(1);

        // update the total in userorders
        String update_sql = "UPDATE pos.userorders SET order_total = ? WHERE user_id = ? AND order_id = ?";
        PreparedStatement update_preparedStatement = getConnection().prepareStatement(update_sql);
        update_preparedStatement.setDouble(1, sum);
        update_preparedStatement.setString(2, user_id);
        update_preparedStatement.setInt(3, order_id);
        update_preparedStatement.executeUpdate();
    }

    // Clear db records
    public void clearDB(String tablename) throws SQLException {
        String sql = "DELETE FROM " + tablename;
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

}
