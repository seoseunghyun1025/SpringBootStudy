package com.example.firstproject.service;

import com.example.firstproject.DTO.JoinRequest;
import com.example.firstproject.DTO.LoginRequest;
import com.example.firstproject.entity.User;
import com.example.firstproject.respository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void join(JoinRequest req) {
        userRepository.save(req.toEntity());
        System.out.println(req.getNickname());
    }

    public User getLoginUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User login(LoginRequest req) {
        Optional<User> optionalUser = userRepository.findByLoginId(req.getLoginId());
        if(optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        if(!user.getPassword().equals(req.getPassword())) {
            return null;
        }
        return user;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        System.out.println("id= "+id);
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateUser(Long id, User updatedUser) {
        User user = getUserById(id);
        if (user != null) {
            user.update(updatedUser.getLoginId(), updatedUser.getPassword(), updatedUser.getNickname(), updatedUser.getRole());
            userRepository.save(user);
        }
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}