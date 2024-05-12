package com.example.vsatdd.order.domain;

import lombok.Getter;

@Getter
public class Order {
    private Long id;
    private final String goodsId;
    private final int quantity;

    public Order(String goodsId, int quantity) {
        this.goodsId = goodsId;
        this.quantity = quantity;
    }

    public void assignId(Long id) {
        this.id = id;
    }
}
