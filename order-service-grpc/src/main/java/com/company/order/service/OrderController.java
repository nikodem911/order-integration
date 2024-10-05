package com.company.order.service;

import com.company.order.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderRepository repository;
    private final static Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/orders")
    List<Order> findAll() {
        log.info("Returning all orders");
        return repository.findAll();
    }
}
