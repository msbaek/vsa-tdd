package com.example.vsatdd.goodscollection;

import lombok.Getter;

@Getter
public class GoodsCollectionItem {
    private Long id;
    private Long goodsNo;
    private String goodsId;
    private String barcode;
    private GoodsCollection goodsCollection;
}
