package org.example.blogapi.service.impl;

import org.example.blogapi.dto.PostDto;
import org.example.blogapi.entity.Post;
import org.example.blogapi.exception.ResourceNotFoundException;
import org.example.blogapi.repository.PostRepository;
import org.example.blogapi.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainPostService implements PostService {
    private final PostRepository postRepository;
    public MainPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return mapToDto(postRepository.save(post));
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setDescription(post.getDescription());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        return postDto;
    }
}