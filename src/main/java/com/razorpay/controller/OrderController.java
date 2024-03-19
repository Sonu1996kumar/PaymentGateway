package com.razorpay.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.payload.OrderDetails;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    //http://localhost:8080/api/order
//    @PostMapping
//    public String createOrder() throws RazorpayException {
//        RazorpayClient razorpay = new RazorpayClient("rzp_test_xWB7uVTUSzKSBO", "jjYwrTRfENLmyHJkQ6aoJ5XH");
//
//        JSONObject orderRequest = new JSONObject();
//        orderRequest.put("amount",50000);
//        orderRequest.put("currency","INR");
//        orderRequest.put("receipt", "receipt#1");
//        JSONObject notes = new JSONObject();
//        notes.put("notes_key_1","Tea, Earl Grey, Hot");
//        notes.put("notes_key_1","Tea, Earl Grey, Hot");
//        orderRequest.put("notes",notes);
//
//        Order order = razorpay.orders.create(orderRequest);
//        return order.get("id").toString();
//    }

    @PostMapping
    public String createOrder(@RequestBody OrderDetails orderDetails) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient("rzp_test_xWB7uVTUSzKSBO", "jjYwrTRfENLmyHJkQ6aoJ5XH");

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",orderDetails.getAmount());
        orderRequest.put("currency",orderDetails.getCurrency());
        orderRequest.put("receipt", UUID.randomUUID().toString());
        JSONObject notes = new JSONObject();
        notes.put(orderDetails.getNoteSubject(),orderDetails.getNoteContent());
        orderRequest.put("notes",notes);

        Order order = razorpay.orders.create(orderRequest);
        return order.get("id").toString();
    }
}
