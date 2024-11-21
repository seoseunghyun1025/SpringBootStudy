package com.example.firstproject.controller;
import com.example.firstproject.DTO.ArticleForm;
import com.example.firstproject.DTO.CommentDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.User;
import com.example.firstproject.respository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import com.example.firstproject.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
@Slf4j //로깅기능
@Controller
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleService articleService;
    @Autowired
    CommentService commentService;

    @GetMapping("/articles/{id}")
    public String show(@PathVariable("id") Long id, Model model,
                       @SessionAttribute(name = "userId", required = false) Long userId,
                       @SessionAttribute(name = "nickname", required = false) String nickname){
        model.addAttribute("userId", userId);
        model.addAttribute("nickname", nickname);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDTO> commentDTOS = commentService.comments(id);
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDTOS", commentDTOS);
        return "articles/show";
    }

    @GetMapping("/articles/new")
    public String newArticles(Model model,
                              @SessionAttribute(name = "userId", required = false) Long userId,
                              @SessionAttribute(name = "nickname", required = false) String nickname) {
        model.addAttribute("userId", userId);
        model.addAttribute("nickname", nickname);
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticles(ArticleForm form,
                                 @SessionAttribute(name = "userId", required = false) Long userId){
        form = new ArticleForm(form.getId(), form.getTitle(), form.getContent(), userId);

        Article article = articleService.create(form);
        return "redirect:/articles/" + article.getId();
    }

    @GetMapping("/articles")
    public String index(Model model,
                        @SessionAttribute(name = "userId", required = false) Long userId,
                        @SessionAttribute(name = "nickname", required = false) String nickname){
        model.addAttribute("userId", userId);
        model.addAttribute("nickname", nickname);

        List<Article> articleList = (List<Article>) articleRepository.findAll();
        model.addAttribute("articleList", articleList);
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model,
                       @SessionAttribute(name = "userId", required = false) Long userId,
                       @SessionAttribute(name = "nickname", required = false) String nickname){
        model.addAttribute("userId", userId);
        model.addAttribute("nickname", nickname);

        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "/articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(@ModelAttribute ArticleForm dto, Model model,
                         HttpServletRequest request,
                         @RequestParam("articleUserid") Long userId,
                         @RequestParam("id") Long id){
        Article updatedArticle = articleService.update(userId, id, dto, request);
        model.addAttribute("article", updatedArticle);
        return "redirect:/articles/" + dto.getId();
    }
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id,
                         HttpServletRequest request,
                         @SessionAttribute(name = "userId", required = false) Long user,
                         @RequestParam("userId") Long userId){
        articleService.delete(id,userId, request);
        return "redirect:/articles";
    }
}
