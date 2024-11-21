package com.example.firstproject.service;

import com.example.firstproject.DTO.MemberDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.MemberEntity;
import com.example.firstproject.respository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    public List<MemberEntity> index() {
        return (List<MemberEntity>) memberRepository.findAll();
    }
    public MemberEntity show(String email){
        return memberRepository.findById(email).orElse(null);
    }
    public MemberEntity create(MemberDTO dto){
        MemberEntity member = dto.toEntity();
        if(member.getEmail() != null){
            return null;
        }
        return memberRepository.save(member);
    }
    public MemberEntity update(String email, MemberDTO dto){
        MemberEntity member = dto.toEntity();
        MemberEntity target = memberRepository.findById(email).orElse(null);
        if(target == null || !email.equals(member.getEmail())){
            return null;
        }
        target.patch(member);
        MemberEntity updated = memberRepository.save(member);
        return updated;
    }
    public MemberEntity delete(String email){
        MemberEntity target = memberRepository.findById(email).orElse(null);
        if (target == null) {
            return null;
        }
        memberRepository.delete(target);
        return target;
    }
}
