package com.example.vsatdd.goodscollection;

import com.ktown4u.utils.Neutralizer;
import com.ktown4u.utils.YamlPrinter;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

class CreateGoodsCollectionTest {
    /**
     * 실제 DB에서 죄회한 상품 정보를 담고 있는 Map
     * 순서도 유지함
     */
    private final Map<Long, Goods> goodsMap = List.of(
                    new Goods(112296L, "GD00112296", "9000000112296"),
                    new Goods(112297L, "GD00112297", "9000000112297"),
                    new Goods(112298L, "GD00112298", "9000000112298"),
                    new Goods(112299L, "GD00112299", "9000000112299"),
                    new Goods(112300L, "GD00112300", "9000000112300"),
                    new Goods(112301L, "GD00112301", "9000000112300"),
                    new Goods(112303L, "GD00112303", "9000000112303"),
                    new Goods(112304L, "GD00112304", "9000000112304"),
                    new Goods(112306L, "GD00112306", "9000000112306"),
                    new Goods(112307L, "GD00112307", "9000000112307"))
            .stream()
            .collect(toMap(Goods::goodsNo, Function.identity()));
    private final AtomicLong goodsCollectionId = new AtomicLong(1L);
    private Map<Long, GoodsCollection> goodsCollectionMap = new HashMap<>();

    @Test
    void create_goods_collection_returns_newly_created_id() {
        Long newlyCreatedId = createGoodsCollection(
                new CreateGoodsCollection.CreateGoodsCollectionRequest(
                        "Collection 1",
                        List.of("GD00112296", "GD00112297", "9000000112298", "9000000112299")));
        assertThat(newlyCreatedId).isGreaterThan(0L);
        Approvals.verify(
                Neutralizer.localDateTime( // LcoalDateTime 형식 문자열을 지정된 문자열로 치환하여 시간 변경을 무력화
                        YamlPrinter.printWithExclusions( // YAML 형식으로 데이터를 출력하는데 "id" 필드는 제외함
                                findById(newlyCreatedId).get(), "id")
                )
        );
    }

    private Optional<GoodsCollection> findById(Long id) {
        return Optional.ofNullable(goodsCollectionMap.get(id));
    }

    private Long createGoodsCollection(CreateGoodsCollection.CreateGoodsCollectionRequest request) {
        GoodsCollection goodsCollection = new GoodsCollection(request.name(), userId());
        List<Goods> goodsList = findGoodsByIds(request.ids());
        for (Goods goods : goodsList) {
            goodsCollection.addItem(new GoodsCollectionItem(goods));
        }
        save(goodsCollection);
        return goodsCollection.getId();
    }

    private void save(GoodsCollection goodsCollection) {
        goodsCollection.setId(goodsCollectionId.getAndIncrement());
        goodsCollectionMap.put(goodsCollection.getId(), goodsCollection);
    }

    private List<Goods> findGoodsByIds(List<String> ids) {
        return goodsMap.values().stream()
                .filter(goods -> ids.contains(goods.goodsId()) || ids.contains(goods.barcode()))
                .sorted(Comparator.comparing(Goods::goodsId)) // DB 쿼리와 정렬 순서를 맞추기 위해
                .toList();
    }

    private Long userId() {
        return 1L;
    }
}