package org.example.blogapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.blogapi.dto.LoginDto;
import org.example.blogapi.dto.RegisterDto;
import org.example.blogapi.entity.Role;
import org.example.blogapi.entity.User;
import org.example.blogapi.exception.BlogApiException;
import org.example.blogapi.repository.RoleRepository;
import org.example.blogapi.repository.UserRepository;
import org.example.blogapi.security.JwtTokenProvider;
import org.example.blogapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MainAuthService implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsUserByUsername(registerDto.getUsername())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Username is already exists");
        }
        if (userRepository.existsUserByEmail(registerDto.getEmail())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Email is taken");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "User registered successfully";
    }
}
