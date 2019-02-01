package com.pos.controllers;

import com.pos.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/")
public class OrderController {

    // Use user_id as a session parameter
    private String user_id = "u01";

    // Get order details
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    public List<HashMap<String, String>> getOrders() throws SQLException {
        return OrderService.getOrderService().getOrders(user_id);
    }

    // Get list of items and quantities of an order
    // Input(user_id, order_id)
    // Output(item_id, item_name, quantity, unit_price)
    @RequestMapping(value = "/order/{order_id}", method = RequestMethod.GET)
    @ResponseBody
    public String showSpecificOrders(@PathVariable String order_id) throws SQLException {
        return OrderService.getOrderService().getItems(user_id, Integer.parseInt(order_id)).toString();
    }

    // Create a new order
    @RequestMapping(value = "/order", method = RequestMethod.POST) // return 200 after post
    @ResponseStatus(value = HttpStatus.OK)
    public void addOrder() throws SQLException {
        OrderService.getOrderService().addOrder(user_id);
    }

    // Add item to a list
    @RequestMapping(value = "/order/{order_id}", method = RequestMethod.POST) // return 200 after post
    @ResponseStatus(value = HttpStatus.OK)
    public void addItemtoOrder(@PathVariable int order_id, @RequestParam("item_id") int item_id, @RequestParam("quantity") int quantity) throws SQLException {
        OrderService.getOrderService().addItemtoOrder(user_id, order_id, item_id, quantity);
    }

    // Delete order
    @RequestMapping(value = "/order/{order_id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteSpecificOrders(@PathVariable int order_id) throws SQLException {
        OrderService.getOrderService().deleteSpecificOrder(user_id, order_id);
    }

    // Delete item from an order
    @RequestMapping(value = "/order/{order_id}/{item_id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteSpecificItem(@PathVariable int order_id, @PathVariable int item_id) throws SQLException {
        OrderService.getOrderService().deleteSpecificItem(user_id, order_id, item_id);
    }

    // Update item quantity of an item
    @RequestMapping(value = "/order/{order_id}/{item_id}", method = RequestMethod.PUT) // return 200 after post
    @ResponseStatus(value = HttpStatus.OK)
    public void changeIteminOrder(@PathVariable int order_id, @PathVariable int item_id, @RequestParam("quantity") int new_quantity) throws SQLException {
        OrderService.getOrderService().updateItemCount(user_id, order_id, item_id, new_quantity);
    }

}
