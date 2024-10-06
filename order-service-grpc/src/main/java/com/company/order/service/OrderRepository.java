package com.company.order.service;

import com.company.order.api.Order;

import java.util.List;

public interface OrderRepository {

    List<Order> findAll();
}
