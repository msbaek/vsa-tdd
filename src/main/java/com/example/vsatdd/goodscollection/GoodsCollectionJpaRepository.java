package com.example.vsatdd.goodscollection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsCollectionJpaRepository extends JpaRepository<GoodsCollection, Long> {
    List<GoodsCollection> findByNameContaining(String keyword, Pageable pageable);
}
