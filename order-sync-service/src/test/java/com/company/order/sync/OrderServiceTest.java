package com.company.order.sync;

import com.company.order.api.OrderSearchRequest;
import com.company.order.api.OrderSearchResponse;
import com.company.order.api.OrderServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class OrderServiceTest {

    @Disabled
    // Test disabled by default as it requires running instance of the other service
    @Test
    void shouldCallRest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/orders";

        JsonOrder[] orders = restTemplate.getForObject(url, JsonOrder[].class);

        assertNotNull(orders, "Orders should not be null");
        Stream.of(orders).forEach(o -> log.info("order={}", o));
    }

    @Disabled
    // Test disabled by default as it requires running instance of the other service
    @Test
    void shouldCallGrpc() {
        var channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        OrderServiceGrpc.OrderServiceBlockingStub orderService = OrderServiceGrpc.newBlockingStub(channel);

        // when
        OrderSearchResponse response = orderService.searchOrders(OrderSearchRequest.newBuilder().build());

        // then
        assertNotNull(response);
        assertEquals(1, response.getOrdersList().size());


        // Close the channel
        channel.shutdown();
    }
}
