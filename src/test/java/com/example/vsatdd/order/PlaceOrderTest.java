package com.example.vsatdd.order;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
@Transactional
class PlaceOrderTest {
    @Autowired private PlaceOrder placeOrder;
    @Test
    @Sql("classpath:GOODS_COLLECTION.sql")
    void shouldCreateOrderPlaced(Scenario scenario) {
        String goodsId = "GD00112296";
        int quantity = 2;
        scenario.stimulate(() -> placeOrder.place(goodsId, quantity))
                .andWaitForEventOfType(OrderPlaced.class)
                .toArriveAndVerify((event, dto) -> {
                    assertThat(event.orderId()).isGreaterThan(0l);
                });
    }
}