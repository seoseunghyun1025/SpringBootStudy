package com.example.firstproject.service;

import com.example.firstproject.DTO.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.User;
import com.example.firstproject.respository.ArticleRepository;
import com.example.firstproject.respository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository; //DB를 바꿀때 타입만 바꾸면 된다 <- 맞는지 잘 모름
    @Autowired
    private UserRepository userRepository;
    public List<Article> index() {
        return (List<Article>) articleRepository.findAll();
    }
    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }
    public Article create(ArticleForm articleForm){
        User user = userRepository.findById(articleForm.getUserId())
                .orElseThrow(() -> new IllegalArgumentException());
        Article article = articleForm.toEntity(user);
        if(article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }
    public Article update(Long articleUserId, Long id, ArticleForm dto, HttpServletRequest request) {
        // 현재 로그인
        Long uid = (Long) request.getSession().getAttribute("userId");

        // ID로 기존 Article을 찾음
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));

        // 작성자 ID로 User를 찾음
        User user = userRepository.findById(articleUserId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + articleUserId));

        // 작성자의 ID가 현재 로그인한 사용자이거나, 관리자(예: uid == 1)인 경우에만 업데이트 허용
        if(article.getUser().getId().equals(uid) || uid == 1) {
            article.update(dto.getTitle(), dto.getContent());
            article.setUser(user); // 작성자 정보 업데이트
            // 저장
            return articleRepository.save(article);
        }

        return null; // 권한이 없는 경우 null 반환
    }/*
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos){
        List<Article> articleList = new ArrayList<>();
        for(int i = 0; i<dtos.size(); i++){
            ArticleForm dto = dtos.get(i);
            Article entity = dto.toEntity();
            articleList.add(entity);
        }
        for(int i=0;i<articleList.size();i++){
            Article article = articleList.get(i);
            articleRepository.save(article);
        }
        try{
            articleRepository.findById(-1L).orElseThrow();
        }catch (Exception e){
            throw new IllegalArgumentException("결제 실패!");
        }
        return articleList;
    }*/

    public void delete(Long id, Long userId, HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("userId");
        Article target = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
        if(userId == uid || uid == 1){
            articleRepository.delete(target);
            System.out.println("삭제되었습니다.");
        }else{
            System.out.println("삭제되지 않았습니다.");
        }
    }
}
