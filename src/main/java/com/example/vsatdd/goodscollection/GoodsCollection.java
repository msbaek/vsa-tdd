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
}
