package org.example.blogapi.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.blogapi.dto.JwtAuthResponseDto;
import org.example.blogapi.dto.LoginDto;
import org.example.blogapi.dto.RegisterDto;
import org.example.blogapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JwtAuthResponseDto jwtAuthResponseDto = new JwtAuthResponseDto();
        jwtAuthResponseDto.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponseDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}