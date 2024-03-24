package org.example.blogapi.service;

import org.example.blogapi.dto.LoginDto;
import org.example.blogapi.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
