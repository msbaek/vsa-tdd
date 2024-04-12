package com.example.vsatdd.goodscollection;

import com.ktown4u.utils.Neutralizer;
import com.ktown4u.utils.YamlPrinter;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CreateGoodsCollectionTest {
    private GoodsCollectionRepository repository;
    private CreateGoodsCollection createGoodsCollection;

    @BeforeEach
    void setUp() {
        repository = new GoodsCollectionInMemoryRepository();
        createGoodsCollection = new CreateGoodsCollection(repository);
    }

    @Test
    void create_goods_collection_returns_newly_created_id() {
        Long newlyCreatedId = createGoodsCollection.createGoodsCollection(
                new CreateGoodsCollection.CreateGoodsCollectionRequest(
                        "Collection 1",
                        List.of("GD00112296", "GD00112297", "9000000112298", "9000000112299")));
        assertThat(newlyCreatedId).isGreaterThan(0L);
        Approvals.verify(
                Neutralizer.localDateTime( // LcoalDateTime 형식 문자열을 지정된 문자열로 치환하여 시간 변경을 무력화
                        YamlPrinter.printWithExclusions( // YAML 형식으로 데이터를 출력하는데 "id" 필드는 제외함
                                repository.findById(newlyCreatedId).get(), "id")
                )
        );
    }
}