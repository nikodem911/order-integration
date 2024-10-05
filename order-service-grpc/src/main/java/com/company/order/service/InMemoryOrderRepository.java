package com.company.order.service;

import com.company.order.model.Order;
import com.company.order.model.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class InMemoryOrderRepository implements OrderRepository {

    private List<Order> orders;

    public InMemoryOrderRepository() {
        this.orders = List.of(
                new Order("order1", List.of(
                        new OrderItem("1", "1"),
                        new OrderItem("2", "3")),
                        "1"),
                new Order("order2", List.of(
                        new OrderItem("1", "2")),
                        "2")
        );
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }
}
