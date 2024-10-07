package com.company.order.service;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RestOrderController {

    private final OrderService orderService;

    public RestOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    String findAll() {
        log.info("Calling REST controller - findAll");
        return orderService.findAll().stream().map(this::toJson).toList().toString();
    }

    @SneakyThrows
    private String toJson(MessageOrBuilder object) {
        return JsonFormat.printer().print(object);
    }
}
