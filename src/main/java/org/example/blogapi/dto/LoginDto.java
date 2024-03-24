package org.example.blogapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
