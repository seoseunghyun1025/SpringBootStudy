package com.example.firstproject.DTO;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ArticleForm {
    // 필드
    private Long id;
    private String title;
    private String content;
    private Long userId;

    public Article toEntity(User user){
        //id 필드가 없기 때문에 null, 있으면 작성
        return new Article(id,title,content, user); //dto 개념
    }
}
