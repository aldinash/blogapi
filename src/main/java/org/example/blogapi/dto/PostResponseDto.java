package org.example.blogapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostResponseDto {
    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}