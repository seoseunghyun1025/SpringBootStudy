package com.example.firstproject.service;

import com.example.firstproject.DTO.CommentDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.respository.ArticleRepository;
import com.example.firstproject.respository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDTO> comments(Long articleId) {
        //엔티티로 받아와서 엔티티에 넣음
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        //엔티티 타입을 DTO로 변환함
        List<CommentDTO> dtos = new ArrayList<CommentDTO>();
        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            CommentDTO dto = CommentDTO.createCommentDTO(c); //createCommentDTO static
            dtos.add(dto);
        }
        return dtos;
        //람다식으로 코딩
        /* return commentRepository.findByArticleId(articleId)
                 .stream()
                 .map(comment -> CommentDTO.createCommentDTO(comment))
                 .colle;
        */
    }
    public CommentDTO create(Long articleId, CommentDTO dto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
        Comment comment = Comment.createComment(dto, article);
        Comment created = commentRepository.save(comment);
        return CommentDTO.createCommentDTO(created);
    }
    @Transactional
    public CommentDTO delete(Long id){
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));
        commentRepository.delete(target);
        return CommentDTO.createCommentDTO(target);
    }
    public CommentDTO update(Long id, CommentDTO dto){
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        target.patch(dto);
        Comment updated = commentRepository.save(target);
        return CommentDTO.createCommentDTO(updated);
    }
}
