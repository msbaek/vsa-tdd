package com.example.vsatdd.goodscollection;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

@Profile("in-memory")
@Repository
public class GoodsCollectionInMemoryRepository implements GoodsCollectionRepository {
    /**
     * 실제 DB에서 죄회한 상품 정보를 담고 있는 Map
     * 순서도 유지함
     */
    final Map<Long, Goods> goodsMap = List.of(
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
            .collect(Collectors.toMap(Goods::goodsNo, Function.identity()));
    final AtomicLong goodsCollectionId = new AtomicLong(1L);
    Map<Long, GoodsCollection> goodsCollectionMap = new HashMap<Long, GoodsCollection>();

    public GoodsCollectionInMemoryRepository() {
    }

    @Override
    public Optional<GoodsCollection> findById(Long id) {
        return Optional.ofNullable(goodsCollectionMap.get(id));
    }

    @Override
    public void save(GoodsCollection goodsCollection) {
        goodsCollection.setId(goodsCollectionId.getAndIncrement());
        goodsCollectionMap.put(goodsCollection.getId(), goodsCollection);
    }

    @Override
    public List<Goods> findGoodsByIds(List<String> ids) {
        return goodsMap.values().stream()
                .filter(goods -> ids.contains(goods.goodsId()) || ids.contains(goods.barcode()))
                .sorted(Comparator.comparing(Goods::goodsId)) // DB 쿼리와 정렬 순서를 맞추기 위해
                .toList();
    }
}