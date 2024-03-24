package org.example.blogapi.dto;

import lombok.Data;

@Data
public class JwtAuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer";
}
