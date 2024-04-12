package com.example.vsatdd.goodscollection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Controller
public class GetGoodsCollection {
    private final GoodsCollectionRepository repository;

    @QueryMapping("listGoodsCollection")
    public Page<GoodsCollectionDto> listGoodsCollection(@Argument final SearchDto request) {
        List<GoodsCollection> goodsCollections = repository.findByNamingContaining(request.keyword(), request.pageableWithSort());
        List<GoodsCollectionDto> result = goodsCollections.stream()
                .map(GoodsCollectionDto::from)
                .toList();
        return new PageImpl<>(result, PageRequest.of(request.page(), request.size()), result.size());
    }

    record GoodsCollectionDto(String name, Long createdBy, LocalDateTime createdAt, Long updatedBy, LocalDateTime updatedAt, List<GoodsCollectionItemDto>goodsCollectionItems) {
        public static GoodsCollectionDto from(GoodsCollection goodsCollection) {
            List<GoodsCollectionItemDto> itemDtoList = goodsCollection.getGoodsCollectionItems().stream()
                    .map(GoodsCollectionItemDto::from)
                    .toList();
            return new GoodsCollectionDto(goodsCollection.getName(), goodsCollection.getCreatedBy(), goodsCollection.getCreatedAt(), goodsCollection.getUpdatedBy(), goodsCollection.getUpdatedAt(), itemDtoList);
        }
    }

    record GoodsCollectionItemDto(Long goodsNo, String goodsId, String barcode) {
        public static GoodsCollectionItemDto from(GoodsCollectionItem goodsCollectionItem) {
            return new GoodsCollectionItemDto(goodsCollectionItem.getGoodsNo(), goodsCollectionItem.getGoodsId(), goodsCollectionItem.getBarcode());
        }
    }
}
