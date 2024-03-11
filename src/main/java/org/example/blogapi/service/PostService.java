package org.example.blogapi.service;

import org.example.blogapi.dto.PostDto;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
