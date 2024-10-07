package com.company.order.sync;

import java.util.List;

// A separate representation is needed as GRPC model cannot be used directly without custom message serializer
record JsonOrder(String id, List<JsonOrderItem> orderItems) {
    record JsonOrderItem(String id) {
    }
}
