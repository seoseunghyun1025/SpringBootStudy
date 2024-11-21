package com.example.firstproject.controller;

import com.example.firstproject.DTO.MemberDTO;
import com.example.firstproject.entity.MemberEntity;
import com.example.firstproject.respository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    MemberRepository memberRepository;
    @PostMapping("/member/create")
    public String createArticles(MemberDTO mem) {
        //log.info(form.toString());
        System.out.println(mem.toString());
        MemberEntity at = mem.toEntity();
        memberRepository.save(at);//DTO와 DAO를 엔티티, 레파지토리로 변환 후 DB에 저장 insert문
        System.out.println(mem.getName());
        return "redirect:/hi";
    }
    @PostMapping("/member/login")  //id 값을 기본키
    public String show(MemberDTO mem){
        System.out.println("url변수 값" + mem.getName());
        MemberEntity memEn = mem.toEntity();
        MemberEntity target = memberRepository.findById(memEn.getEmail()).orElse(null); //findById()는 select문에 where절 추가다.
        if(target != null){
            return "redirect:/hi/" + target.getName();
        }
        return "redirect:/hi";
    }
    @GetMapping("/memberList")
    public String index(Model model){
        List<MemberEntity> memberList = (List<MemberEntity>) memberRepository.findAll();
        model.addAttribute("memberList",memberList);
        return "member/memberList";
    }
    @GetMapping("/member/{email}")
    public String edit(@PathVariable String email, Model model){
        MemberEntity member = memberRepository.findById(email).orElse(null);
        model.addAttribute("member",member);
        return "member/memberDetail";
    }
    @GetMapping("/member/{email}/update")
    public String update(@PathVariable String email, Model model){
        MemberEntity member = memberRepository.findById(email).orElse(null);
        model.addAttribute("member",member);
        return "member/memberEdit";
    }
    @PostMapping("/member/{email}/edit")
    public String update(@PathVariable String email, MemberDTO mem){
        MemberEntity memEn = mem.toEntity();
        //레파지토리로 저장
        MemberEntity target = memberRepository.findById(email).orElse(null);
        System.out.println(target.toString());
        if(target != null) {
            memberRepository.save(memEn);
        }
        return "redirect:/member/" + target.getEmail();
    }
    @GetMapping("/member/{email}/delete")
    public String delete(@PathVariable String email, RedirectAttributes rttr){
        MemberEntity target = memberRepository.findById(email).orElse(null);
        if(target != null){
            memberRepository.delete(target);
            rttr.addFlashAttribute("memDelete", "삭제됐습니다!");
        }
        return "redirect:/memberList";
    }
}
