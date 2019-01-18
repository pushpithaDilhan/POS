package com.pos.controllers;

import com.pos.models.Order;
import com.pos.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/")
public class OrderController {

    @RequestMapping(value = "/orderlist", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Order> showorders(){
        return OrderService.getOrderService().getOrderList();
    }

    @RequestMapping(value = "/orderlist/{order_id}", method = RequestMethod.GET)
    @ResponseBody
    public Order showSpecificOrders(){
        return OrderService.getOrderService().getSpecificOrder("o2");
    }
}
