package com.dev.backend.models;

import lombok.Getter;

import java.util.List;

@Getter
public class PaginationResponse<T> {
    private final int page;
    private final int lastPage;
    private final int itemsPerPage;
    private final List<T> content;

    public PaginationResponse(int page, int lastPage, int itemsPerPage, List<T> content) {
        this.page = page;
        this.lastPage = lastPage;
        this.itemsPerPage = itemsPerPage;
        this.content = content;
    }
}
