package com.example.firstproject.api;

import com.example.firstproject.DTO.MemberDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.MemberEntity;
import com.example.firstproject.respository.MemberRepository;
import com.example.firstproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;

@RestController
public class MemberApiController {
    @Autowired
    MemberService memberService;
    //get 목록, 내용 조회
    @GetMapping("/api/member")
    public List<MemberEntity> index(){
        return (List<MemberEntity>)memberService.index();
    }
    @GetMapping("/api/member/{email}")
    public MemberEntity show(@PathVariable String email){
        return memberService.show(email);
    }
    //post 생성
    @PostMapping("/api/member")
    public ResponseEntity<MemberEntity> create(@RequestBody MemberDTO dto){
        MemberEntity created = memberService.create(dto);
        if(created != null){
            return ResponseEntity.status(HttpStatus.OK).body(created);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //patch  수정
    @PatchMapping("/api/member/{email}")
    public ResponseEntity<MemberEntity> update(@PathVariable String email,
                                                    @RequestBody MemberDTO dto){
       MemberEntity updated = memberService.update(email, dto);
       if(updated != null){
           return ResponseEntity.status(HttpStatus.OK).body(updated);
       }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //delete
    @DeleteMapping("/api/member/{email}")
    public ResponseEntity<Member> delete(@PathVariable String email) {
        MemberEntity deleted = memberService.delete(email);
        if(deleted != null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}