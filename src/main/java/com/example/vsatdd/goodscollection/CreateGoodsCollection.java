package com.example.vsatdd.goodscollection;

import com.example.vsatdd.goodscollection.domain.Goods;
import com.example.vsatdd.goodscollection.domain.GoodsCollection;
import com.example.vsatdd.goodscollection.domain.GoodsCollectionItem;
import com.example.vsatdd.goodscollection.domain.GoodsCollectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Controller
public class CreateGoodsCollection {
    private final GoodsCollectionRepository repository;

    @MutationMapping("createGoodsCollection")
    public Long createGoodsCollection(@Argument final CreateGoodsCollectionRequest request) {
        GoodsCollection goodsCollection = new GoodsCollection(request.name(), userId());
        List<Goods> goodsList = repository.findGoodsByIds(request.ids());
        for (Goods goods : goodsList) {
            goodsCollection.addItem(new GoodsCollectionItem(goods));
        }
        repository.save(goodsCollection);
        return goodsCollection.getId();
    }

    private Long userId() {
        return 1L;
    }

    record CreateGoodsCollectionRequest(String name, List<String> ids) {
    }
}
