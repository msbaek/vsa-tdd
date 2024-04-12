package com.example.vsatdd.goodscollection;

public record Goods(Long goodsNo, String goodsId, String barcode) {
    public static Goods of(Object[] row) {
        return new Goods((Long) row[0], (String) row[1], (String) row[2]);
    }
}
