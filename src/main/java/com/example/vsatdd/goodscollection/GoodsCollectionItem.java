package com.example.vsatdd.goodscollection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class GoodsCollectionItem {
    private Long id;
    private Long goodsNo;
    private String goodsId;
    private String barcode;
    @JsonIgnore
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
