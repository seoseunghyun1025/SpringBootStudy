package com.example.firstproject.api;

import com.example.firstproject.DTO.CommentDTO;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    //1. 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDTO>> comments(@PathVariable Long articleId) {

        List<CommentDTO> dtos = commentService.comments(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //2. 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDTO> create(@PathVariable Long articleId, @RequestBody CommentDTO dto){
        CommentDTO createDTO = commentService.create(articleId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(createDTO);
    }
    //3. 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDTO> update(@PathVariable Long id, @RequestBody CommentDTO dto){
        CommentDTO updatedDTO = commentService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDTO);
    }
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDTO>delete(@PathVariable Long id){
        CommentDTO deletedDTO = commentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDTO);
    }

}
