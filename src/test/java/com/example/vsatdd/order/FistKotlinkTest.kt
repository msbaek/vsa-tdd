package com.example.vsatdd.order

import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.modulith.test.ApplicationModuleTest
import org.springframework.modulith.test.Scenario
import org.springframework.test.context.jdbc.Sql
import java.util.function.Supplier

@ApplicationModuleTest
@Transactional
class FistKotlinkTest(val placeOrder: PlaceOrder) {
    @Test
    @Sql("classpath:GOODS_COLLECTION.sql")
    fun `shouldCreateOrderPlaced`(scenario: Scenario) {
        val goodsId = "GD00112296"
        val quantity = 2
        val placeOrder = Supplier {
            placeOrder.place(goodsId, quantity)
        }

        scenario.stimulate(placeOrder)
            .andWaitForEventOfType(OrderPlaced::class.java)
            .toArriveAndVerify { event: OrderPlaced ->
                assertTrue(event.orderId > 0)
            }
    }
}