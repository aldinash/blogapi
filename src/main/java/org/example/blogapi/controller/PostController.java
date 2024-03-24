package org.example.blogapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.blogapi.dto.PostDto;
import org.example.blogapi.dto.PostResponseDto;
import org.example.blogapi.service.PostService;
import org.example.blogapi.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        PostDto response = postService.createPost(postDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponseDto getAllPosts(
            @RequestParam(value="pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value="pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value="sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable long id) {
        PostDto response = postService.updatePost(postDto, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
