package com.company.order.sync;

import com.company.order.model.Order;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderServiceInProcessTest {
    private static Process process;
    private static final String URL = "http://localhost:8080/orders";

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
    void retrieveOrders() {
        RestTemplate restTemplate = new RestTemplate();
        Order[] orders = restTemplate.getForObject(URL, Order[].class);

        assertNotNull(orders, "Orders should not be null");
        System.out.println(List.of(orders));
    }

    @AfterAll
    static void stopOrderService() {
        process.destroy();
    }
}
