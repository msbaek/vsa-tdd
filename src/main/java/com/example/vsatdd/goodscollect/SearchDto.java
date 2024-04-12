package com.example.vsatdd.goodscollect;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public record SearchDto(String type, String keyword, Integer page, Integer size, SortDto sort) {
    public String sortBy() {
        return sort().by();
    }

    public String sortDirection() {
        return sort().direction();
    }

    public Pageable pageable() {
        return PageRequest.of(page(), size());
    }

    public int offset() {
        return page * size;
    }

    public boolean isAscending() {
        return "asc".equals(sortDirection());
    }

    public Pageable pageableWithSort() {
        Sort sort = isAscending() ?
                Sort.by(Sort.Order.asc(sortBy()))
                : Sort.by(Sort.Order.desc(sortBy()));
        return PageRequest.of(page(), size(), sort);
    }
}

record SortDto(String by, String direction) {
}