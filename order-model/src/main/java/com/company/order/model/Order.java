package com.company.order.model;

import java.util.List;

public record Order(String id, List<OrderItem> orderItems, String customerId) {

}
