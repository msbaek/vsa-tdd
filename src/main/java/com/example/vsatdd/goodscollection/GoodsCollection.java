package com.example.vsatdd.goodscollection;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "GOODS_COLLECTION")
public class GoodsCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long createdBy;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "goodsCollection", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
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
