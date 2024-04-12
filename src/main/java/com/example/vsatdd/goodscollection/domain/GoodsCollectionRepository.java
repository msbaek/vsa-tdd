package com.example.vsatdd.goodscollection.domain;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GoodsCollectionRepository {
    Optional<GoodsCollection> findById(Long id);

    GoodsCollection save(GoodsCollection goodsCollection);

    List<Goods> findGoodsByIds(List<String> ids);

    List<GoodsCollection> findByNamingContaining(String keyword, Pageable pageable);
}
