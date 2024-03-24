package org.example.blogapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.blogapi.dto.PostDto;
import org.example.blogapi.dto.PostResponseDto;
import org.example.blogapi.entity.Post;
import org.example.blogapi.exception.ResourceNotFoundException;
import org.example.blogapi.repository.PostRepository;
import org.example.blogapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainPostService implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        return mapToDto(postRepository.save(mapToEntity(postDto)));
    }

    @Override
    public PostResponseDto getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content = listOfPosts.stream()
                                            .map(this::mapToDto).collect(Collectors.toList());
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setContent(content);
        postResponseDto.setPageNo(pageNo);
        postResponseDto.setPageSize(pageSize);
        postResponseDto.setTotalElements(posts.getTotalElements());
        postResponseDto.setTotalPages(posts.getTotalPages());
        postResponseDto.setLast(posts.isLast());
        return postResponseDto;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        Post updatedPost = postRepository.save(mapToEntity(postDto));
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    private PostDto mapToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }
    private Post mapToEntity(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }
}