package com.pos.controllers;

import com.pos.models.Order;
import com.pos.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/")
public class OrderController {

    @RequestMapping(value = "/orderlist", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Order> showorders(){
        return OrderService.getOrderService().getOrderList();
    }

    @RequestMapping(value = "/orderlist", method = RequestMethod.POST) // return 200 after post
    @ResponseStatus(value = HttpStatus.OK)
    public void addOrder(){
        String orderID = "o" + (OrderService.getOrderService().getLastOrderID() + 1);
        OrderService.getOrderService().addOrder(new Order(orderID));
    }

    @RequestMapping(value = "/orderlist/{order_id}", method = RequestMethod.GET)
    @ResponseBody
    public Order showSpecificOrders(@PathVariable String order_id){
        return OrderService.getOrderService().getSpecificOrder(order_id);
    }

    @RequestMapping(value = "/orderlist/{order_id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletepecificOrders(@PathVariable String order_id){
        OrderService.getOrderService().deleteSpecificOrder(order_id);
    }

}
