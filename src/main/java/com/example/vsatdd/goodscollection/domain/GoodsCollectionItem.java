package com.example.vsatdd.goodscollection.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class GoodsCollectionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long goodsNo;
    private String goodsId;
    private String barcode;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "GOODS_COLLECTION_ID", nullable = false)
    private GoodsCollection goodsCollection;

    public GoodsCollectionItem(Goods goods) {
        this.goodsNo = goods.goodsNo();
        this.goodsId = goods.goodsId();
        this.barcode = goods.barcode();
    }

    public void setGoodsCollection(GoodsCollection goodsCollection) {
        this.goodsCollection = goodsCollection;
    }
}
