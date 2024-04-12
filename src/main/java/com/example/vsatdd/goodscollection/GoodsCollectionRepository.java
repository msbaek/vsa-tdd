package com.example.vsatdd.goodscollection;

import java.util.List;
import java.util.Optional;

public interface GoodsCollectionRepository {
    Optional<GoodsCollection> findById(Long id);

    void save(GoodsCollection goodsCollection);

    List<Goods> findGoodsByIds(List<String> ids);
}
