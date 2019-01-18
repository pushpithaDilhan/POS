package com.pos.controllers;

import com.pos.models.Order;
import com.pos.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/")
public class OrderController {

    @RequestMapping(value = "/orderlist")
    @ResponseBody
    public ArrayList<Order> showorders(){
        return OrderService.getOrderService().getOrderList();
    }
}
