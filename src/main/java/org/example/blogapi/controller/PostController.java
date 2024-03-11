package org.example.blogapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.blogapi.dto.PostDto;
import org.example.blogapi.service.PostService;
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
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto response = postService.createPost(postDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable long id) {
        PostDto response = postService.updatePost(postDto, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
