package com.example.vsatdd.goodscollection;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GoodsCollection {
    private Long id;
    private String name;
    private Long createdBy;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;
    private List<GoodsCollectionItem> goodsCollectionItems = new ArrayList<>();

    public GoodsCollection(String name, Long createdBy) {
        this.name = name;
        this.createdBy = this.updatedBy = createdBy;
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    public void addItem(GoodsCollectionItem goodsCollectionItem) {
        goodsCollectionItems.add(goodsCollectionItem);
        goodsCollectionItem.setGoodsCollection(this);
    }

    public void setId(long id) {
        this.id = id;
    }
}
