package com.company.order.service;

import com.company.order.api.Order;
import com.company.order.api.OrderItem;
import com.company.order.api.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class InMemoryOrderRepository implements OrderRepository {

    private List<Order> orders;

    public InMemoryOrderRepository() {
        this.orders = List.of(Order.newBuilder()
                .addOrderItems(OrderItem.newBuilder()
                        .setId("1")
                        .setProduct(Product.newBuilder()
                                .setId("p1")
                                .setName("Shoe")
                        )
                )
                .addOrderItems(OrderItem.newBuilder()
                        .setId("2")
                        .setProduct(Product.newBuilder()
                                .setId("p2")
                                .setName("Shirt")
                        )
                ).build());
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }
}
