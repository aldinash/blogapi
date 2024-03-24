package org.example.blogapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty(message = "name should not be null or empty")
    private String name;
    @NotEmpty
    @Size(min=10, message = "comment body must be minimum 10 characters ")
    private String body;
}