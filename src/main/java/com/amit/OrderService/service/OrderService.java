package com.amit.OrderService.service;

import com.amit.OrderService.model.OrderRequest;

public interface OrderService {

    long placeOrder(OrderRequest orderRequest);
}
