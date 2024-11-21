package com.example.firstproject.controller;

import com.example.firstproject.DTO.NoticeDTO;
import com.example.firstproject.entity.Notice;
import com.example.firstproject.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    //1. 공지사항 목록 조회
    @GetMapping(value = {"","/"})
    public String list(Model model,
                       @SessionAttribute(name= "userId", required = false) Long userId,
                       @SessionAttribute(name= "nickname", required = false) String nickname) {
        List<NoticeDTO> list = noticeService.list();
        if (list == null || list.isEmpty()) {
            model.addAttribute("userId", userId);
            model.addAttribute("nickname", nickname);
            model.addAttribute("noticeDTO", null);
            return "notices/notice";
        } else {
            model.addAttribute("userId", userId);
            model.addAttribute("nickname", nickname);
            model.addAttribute("noticeDTO", list);
        }
        model.addAttribute("userId", userId);
        model.addAttribute("nickname", nickname);
        return "notices/notice";
    }
    @GetMapping("/{id}/info")
    public String info(@PathVariable Long id,Model model,
                       @SessionAttribute(name="nickname", required = false) String nickname,
                       @SessionAttribute(name="userId",required = false) Long userId){
        Notice notice = noticeService.info(id);
        model.addAttribute("userId", userId);
        model.addAttribute("nickname", nickname);
        model.addAttribute("notice", notice);
        return "notices/info";
    }
    @GetMapping("/create")
    public String createPage(Model model,
                             @SessionAttribute(name= "userId", required = false) Long userId,
                             @SessionAttribute(name= "nickname", required = false) String nickname){
        if(userId == null){
            userId = 0L;
        }
        if(userId != 1L){
            model.addAttribute("userId", userId);
            model.addAttribute("nickname", nickname);
            model.addAttribute("grant", "관리자만 가능합니다.");
            return "redirect:/notice";
        }
        model.addAttribute("userId", userId);
        model.addAttribute("nickname", nickname);
        model.addAttribute("noticeDTO", new NoticeDTO());
        return "notices/create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute NoticeDTO noticeDTO){
        noticeService.create(noticeDTO);
        return "redirect:/notice";
    }
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, NoticeDTO dto,
                         @SessionAttribute(name= "userId", required = false) Long userId,
                         @SessionAttribute(name= "nickname", required = false) String nickname,
                         Model mo){
        long userIdLong = userId != null ? userId : 0L; // userId가 null인 경우 0L로 초기화

        if(userIdLong != 1L){
            mo.addAttribute("grant", "관리자만 수정 가능합니다.");
            return "redirect:/notice";
        }
        NoticeDTO updated = noticeService.update(id, dto);
        if(updated == null || id != updated.getId()){
            return "redirect:/notice/";
        }
        return "redirect:/notice/" + updated.getId() + "/info";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id,
                         @SessionAttribute(name= "userId", required = false) Long userId,
                         @SessionAttribute(name= "nickname", required = false) String nickname){
        if(userId == null){
            userId = 0L;
        }
        if(userId != 1L){
            return "redirect:/notice";
        }
       noticeService.delete(id);
        return "redirect:/notice";
    }
}
