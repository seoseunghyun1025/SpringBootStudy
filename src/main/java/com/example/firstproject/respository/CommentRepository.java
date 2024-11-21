package com.example.firstproject.respository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //실행 시키고 싶은 쿼리문을 작성
    @Query(value="SELECT * FROM comment WHERE article_id = :articleId",nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);
    List<Comment> findByNickname(String nickname);
}