package org.example.blogapi.service;

import org.example.blogapi.dto.PostDto;
import org.example.blogapi.dto.PostResponseDto;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponseDto getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
