package com.company.order.sync;

import com.company.order.api.OrderSearchRequest;
import com.company.order.api.OrderSearchResponse;
import com.company.order.api.OrderServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class OrderServiceInProcessTest {
    private static Process process;
    private static final String REST_URL = "http://localhost:8080/orders";

    @BeforeAll
    static void startOrderService() throws IOException, InterruptedException {
        var serviceJarFile = "order-service-grpc.jar";
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "target/service/" + serviceJarFile);
        processBuilder.inheritIO();
        // TODO: add error handling
        process = processBuilder.start();

        // Wait for the service to start
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    void retrieveOrdersJson() {
        RestTemplate restTemplate = new RestTemplate();
        JsonOrder[] orders = restTemplate.getForObject(REST_URL, JsonOrder[].class);

        assertNotNull(orders, "Orders should not be null");
        assertEquals(1, orders.length);
        log.info("REST orders = {}", List.of(orders));
    }

    @Test
    void retrieveOrdersGrpc() {
        var channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        OrderServiceGrpc.OrderServiceBlockingStub orderService = OrderServiceGrpc.newBlockingStub(channel);

        // when
        OrderSearchResponse response = orderService.searchOrders(OrderSearchRequest.newBuilder().build());

        // then
        assertNotNull(response);
        assertEquals(1, response.getOrdersList().size());
        log.info("GRPC orders = {}", response.getOrdersList());

        // Close the channel
        channel.shutdown();
    }

    @AfterAll
    static void stopOrderService() {
        process.destroy();
    }


}
