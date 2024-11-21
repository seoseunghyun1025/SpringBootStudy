package com.example.firstproject.DTO;

import com.example.firstproject.entity.User;
import com.example.firstproject.entity.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JoinRequest {

    private String loginId;
    private String password;
    private String passwordCheck;
    private String nickname;

    public User toEntity() {
        return User.builder()
                .loginId(this.loginId)
                .password(this.password)
                .nickname(this.nickname)
                .role(UserRole.USER)
                .build();
    }
}
