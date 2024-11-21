package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="UserMember")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;
    private String password;
    private String nickname;
    private UserRole role;

    public void update(String loginId, String password, String nickname, UserRole role) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }
}
