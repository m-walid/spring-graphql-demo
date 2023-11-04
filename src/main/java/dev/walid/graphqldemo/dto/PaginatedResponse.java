package dev.walid.graphqldemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaginatedResponse<T> {
    private T data;
    private long totalElements;
    private int totalPages;

}
