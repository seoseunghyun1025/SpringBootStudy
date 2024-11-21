package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter //모든 필드에 대한 getter를 정의
@ToString
@Data
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id 자동 생성
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user; // 작성자의 ID 추가


    public Article(Long id, String title, String content) {
    }

    public void patch(Article article){
        if(article.title != null){ //null을 덮어씌우지 않기 위해 사용
            this.title = article.title;
        }
        if(article.content != null){
            this.content = article.content;
        }
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
