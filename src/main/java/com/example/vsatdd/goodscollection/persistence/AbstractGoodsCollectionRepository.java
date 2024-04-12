package com.example.vsatdd.goodscollection.persistence;

import com.example.vsatdd.goodscollection.domain.GoodsCollection;
import com.example.vsatdd.goodscollection.domain.GoodsCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractGoodsCollectionRepository implements GoodsCollectionRepository {
    private final GoodsCollectionJpaRepository jpaRepository;

    @Override
    public Optional<GoodsCollection> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public GoodsCollection save(GoodsCollection goodsCollection) {
        return jpaRepository.save(goodsCollection);
    }

    @Override
    public List<GoodsCollection> findByNamingContaining(String keyword, Pageable pageable) {
        return jpaRepository.findByNameContaining(keyword, pageable);
    }
}