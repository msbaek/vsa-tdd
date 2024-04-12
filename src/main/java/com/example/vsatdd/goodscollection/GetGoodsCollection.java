package com.example.vsatdd.goodscollection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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

    @MutationMapping("listGoodsCollection")
    public Page<GoodsCollectionDto> listGoodsCollection(@Argument final SearchDto request) {
        return new PageImpl<>(Collections.emptyList(), PageRequest.of(request.page(), request.size()), 0);
    }

    record GoodsCollectionDto(String name, Long createdBy, LocalDateTime createdAt, Long updatedBy, LocalDateTime updatedAt, List<GoodsCollectionItemDto>goodsCollectionItems) {
    }

    record GoodsCollectionItemDto(Long goodsNo, String goodsId, String barcode) {
    }
}
