package com.example.firstproject;

import com.example.firstproject.entity.User;
import com.example.firstproject.entity.UserRole;
import com.example.firstproject.respository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MakeInitData {
    @Autowired
    UserRepository userRepository;
    //더미데이타 빈
    @PostConstruct
    public void makeAdminAndUser(){
        User admin1 = User.builder()
                .loginId("admin1")
                .password("1234")
                .nickname("관리자")
                .role(UserRole.ADMIN)
                .build();
        userRepository.save(admin1);

        User user1 = User.builder()
                .loginId("user1")
                .password("1234")
                .nickname("User1")
                .role(UserRole.USER)
                .build();
        userRepository.save(user1);
    }
}
