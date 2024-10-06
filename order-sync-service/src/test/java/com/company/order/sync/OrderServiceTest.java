package com.company.order.sync;

import com.company.order.model.Order;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderServiceTest {

    @Disabled
    // Test disabled by default as it requires running instance of the other service
    @Test
    void shouldCallRealInstance() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/orders";

        Order[] orders = restTemplate.getForObject(url, Order[].class);

        assertNotNull(orders, "Orders should not be null");
        System.out.println(List.of(orders));
    }
}
