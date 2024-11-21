package com.example.firstproject.api;

import com.example.firstproject.DTO.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.respository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
public class ArticleApiController {
    /*
    @Autowired
    ArticleService articleService;

    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        if (created != null)
        {
               return ResponseEntity.status(HttpStatus.OK).body(created);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        // 잘못된 요청 처리
        if(deleted == null){
            //스타트 라인에 배드 상태값을 넣어주는 동작을 함
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); //잘못된 값을 보내기 위한 동작
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);
        if(updated == null || id != updated.getId()){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //build()는 body가 없음
        }
        return ResponseEntity.status(HttpStatus.OK).body(updated); //수정 작업을 body로 보냄
    }
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
     */
}