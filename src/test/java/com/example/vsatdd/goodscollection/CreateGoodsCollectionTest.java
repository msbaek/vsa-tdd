package com.example.vsatdd.goodscollection;

import com.ktown4u.utils.Neutralizer;
import com.ktown4u.utils.YamlPrinter;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CreateGoodsCollectionTest {
    @Test
    void create_goods_collection_returns_newly_created_id() {
        Long newlyCreatedId = createGoodsCollection(
                new CreateGoodsCollection.CreateGoodsCollectionRequest(
                        "Collection 1",
                        List.of("GD00112296", "GD00112297", "9000000112298", "9000000112299")));
        assertThat(newlyCreatedId).isGreaterThan(0L);
        Approvals.verify(
                Neutralizer.localDateTime(
                        YamlPrinter.printWithExclusions(
                                findById(newlyCreatedId).get(), "id")
                )
        );
    }

    private Optional<GoodsCollection> findById(Long id) {
        throw new UnsupportedOperationException("CreateGoodsCollectionTest::findById not implemented yet");
    }

    private Long createGoodsCollection(CreateGoodsCollection.CreateGoodsCollectionRequest createGoodsCollectionRequest) {
        throw new UnsupportedOperationException("CreateGoodsCollectionTest::createGoodsCollection not implemented yet");
    }
}