package com.amit.OrderService.service;

import com.amit.OrderService.entity.Order;
import com.amit.OrderService.external.client.ProductService;
import com.amit.OrderService.model.OrderRequest;
import com.amit.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Log4j2
@Service
public class OrderServiceImpl implements OrderService{
   @Autowired
   private OrderRepository orderRepository;
   @Autowired
   private ProductService productService;
    @Override
    public long placeOrder(OrderRequest orderRequest) {
        //order Entity will be created to database
        //Product service to block the product
        log.info("Order Request:{}", orderRequest);
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
        log.info("Creating Order with placed status");
        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("PLACED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
       order= orderRepository.save(order);
        log.info("Order Placed" );

        return order.getId();
    }
}
