package com.company.order.service;

import com.company.order.api.OrderSearchRequest;
import com.company.order.api.OrderSearchResponse;
import com.company.order.api.OrderServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class GrpcOrderController extends OrderServiceGrpc.OrderServiceImplBase {

    private final OrderService orderService;

    public GrpcOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void searchOrders(OrderSearchRequest request, StreamObserver<OrderSearchResponse> responseObserver) {
        log.info("Calling GRPC controller - search");
        responseObserver.onNext(OrderSearchResponse.newBuilder().addAllOrders(orderService.findAll()).build());
        responseObserver.onCompleted();
    }
}
