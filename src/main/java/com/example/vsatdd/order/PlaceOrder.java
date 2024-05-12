package com.example.vsatdd.order;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class PlaceOrder {
    public Long place(String goodsId, int quantity) {
        throw new UnsupportedOperationException("PlaceOrder::place not implemented yet");
    }
}

record OrderPlaced(Long orderId) {
}