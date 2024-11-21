package com.example.firstproject.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class LoginRequest {

    private String loginId;
    private String password;
}