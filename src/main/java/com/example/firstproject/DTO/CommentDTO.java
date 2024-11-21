package com.example.firstproject.DTO;

import com.example.firstproject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDTO {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDTO createCommentDTO(Comment comment){ //바로 DTO를 리턴하게 됨
        return new CommentDTO(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody());
    }
}
