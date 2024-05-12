package com.example.vsatdd.order;

import com.example.vsatdd.order.domain.Order;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Transactional
@Service
@RequiredArgsConstructor
public class PlaceOrder {
    private final ApplicationEventPublisher events;
    private static final AtomicLong orderId = new AtomicLong(1);

    public Long place(String goodsId, int quantity) {
        Order order = new Order(goodsId, quantity);
        assignId(order);
        events.publishEvent(new OrderPlaced(order.getId()));
        return order.getId();
    }

    private void assignId(Order order) {
        order.assignId(orderId.getAndIncrement());
    }
}

record OrderPlaced(Long orderId) {
}